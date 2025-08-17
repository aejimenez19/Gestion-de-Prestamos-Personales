# Gestión de Préstamos Personales💰🏦

Proyecto desarrollado en **Java con Spring Boot**, siguiendo principios de **Clean Architecture**. Permite gestionar clientes, préstamos y pagos, con un enfoque en código mantenible y escalable.

## Funcionalidades
- Crear y listar clientes.
- Registrar préstamos para clientes.
- Registrar pagos de préstamos.
- Consultar saldo pendiente de un préstamo.

## Arquitectura
El proyecto se organiza en capas:
- **Domain**: Entidades y repositorios.
- **Application**: Casos de uso (lógica de negocio).
- **Infrastructure**: Persistencia JPA, adaptadores y mappers.
- **Interface**: Controladores REST y DTOs.

## Tecnologías
- Java 21
- Spring Boot 3
- Spring Data JPA (Hibernate)
- PostgreSQL
- MapStruct

## Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/aejimenez19/Gestion-de-Prestamos-Personales
2. Configurar la base de datos en application.properties.
3. Ejecutar la aplicación:
  ```bash
  ./mvnw spring-boot:run
 ```
5. Acceder a la API en:
   http://localhost:8080/api

## Autor
**Desarrollado por Álvaro Jiménez 👨‍💻**

