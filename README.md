
# Pilotes Order Management Application

Welcome to the Pilotes Order Management Application! This Spring-based application is designed to facilitate the management of orders for the delectable "pilotes," a Majorcan meatball stew recipe. The application provides a comprehensive set of REST API endpoints that enable you to create, update, and search pilotes orders. Each order is associated with essential customer details and includes information such as the delivery address, number of pilotes, and the order total. The application ensures robust data validation and incorporates security measures to ensure a seamless user experience.


## Prerequisites

Before you start working with the Pilotes Order Management Application, please ensure that you have the following prerequisites:

- **Java 17:** This project is developed using Java programming language. Make sure you have Java installed on your machine. You can download it from the [official Java website](https://www.oracle.com/java/technologies/javase-downloads.html).

- **Maven:** This project uses Maven as a build tool. You can download and install Maven from the [official Maven website](https://maven.apache.org/download.cgi).

- **Docker (optional):** If you plan to run the application in a Docker container, make sure you have Docker installed on your system. You can download Docker from the [official Docker website](https://www.docker.com/get-started).

## API Reference

#### Create a Pilotes Order

```http
  POST /api/orders/{clientId}
```
Create a new Pilotes order by specifying the number of pilotes (5, 10, or 15).


| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `clientId` | `long` | Path parameter. ID of the client. |
| `deliveryAddress` | `string` | **Required**. Delivery address |
| `numberOfPilotes` | `integer` | **Required**. Number of pilotes (5, 10, or 15) |

#### Update a Pilotes Order

```http
  PUT /api/orders/${orderId}
```
Update an existing Pilotes order within a 5-minute window after creation.

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `orderId`      | `long` | 	Path parameter. ID of the order to update. |
| `deliveryAddress`      | `string` | **Required**.  Updated delivery address |
| `numberOfPilotes`      | `integer` | **Required**. Updated number of pilotes (5, 10, or 15) |

#### Delete an Order

```http
  DELETE /api/orders/{orderId}
```
Delete an existing Pilotes order by its ID.

| Parameter | Type     | Description                                 |
| :-------- | :------- |:--------------------------------------------|
| `orderId`      | `long` | 	Path parameter. ID of the order to delete. |

#### Search Orders

```http
  GET /api/orders/search?searchTerm=Doe
```
Retrieve orders based on customer data.

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `searchTerm`      | `string` | **Required**.  Term to search for in orders. |

**Note**: To access this operation, provide your Basic Auth credentials (username and password) when making the request. Basic Auth is used to ensure the security of this sensitive operation.

### Create a Client

```http
  POST /api/clients
```
Create a new client by providing client details.

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `firstName`      | `string` | **Required**.  First name of the client. |
| `lastName`      | `string` | **Required**.  Last name of the client. |
| `telephoneNumber`      | `string` | **Required**.  Telephone number of the client. |

### Update a Client

```http
  PUT /api/clients/{clientId}
```

Update an existing client by its ID.

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `clientId`      | `long` | Path parameter. ID of the client to update. |
| `firstName`      | `string` | **Required**.  First name of the client. |
| `lastName`      | `string` | **Required**.  Last name of the client. |
| `telephoneNumber`      | `string` | **Required**.  Telephone number of the client. |

### Retrieve Clients

```http
  GET /api/clients
```

Update an existing client by its ID.

| Parameter | Type     | Description                       |
|:----------|:---------| :-------------------------------- |
| `name`    | `string` | Optional. Search clients by name. |

## Authors

- [@MohamedMosa](https://www.linkedin.com/in/mohamed-mosa-924992183)


## Support

For any questions or assistance, please contact m.mosa30@hotmail.com.



## Features

- **Create Pilotes Orders**: Easily place orders for the delightful "pilotes" dish by specifying the desired number of pilotes (5, 10, or 15) to enjoy.
- **Timely Updates**: Seamlessly modify existing Pilotes orders within a 5-minute window after their creation, ensuring you have the flexibility you need.
- **Efficient Search**: Effortlessly search for orders using customer data, with the ability to perform partial searches based on customer names. Find orders faster and more accurately.
- **Data Integrity**: Our application takes data integrity seriously. Enjoy peace of mind with thorough data validation.
- **Enhanced Security**: We prioritize your data's security. Our search operation is secured with authorized access, ensuring that only authorized users can retrieve sensitive information.
- **Efficient Data Storage**: Leveraging an in-memory H2 database, we ensure efficient and reliable data storage to support your order management needs.
- **Thorough Testing**: Our comprehensive suite of unit and integration tests guarantees that the functionality and reliability of the application are maintained at the highest level.


## Documentation

[Documentation](http://localhost:8080/swagger-ui/index.html)


## Local deployment with Docker Compose

To run the Pilotes Order Management Application locally using Docker Compose, follow these steps:

1. Make sure you have Docker installed on your system.

2. Open a terminal or command prompt.

3. Navigate to the project directory where the `Dockerfile` and `docker-compose.yml` files are located.

4. Build and start the containers:
```bash
  docker-compose up -d --build
```
5. Access the application in your web browser at http://localhost:8080.

6. To stop the containers, use the following command:
```bash
  docker-compose down
```
