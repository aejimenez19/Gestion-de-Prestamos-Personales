# Endpoints de la API

### **Clientes**
- `POST /api/clients`
  - Crea un nuevo cliente.
- `GET /api/clients`
  - Obtiene todos los clientes.

### **Préstamos**
- `POST /api/loans`
  - Crea un nuevo préstamo.
- `GET /api/loans/{userId}`
  - Obtiene todos los préstamos de un cliente.

### **Pagos**
- `POST /api/payments`
  - Registra un pago a un préstamo.
- `GET /api/payments/{loanId}`
  - Obtiene todos los pagos realizados a un préstamo.

Detalles de los modelos y ejemplos de request/response en [ejemplos.md](ejemplos.md)
