# Gestión de Préstamos Personales 💰🏦  #aejimenez.online

Proyecto desarrollado en **Java con Spring Boot**, siguiendo principios de **Clean Architecture**. Permite gestionar clientes, préstamos y pagos, con un enfoque en código mantenible y escalable.

## Tabla de Contenido
- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Consumo de la API](#consumo-de-la-api)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Autor](#autor)

## Descripción

Esta API expone servicios REST para:
- Crear y listar clientes.
- Registrar préstamos y consultar préstamos de un cliente.
- Registrar y consultar pagos sobre préstamos.
- Consultar saldo pendiente de un préstamo.

## Arquitectura

El proyecto se organiza en capas:
- **Domain:** Entidades y repositorios.
- **Application:** Casos de uso (lógica de negocio).
- **Infrastructure:** Persistencia JPA, adaptadores y mappers.
- **Web:** Controladores REST y DTOs.

## Tecnologías

- Java 21
- Spring Boot 3
- Spring Data JPA (Hibernate)
- PostgreSQL
- MapStruct

## Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/aejimenez19/Gestion-de-Prestamos-Personales
   ```
2. Configura la base de datos en `src/main/resources/application.properties`.
3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. La API estará disponible en [http://localhost:8080/api](http://localhost:8080/api)

## Consumo de la API

Documentación completa de los endpoints y ejemplos prácticos disponibles en: [docs/api_endpoints.md](docs/api_endpoints.md)

## Ejemplos de Uso

Puedes encontrar ejemplos de peticiones y respuestas en: [docs/ejemplos.md](docs/ejemplos.md)

## Autor

**Desarrollado por Álvaro Jiménez 👨‍💻**

