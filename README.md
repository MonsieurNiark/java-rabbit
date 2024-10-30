# HOW TO RUN IT
Make sure you have a JDK installed on your computer. JDK 21 is used on this app.
Maven is needed if you need to compile the app.
For the first time, launch the producer before the consumer. It will create the queues if there are not existing.

## FROM SOURCE CODE
To execute both applications without packaging them, you can do it with maven by executing this command 

`mvn spring-boot:run -pl rabbit-producer`

and this one in another terminal

`mvn spring-boot:run -pl rabbit-consumer`

## FROM JAR FILE
If the application is not compiled in jar, you have to do it first. Open a terminal into the parent folder “java-rabbit” and execute this command

`mvn clean install package -pl rabbit-producer -am`

`mvn clean install package -pl rabbit-consumer -am`

You will find the result in java-rabbit\rabbit-producer\target\rabbit-producer-1.0-SNAPSHOT.jar

You will find the result in java-rabbit\rabbit-consumer\target\rabbit-consumer-1.0-SNAPSHOT.jar

And then launch them with Java 

`java -jar rabbit-producer-1.0-SNAPSHOT.jar`

`java -jar rabbit-consumer-1.0-SNAPSHOT.jar`
