# Actividad 5 — CRUD Contacto Amigos

Proyecto de Programación Orientada a Objetos (POO) en Java con interfaz Swing.
Permite gestionar una agenda de contactos (crear, listar, buscar, actualizar y
eliminar) guardándolos en un archivo de texto.

## Estructura del proyecto

```
actividad5/
├── src/main/java/com/mypoo2/ejercicio_amigos_act5/
│   ├── Ejercicio_amigos_act5.java   # main: arranca la aplicación
│   ├── Vista.java                   # interfaz gráfica (JFrame)
│   ├── Crud.java                    # lógica CRUD sobre el archivo
│   └── Contacto.java                # modelo (nombre, teléfono, correo)
├── data/
│   └── Contacto_amigos.txt          # archivo donde se guardan los contactos
└── docs/
    ├── diagrama_clases.md           # diagrama de clases (Mermaid)
    └── diagrama_casos_uso.md        # diagrama de casos de uso (Mermaid)
```

## Cómo verlo

Los diagramas están en Mermaid dentro de la carpeta `docs/`. Se ven
renderizados directamente en GitHub o en cualquier editor con soporte Mermaid.
