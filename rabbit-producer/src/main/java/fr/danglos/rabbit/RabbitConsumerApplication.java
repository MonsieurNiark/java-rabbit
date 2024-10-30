package fr.danglos.rabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.Header;

/** Spring Boot Application to run a prompt app to receive messages from a RabbitMQ queue */
@SpringBootApplication
public class RabbitConsumerApplication {

    /**
     * Launcher of the application
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApplication.class, args);
    }

    /**
     *  The @RabbitListener annotation is used to call a specific and asynchronous method when a message is received
     *  When launching the application, this method will receive only the messages it didn't received before.
     */
    @RabbitListener(queues = {"${queue_one.name}","${queue_two.name}"})
    public void receiveMessageAllQueues(String message, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue) {
        System.out.println(" [x] Received message on queue '" + queue + " : " + message + "'");

        // You can also add a condition on String queue
        // Or you can create a single method annotated with @RabbitListener with a single value in the "queues" parameter for each queues
    }


}