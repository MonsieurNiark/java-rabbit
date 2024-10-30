package fr.danglos.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of RabbitMQ used by this java application, to create and persist the queue when the application is started
 */
@Configuration
public class RabbitProducerConfig {

    /** The queue name */
    @Value("${queue_one.name}")
    private String queueOne;

    /** The queue name */
    @Value("${queue_two.name}")
    private String queueTwo;



    /** By using @Bean, Spring will call this method when it starts */
    @Bean
    public Queue createQueueOne() {
        return new Queue(queueOne, true);  // true to create and persiste the queue
    }

    /** By using @Bean, Spring will call this method when it starts */
    @Bean
    public Queue createQueueTwo() {
        return new Queue(queueTwo, true);  // true to create and persiste the queue
    }
}