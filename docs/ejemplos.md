# Ejemplos de uso de la API

## Crear cliente `POST /api/clients`

#### Request
```json
{
  "identificationNumber": "12345678",
  "name": "Juan Pérez",
  "email": "juan.perez@correo.com",
  "phoneNumber": "555123456"
}
```

#### Response
```json
{
  "id": "1e2e3e4e-5678-1234-9876-654322199999",
  "identificationNumber": "12345678",
  "name": "Juan Pérez",
  "phoneNumber": "555123456"
}
```

---

## Obtener todos los clientes `GET /api/clients`

#### Response
```json
[
  {
    "id": "1e2e3e4e-5678-1234-9876-654322199999",
    "identificationNumber": "12345678",
    "name": "Juan Pérez",
    "phoneNumber": "555123456"
  }
]
```

---

## Crear préstamo `POST /api/loans`

#### Request
```json
{
  "clientId": "1e2e3e4e-5678-1234-9876-654322199999",
  "amount": 5000,
  "monthlyInterestRate": 1.5,
  "startDate": "2024-12-01"
}
```

#### Response
```json
{
  "id": "ab12cd34-5678-1234-cccc-111222333444",
  "clientId": "1e2e3e4e-5678-1234-9876-654322199999",
  "amount": 5000,
  "monthlyInterestRate": 1.5,
  "startDate": "2024-12-01",
  "isPerDay": false,
  "outstandingBalance": 5000,
  "currentMonthInterest": 75,
  "principalOutstanding": 5000,
  "totalPaidAmount": 0,
  "elapsedMonths": 0
}
```

---

## Registrar pago `POST /api/payments`

#### Request
```json
{
  "loanId": "ab12cd34-5678-1234-cccc-111222333444",
  "amount": 1000,
  "paymentDate": "2024-12-10"
}
```

#### Response
```json
{
  "id": "ddeeaaef-5678-1234-zzzz-321987654000",
  "loanId": "ab12cd34-5678-1234-cccc-111222333444",
  "amount": 1000,
  "paymentDate": "2024-12-10"
}
```
