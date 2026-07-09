# Diagrama de Clases

Proyecto: **CRUD Contacto Amigos** (Java Swing + POO)

Este diagrama muestra las clases del proyecto y sus relaciones. La aplicación
sigue una separación por responsabilidades:

- `Contacto` → **Modelo** (los datos de un contacto).
- `Crud` → **Lógica de negocio / persistencia** (leer y escribir el archivo).
- `Vista` → **Interfaz gráfica** (ventana Swing).
- `Ejercicio_amigos_act5` → **Punto de entrada** (arranca la aplicación).

```mermaid
classDiagram
    class Ejercicio_amigos_act5 {
        ~main(String[] args) void
    }

    class Vista {
        -Crud crud
        -JTextField txtNombre
        -JTextField txtTelefono
        -JTextField txtCorreo
        -JTextField txtBuscar
        +Vista()
        -btnCreateActionPerformed(ActionEvent) void
        -btnReadActionPerformed(ActionEvent) void
        -btnUpdateActionPerformed(ActionEvent) void
        -btnDeleteActionPerformed(ActionEvent) void
        -btnBuscarActionPerformed(ActionEvent) void
        -btnLimpiarActionPerformed(ActionEvent) void
        -btnSalirActionPerformed(ActionEvent) void
    }

    class Crud {
        -Path archivo
        +create(Contacto nuevoContacto) boolean
        +read() ArrayList~Contacto~
        +buscar(String nombreBuscar) Contacto
        +update(String nombreBuscar, Contacto nuevoContacto) boolean
        +delete(String nombreBuscar) boolean
    }

    class Contacto {
        -String nombre
        -String telefono
        -String correo
        +Contacto()
        +Contacto(String nombre, String telefono, String correo)
        +getNombre() String
        +setNombre(String nombre) void
        +getTelefono() String
        +setTelefono(String telefono) void
        +getCorreo() String
        +setCorreo(String correo) void
        +toString() String
    }

    class JFrame {
        <<Swing>>
    }

    Vista --|> JFrame : extends
    Ejercicio_amigos_act5 ..> Vista : crea
    Vista *-- Crud : compone
    Vista ..> Contacto : usa
    Crud ..> Contacto : gestiona
```

## Relaciones

| Relación | Tipo | Descripción |
|----------|------|-------------|
| `Vista` → `JFrame` | Herencia | `Vista` extiende `javax.swing.JFrame`. |
| `Ejercicio_amigos_act5` → `Vista` | Dependencia | El `main` instancia y muestra la ventana. |
| `Vista` → `Crud` | Composición | `Vista` contiene un objeto `Crud` como atributo. |
| `Vista` → `Contacto` | Dependencia | Crea objetos `Contacto` con lo que el usuario escribe. |
| `Crud` → `Contacto` | Dependencia | Devuelve/gestiona objetos `Contacto` desde el archivo. |
