# mediscren_UI


![uml_ms_ui](https://user-images.githubusercontent.com/42712490/181648275-4b935721-0f9f-480f-af30-3faee5b01ede.png)




Technology and Prerequisites


JAVA 8 JDk


Springboot

Maven

Docker

Installing


Install Java: - https://www.oracle.com/fr/java/technologies/javase-downloads.html


Install Maven - https://maven.apache.org/install.html

Install Docker: - https://www.docker.com/products/docker-desktop

MicroService Detail

Port:8703 - mediscren_Ui:


It is the microservice which manage the user interface. 

It communicate with the 3 other microservices thanks to Feign( HTTP client which facilitates the Api's calls)


To facilitate development, THYMELEAF and BOOSTRAP are used.

Run Application

Install the prerequisites and Technology list above.

1️⃣ Build .jar

Open your terminal and go to each microServices directory and run this command :


▶️ SYNTAX = mvn clean package


thanks to this command you build .jar


you have to build jar to run docker-compose.


Docker


Open your terminal and go to the directory containing docker-compose.yml


▶️ SYNTAX = docker-compose up -d
