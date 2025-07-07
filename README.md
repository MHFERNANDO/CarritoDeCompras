# 🛒 Carrito de Compras - Aplicación de Escritorio en Java Swing

Este proyecto es una aplicación de escritorio para la gestión de carritos de compras, desarrollada en Java utilizando Swing con un enfoque MDI (Multiple Document Interface). El sistema implementa arquitectura **MVC**, el patrón **DAO** para persistencia de datos, y sigue principios **SOLID** para un diseño limpio, extensible y mantenible.

---
# Autor
Fernando Martinez <p>
Grupo 1

## 🎯 Funcionalidades Principales

✅ Registro de usuarios con:
- Datos personales (nombre, cédula, correo, etc.)
- Selección y respuesta a preguntas de seguridad

✅ Inicio de sesión con control de acceso por rol (admin / usuario)

✅ Recuperación de contraseña usando preguntas de seguridad

✅ Gestión de productos (CRUD) — solo administrador

✅ Gestión de usuarios (CRUD) — solo administrador

✅ Carrito de compras:
- Crear carritos
- Añadir productos
- Calcular subtotal, IVA y total
- Visualizar/modificar/eliminar contenido del carrito
- Listar solo "mis carritos" o todos (admin)

✅ Internacionalización dinámica:
- Idiomas: Español 🇪🇸, Inglés 🇺🇸, Francés 🇫🇷, Italiano 🇮🇹
- Cambio de idioma en tiempo real con archivos `.properties`

✅ Formateo según región:
- Números y fechas usando `NumberFormat` y `DateFormat`

✅ Interfaz moderna:
- `JDesktopPane` + `JInternalFrame` (MDI)
- Uso de `ImageIcon` en todos los botones
- Gráfico personalizado usando `Graphics`

---

## 🧱 Tecnologías Usadas

- Java SE 17
- Swing
- MVC + DAO
- ResourceBundle para i18n
- Git & GitHub para control de versiones

---

## Diagrama UML
![image](https://github.com/user-attachments/assets/1c4f9782-67cb-4567-b3cf-e739fd432555)


