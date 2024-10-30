# My Challenges

This repository contains various coding challenges I have solved, demonstrating my problem-solving skills and understanding of software development concepts. Each challenge is located in its respective folder.

## Table of Contents

1. [PazhouhandeRah-demo](#PazhouhandeRah-demo)





## PazhouhandeRah-demo

#### Overview
This challenge is about apply some metrics to get valuable information about advertising sector.

#### Technologies Used
- **Java**: version : 11
- **Spring Boot**: version : 2.7.13
- **Apache Spark**: version : 3.5.3
- **MySQL**: version : 8.0.33

#### Setup
Follow these instructions to set up the project locally:
1. Clone the repository:
   ```bash
   git clone https://github.com/akar-777/My-challenges.git
2. Navigate to the project directory:
   ```bash
   cd My-challenges/PazhouhandeRah-demo

3. Install dependencies:
   ```bash
   mvn install

4. Configure the database connection in application.properties

5. Execute the content of the schema.sql file located in project resources folder into the MySQL script window manually.

6. Start the application by running below command on the commandline(terminal)
   ```bash
   mvn spring-boot:run

7. Access the application and get specified metrics, copy and paste below url
   ```bash
   http://localhost:8080/metrics
   OR
   curl http://localhost:8080/metrics

8. Access the application and get advertisment reccomendation, copy and paste below url metrics, copy and paste below url
```bash
   http://localhost:8080/recommendation
   OR
   curl http://localhost:8080/recommendation

