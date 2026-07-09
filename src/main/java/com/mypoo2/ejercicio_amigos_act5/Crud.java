package com.mypoo2.ejercicio_amigos_act5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Lógica CRUD sobre el archivo de contactos.
 * <p>
 * El archivo se guarda de forma relativa al proyecto (carpeta {@code data/}),
 * usando codificación UTF-8 y "!" como separador de campos.
 */
public class Crud {

    private final Path archivo = Paths.get("data", "Contacto_amigos.txt");

    /** Crea el archivo (y su carpeta) si aún no existe. */
    private void asegurarArchivo() throws IOException {
        if (Files.notExists(archivo)) {
            if (archivo.getParent() != null) {
                Files.createDirectories(archivo.getParent());
            }
            Files.createFile(archivo);
        }
    }

    /** Escribe la lista completa de contactos, reemplazando el contenido. */
    private void guardarTodo(List<Contacto> lista) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Contacto c : lista) {
            sb.append(c.getNombre()).append("!")
              .append(c.getTelefono()).append("!")
              .append(c.getCorreo()).append(System.lineSeparator());
        }
        Files.writeString(archivo, sb, StandardCharsets.UTF_8);
    }

    //==================== CREATE ====================

    /** @return true si se agregó, false si ya existía o hubo error. */
    public boolean create(Contacto nuevoContacto) {
        try {
            asegurarArchivo();

            if (buscar(nuevoContacto.getNombre()) != null) {
                System.out.println("El contacto ya existe.");
                return false;
            }

            String linea = nuevoContacto.getNombre() + "!"
                    + nuevoContacto.getTelefono() + "!"
                    + nuevoContacto.getCorreo() + System.lineSeparator();

            Files.writeString(archivo, linea, StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);

            System.out.println("Contacto agregado.");
            return true;

        } catch (IOException e) {
            System.err.println("No se pudo crear el contacto: " + e.getMessage());
            return false;
        }
    }

    //==================== READ ====================

    public ArrayList<Contacto> read() {
        ArrayList<Contacto> lista = new ArrayList<>();
        try {
            asegurarArchivo();

            for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split("!");

                if (datos.length != 3) {
                    System.out.println("Línea incorrecta: " + linea);
                    continue;
                }

                lista.add(new Contacto(datos[0], datos[1], datos[2]));
            }

        } catch (IOException e) {
            System.err.println("No se pudieron leer los contactos: " + e.getMessage());
        }
        return lista;
    }

    public Contacto buscar(String nombreBuscar) {
        for (Contacto c : read()) {
            if (c.getNombre().equalsIgnoreCase(nombreBuscar)) {
                return c;
            }
        }
        return null;
    }

    //==================== UPDATE ====================

    /** @return true si se encontró y actualizó, false en caso contrario. */
    public boolean update(String nombreBuscar, Contacto nuevoContacto) {
        try {
            asegurarArchivo();

            ArrayList<Contacto> lista = read();
            boolean encontrado = false;

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNombre().equalsIgnoreCase(nombreBuscar)) {
                    lista.set(i, nuevoContacto);
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                guardarTodo(lista);
                System.out.println("Contacto actualizado.");
            }
            return encontrado;

        } catch (IOException e) {
            System.err.println("No se pudo actualizar el contacto: " + e.getMessage());
            return false;
        }
    }

    //==================== DELETE ====================

    /** @return true si se encontró y eliminó, false en caso contrario. */
    public boolean delete(String nombreBuscar) {
        try {
            asegurarArchivo();

            ArrayList<Contacto> lista = read();
            boolean encontrado = lista.removeIf(
                    c -> c.getNombre().equalsIgnoreCase(nombreBuscar));

            if (encontrado) {
                guardarTodo(lista);
                System.out.println("Contacto eliminado.");
            }
            return encontrado;

        } catch (IOException e) {
            System.err.println("No se pudo eliminar el contacto: " + e.getMessage());
            return false;
        }
    }

}
