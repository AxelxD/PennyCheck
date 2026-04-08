# PennyCheck

API empresarial desarrollada en **Java 21 + Quarkus** para **Compartamos Servicios**, diseñada para desacoplar e integrar el consumo del servicio **Bank Account Verification** de **Círculo de Crédito**.

---

# Problemática identificada

Durante el levantamiento inicial con personal de la empresa se detectó la necesidad de **validar cuentas bancarias antes de ejecutar operaciones financieras sensibles**.

Actualmente, Compartamos Servicios requiere consumir el servicio **Bank Account Verification** de Círculo de Crédito; sin embargo, existen diferencias importantes entre:

- La estructura de datos interna de la empresa
- La nomenclatura de campos del proveedor externo
- El flujo de respuesta, que puede devolverse posteriormente
- Los lineamientos de seguridad y trazabilidad requeridos

La principal problemática consiste en que **no existe una capa intermedia especializada que traduzca, proteja y administre esta integración de forma controlada**.

---

# Mitigación propuesta

La solución se implementa mediante el desarrollo de una API empresarial en **Java Quarkus** llamada **PennyCheck**, la cual:

1. Recibe solicitudes internas
2. Valida los datos
3. Transforma la información al formato de Círculo de Crédito
4. Consume el servicio externo
5. Expone un webhook para recibir la respuesta
6. Homologa la respuesta al formato de Compartamos
7. Aplica seguridad, trazabilidad y control de errores

---

# Diagrama de bloques de la solución

![alt text](image.png)

---

# Definición de componentes y requerimientos

## 1. Sistemas internos Compartamos

### Componentes
- Consumidor interno
- Datos bancarios de entrada
- Identificador de correlación

### Requerimientos funcionales
- Enviar solicitud de validación
- Consultar o recibir resultado

### Requerimientos externos
- Consumo REST por HTTPS
- Formato JSON

### Requerimientos de diseño
- Contrato estable
- Bajo acoplamiento con proveedor

### Atributos
- Trazabilidad
- Consistencia
- Facilidad de integración

---

## 2. API PennyCheck

### Componentes
- Endpoint principal
- Validador
- Transformador
- Cliente REST externo
- Webhook
- Logs

### Requerimientos funcionales
- Validar datos obligatorios
- Traducir payload
- Consumir servicio externo
- Procesar webhook
- Estandarizar respuesta

### Requerimientos de diseño
- Java 21
- Quarkus
- Arquitectura modular
- Configuración desacoplada

### Atributos
- Escalabilidad
- Mantenibilidad
- Disponibilidad

---

## 3. Integración externa con Círculo de Crédito

### Componentes
- Endpoint externo
- Credenciales
- Contrato técnico

### Requerimientos
- Disponibilidad del servicio
- Seguridad en autenticación
- Manejo de errores y reintentos

### Atributos
- Confiabilidad
- Interoperabilidad

---

## 4. Webhook

### Componentes
- Endpoint callback
- Procesador de respuesta
- Control idempotente

### Requerimientos
- Recepción segura
- Validación de origen
- Asociación con folio original

### Atributos
- Seguridad
- Auditabilidad

---

## 5. Seguridad

### Componentes
- HTTPS
- Token o firma
- Control de acceso
- Logs de auditoría

### Requerimientos
- Protección de secretos
- Cifrado en tránsito
- Validación de autenticidad
- Protección del webhook

### Atributos
- Confidencialidad
- Integridad
- Disponibilidad

---

# Dependencia y prioridad por funcionalidad

| Elemento | Funcionalidad | Dependencia | Prioridad |
|---|---|---|---|
| Sistemas internos | Envío de solicitud | Contrato de API | Alta |
| API PennyCheck | Validación de datos | Reglas funcionales | Alta |
| API PennyCheck | Homologación de campos | Contrato interno y externo | Alta |
| API PennyCheck | Consumo de servicio externo | Credenciales proveedor | Alta |
| API PennyCheck | Trazabilidad | ID correlación | Alta |
| Webhook | Recepción de resultado | Callback del proveedor | Alta |
| Webhook | Control de duplicados | Idempotencia | Media |
| Seguridad | Protección HTTPS | Infraestructura | Alta |
| Seguridad | Validación de autenticidad | Token/firma | Alta |
| Seguridad | Auditoría | Logs | Media |

---

# Alcance del producto

## Incluye
- Desarrollo de API PennyCheck
- Endpoint REST de entrada
- Validación y homologación de datos
- Integración con Bank Account Verification
- Webhook seguro
- Trazabilidad y logs
- Seguridad base

## No incluye
- Rediseño de sistemas consumidores
- Cambio del proveedor externo
- Portal administrativo adicional
- Nuevos procesos fuera de validación bancaria

---

# Stack tecnológico

- **Java 21**
- **Quarkus**
- **REST API**
- **Webhook callback**
- **JUnit**
- **GitHub**
- **Travis CI**
- **Trello / Zube**
- **Git Flow (develop / master)**

---

# Objetivo técnico

Construir una capa de integración desacoplada que permita a Compartamos Servicios consumir el servicio de validación bancaria de Círculo de Crédito de forma:

- Segura
- Escalable
- Trazable
- Mantenible
- Resiliente

---

# Conclusión

La solución **PennyCheck**, construida en **Java con Quarkus**, representa una propuesta técnicamente viable para desacoplar la complejidad de la integración con **Círculo de Crédito**, homologar la información institucional y garantizar la recepción segura de resultados mediante un webhook.

El análisis realizado permite delimitar claramente el alcance del producto, establecer prioridades funcionales y sentar bases sólidas para las siguientes fases del proyecto, particularmente la planeación detallada, diseño técnico y desarrollo incremental del sistema.