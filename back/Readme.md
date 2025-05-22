# 🌙 Ruta Gemelar

**Ruta Gemelar** es una aplicación web pensada para acompañar a madres de embarazos múltiples durante su proceso. Permite llevar un diario emocional, visualizar eventos clave en un calendario, y consultar contenidos semanales adaptados al embarazo.

---

## 🛠️ Tecnologías utilizadas

- **Java 21** + **Spring Boot**
- **Spring Security** + JWT
- **PostgreSQL** + JPA/Hibernate
- **JUnit** + **Mockito** (test backend)
- **React** (frontend en fase final)
- **TailwindCSS** + Figma (diseño)

---

## 🧱 Arquitectura

- Patrón MVC: Controller · Service · Repository · Model
- Seguridad con `SecurityFilterChain` y filtro JWT personalizado
- Base de datos real conectada a PostgreSQL
- 111 semanas de embarazo precargadas

---

## 📌 Estado del proyecto

✅ Backend completamente funcional  
✅ Registro y login protegidos con JWT  
✅ CRUD de diario emocional autenticado  
✅ Test unitarios implementados  
✅ Vista de semanas y calendario conectadas  
🚧 Frontend en fase final de maquetación

---

## ▶️ Cómo ejecutar

1. Clonar el repositorio:

```bash
git clone https://github.com/tuusuario/ruta-gemelar.git
```

2. Ejecutar el backend:

```bash
./mvnw spring-boot:run
```

3. Acceder desde el navegador:

```
http://localhost:8080
```

> ℹ️ Asegúrate de tener PostgreSQL corriendo y configurado en `application.properties`

---

## 🖼️ Vistas de ejemplo

| Vista semanas | Diario emocional |
|---------------|------------------|
| ![Semanas](./img/vista_semanas.png) | ![Diario](./img/diario_emocional.png) |

---

## ✒️ Autoría

Desarrollado por **Alba Riera**  
Bootcamp Web FullStack · [Factoría F5](https://factoriaf5.org/)

---

## 📄 Licencia

Uso educativo. No se permite uso comercial sin autorización.
