Este proyecto demuestra la automatización de pruebas funcionales API.
API Testing → Validación del CRUD completo del servicio público Petstore Swagger.
Pruebas API – Petstore Swagger
Método	Endpoint	Descripción

Tecnologias utilizadas.

| Componente              | Descripción                              |
| ----------------------- | ---------------------------------------- |
| **Java 11+**            | Lenguaje base                            |
| **Maven**               | Gestor de dependencias                   |
| **Serenity BDD 4.1.17** | Framework de automatización y reportería |
| **Cucumber 7.x**        | Framework BDD (Gherkin)                  |
| **Screenplay Pattern**  | Diseño de tareas, acciones y preguntas   |
| **Rest-Assured**        | Consumo de endpoints REST                |
| **JUnit 4**             | Ejecutor de escenarios                   |
| **Hamcrest / AssertJ**  | Librerías de validaciones                |

Pet → CRUD completo para gestión de mascotas.

| Método     | Endpoint       | Descripción            |
| ---------- | -------------- | ---------------------- |
| **POST**   | `/pet`         | Crear una mascota      |
| **GET**    | `/pet/{petId}` | Consultar una mascota  |
| **PUT**    | `/pet`         | Actualizar una mascota |
| **DELETE** | `/pet/{petId}` | Eliminar una mascota   |

Store → Validación de pedidos (orders) y stock.

| Método     | Endpoint                 | Descripción                 |
| ---------- | ------------------------ | --------------------------- |
| **POST**   | `/store/order`           | Crear un pedido             |
| **GET**    | `/store/order/{orderId}` | Consultar pedido por ID     |
| **DELETE** | `/store/order/{orderId}` | Eliminar un pedido          |
| **GET**    | `/store/inventory`       | Consultar inventario actual |

User → Creación, login, consulta y eliminación de usuarios.

| Método     | Endpoint           | Descripción                    |
| ---------- | ------------------ | ------------------------------ |
| **POST**   | `/user`            | Crear usuario                  |
| **GET**    | `/user/{username}` | Obtener información de usuario |
| **GET**    | `/user/login`      | Login de usuario               |
| **DELETE** | `/user/{username}` | Eliminar usuario               |
