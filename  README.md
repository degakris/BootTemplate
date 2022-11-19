Application has return using spring boot framework;
This repo contains guest booking service code .

Application Port: 8989 
//you can change port using server.port via application.xml

Health check endPoint : http://localhost:8989/actuator/health


Onboarding :
1) Install any ide and use vesrion 16 of java 
2)download maven
   
Run command :
1) mvn clean install
2) mvn clean install -DskipTests 
   3)start application using spring boot application
   
   
Database:
1)we are using in-memory h2 db for storing  entity values.This is
volatile.Data will be erased once server is re-started.

