package fr.danglos.rabbit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

/** Spring Boot Application to run a command line app to receive user input and send it to a RabbitMQ queue */
@SpringBootApplication
public class RabbitProducerApplication {

    @Value("${queue_one.name}")
    private String queueNameOne;

    @Value("${queue_two.name}")
    private String queueNameTwo;

    private final RabbitTemplate rabbitTemplate;

    /**
     * Constructor
     * @param rabbitTemplate rabbitTemplate
     */
    public RabbitProducerApplication(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Launcher of the application
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }

    /**
     * The runner of the command line context
     * @return the CommandLineRunner object
     */
    @Bean
    public CommandLineRunner runner() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type a message to send to RabbitMQ. Type 'exit' to quit.");

            while (true) {
                System.out.print("Message: ");
                // Read the message from the command line
                String message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) { // if typing exit, stop the application
                    System.out.println("Exiting producer...");
                    break;
                }
                try {
                    // Send the message to the Rabbit queues
                    System.out.println(" [x] Sent '" + message + "' to queue one");
                    rabbitTemplate.convertAndSend(queueNameOne, message);

                    System.out.println(" [x] Sent '" + message + "' to queue two");
                    rabbitTemplate.convertAndSend(queueNameTwo, message);
                } catch (AmqpException e) { // If there is any error, print it
                    System.err.println("Connection error to RabbitMQ : " + e.getMessage());
                }

            }
            scanner.close(); // Stop the application
        };
    }
}