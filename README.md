# Laboratorio 3 - Sistema de Aulas (UFG)

JavaFX + FXML | Hibernate (JPA) | MySQL/MariaDB | Maven | MVC

## Requisitos

* JDK 17+
* Maven
* MySQL o MariaDB encendido en localhost:3306
* NetBeans (opcional: Scene Builder para editar los .fxml)

## Configuración

1. Edita `src/main/resources/hibernate.cfg.xml` y pon tu usuario/clave de MySQL.
2. La BD `aulas\_db`, las tablas y los datos iniciales se crean solos al primer arranque.

## Ejecutar

&#x20;   mvn clean javafx:run


## Credenciales iniciales

* Correo: admin@ufg.edu.sv
* Clave: admin123  (se guarda como hash SHA-256)

## Estructura

&#x20;   src/main/java/edu/ufg/aulas/
      App.java
      entidades/      Usuario, Aula, TipoAula, EstadoAula, Planta
      controladores/  LoginController, HomeController
      dao/            UsuarioDAO, AulaDAO, CatalogoDAO
      util/           HibernateUtil, HashUtil, DatosIniciales
    src/main/resources/
      vistas/         Login.fxml, Home.fxml
      hibernate.cfg.xml





# **## Arquetipo**

La carpeta `Arquetipo/` contiene el arquetipo Maven personalizado (AulasUFG-archetype)

con la estructura MVC, Login.fxml y Home.fxml.



Para instalarlo: entrar a la carpeta Arquetipo y ejecutar `mvn install`.

Para generar un proyecto: `mvn archetype:generate -DarchetypeCatalog=local`

(groupId edu.ufg, artifactId AulasUFG, package edu.ufg.aulas).

