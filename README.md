<p align="center">
  <img src="src/recursos/logo.png" width="220">
</p>

# Agencia de Modelaje  
## "No Más Enanos Por Favor"

### Sistema de Gestión — Trabajo Práctico de Estructuras de Datos

---

## Descripción del sistema

Sistema desarrollado en **Java** con **interfaz gráfica Swing** para gestionar una agencia de modelaje.

El sistema permite administrar la información relacionada con:

- Modelos
- Fotógrafos
- Lugares
- Eventos (públicos y privados)

Además, permite **asignar modelos y fotógrafos a eventos**, así como **guardar y cargar automáticamente la información** mediante archivos de texto.

---

## Características principales

El sistema permite:

- Registrar **modelos** con su información básica
- Registrar **fotógrafos**
- Registrar **lugares**
- Crear **eventos públicos o privados**
- Asignar modelos y fotógrafos a eventos
- Visualizar la información mediante **tablas en la interfaz gráfica**
- Guardar automáticamente los datos al cerrar el sistema
- Cargar automáticamente los datos al iniciar el sistema

---

## Interfaz del sistema

La aplicación cuenta con una interfaz gráfica desarrollada en **Swing**, organizada en pestañas:

- Modelos
- Fotógrafos
- Lugares
- Eventos
- Asignar

Esto permite gestionar de forma clara cada elemento del sistema.

---

## Estructura del proyecto

```
AgenciaModelaje
│
├── datos
│   ├── modelos.txt
│   ├── fotografos.txt
│   ├── lugares.txt
│   └── eventos.txt
│
├── src
│   │
│   ├── Classes
│   │   ├── Persona.java
│   │   ├── Modelo.java
│   │   ├── Fotografo.java
│   │   ├── Lugar.java
│   │   ├── Evento.java
│   │   ├── EventoPublico.java
│   │   ├── EventoPrivado.java
│   │   └── Agencia.java
│   │
│   ├── recursos
│   │   └── logo.png
│   │
│   └── VentanaPrincipal.java
│
└── README.md
```

---

## Estructura de clases (POO)

```
Persona (abstracta)
├── Modelo
└── Fotografo

Evento (abstracta)
├── EventoPublico
└── EventoPrivado

Lugar
Agencia
VentanaPrincipal
```

### Descripción de las clases principales

**Persona (abstracta)**  
Clase base que representa una persona dentro del sistema.

**Modelo**  
Hereda de `Persona` y representa a un modelo registrado en la agencia.

**Fotografo**  
Hereda de `Persona` y representa a un fotógrafo registrado.

**Lugar**  
Representa los lugares donde se pueden realizar eventos.

**Evento (abstracta)**  
Clase base para los diferentes tipos de eventos.

**EventoPublico**  
Evento abierto al público.

**EventoPrivado**  
Evento con acceso restringido.

**Agencia**  
Clase encargada de gestionar todos los modelos, fotógrafos, eventos y lugares.

**VentanaPrincipal**  
Contiene la interfaz gráfica del sistema y el método `main`.

---

## Archivos de persistencia

El sistema guarda los datos en archivos de texto ubicados en la carpeta `datos`.

Archivos utilizados:

- `datos/modelos.txt`
- `datos/fotografos.txt`
- `datos/lugares.txt`
- `datos/eventos.txt`

Estos archivos se crean automáticamente si no existen.

---

## Instrucciones de compilación y ejecución

### 1. Compilar el proyecto

Ubicarse en la carpeta `src` y ejecutar:

```
javac Classes/*.java VentanaPrincipal.java
```

### 2. Ejecutar el programa

```
java VentanaPrincipal
```

---

## Restricciones del proyecto

De acuerdo con los requisitos del trabajo práctico:

No se utilizan estructuras de datos de `java.util` como:

- ArrayList
- LinkedList
- HashMap
- Set

Para almacenar colecciones se utilizan **únicamente arreglos (`[]`)**.

Se permite el uso de:

- `File`
- `Scanner`

---

## Principios de Programación Orientada a Objetos aplicados

### Herencia

Las clases derivadas reutilizan comportamiento de clases base.

Ejemplo:

- `Modelo` y `Fotografo` heredan de `Persona`
- `EventoPublico` y `EventoPrivado` heredan de `Evento`

---

### Clases abstractas

Las clases:

- `Persona`
- `Evento`

son **abstractas**, lo que significa que no pueden instanciarse directamente.

---

### Polimorfismo

Se utiliza cuando se trabaja con arreglos del tipo:

```
Evento[]
```

Esto permite tratar eventos públicos y privados de forma uniforme.

---

### Encapsulamiento

Los atributos de las clases son **privados o protegidos** y se accede a ellos mediante:

- getters
- setters

Esto protege el estado interno de los objetos.

---

### Relaciones entre clases

La clase **Agencia** gestiona:

- Modelos
- Fotógrafos
- Eventos
- Lugares

Cada **Evento** puede tener asociado:

- Un `Lugar`
- Uno o varios `Modelo`
- Uno o varios `Fotografo`

---

## Tecnologías utilizadas

- Java
- Swing
- Programación Orientada a Objetos
- Archivos planos para persistencia de datos

---

## Integrantes del grupo

- Nombre integrante 1
- Nombre integrante 2
- Nombre integrante 3

---

## Autor

Proyecto académico desarrollado como práctica de **Programación Orientada a Objetos en Java**.
