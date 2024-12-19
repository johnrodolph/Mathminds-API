# MathMinds (Backend)

## Project Description

This project serves as the backend for the MathMinds. It is developed using Java Spring Boot to provide REST API endpoints for interacting with a MySQL database. Check the frontend repository [here](https://github.com/8maaan/MathMinds).

## Tools/Tech Used

![Java](https://skillicons.dev/icons?i=java,spring,mysql) 

## Installation Instructions

### Prerequisites
1. **Java**: Ensure Java is installed on your system. You can download and install it from [here](https://www.java.com/).
2. **MySQL Database**: Ensure MySQL is installed on your system. You can download and install it from [here](https://www.mysql.com/).
3. **Spring Boot**: Ensure Spring Boot is set up and configured on your system. You can find installation instructions [here](https://spring.io/projects/spring-boot).

### Steps to Install and Run the Project
1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/8maaan/MathMinds.git

2. **Ensure MySQL Database and Schema Exist**: 
  - Make sure MySQL is running.
  - Create a database schema named `mathmindsdb` in your MySQL server. If it doesn't exist, you can create it using MySQL Workbench or any other MySQL management tool.

3. **Configure Application Properties**: 
- Open `src/main/resources/application.properties` file.
- Set the username and password for your MySQL database to match your MySQL Workbench credentials:
  ```properties
  spring.datasource.username=<your-username>
  spring.datasource.password=<your-password>

4. **Run the application**: 
- Run the Application.java file in `src/main/resources/Application.java`.

  

