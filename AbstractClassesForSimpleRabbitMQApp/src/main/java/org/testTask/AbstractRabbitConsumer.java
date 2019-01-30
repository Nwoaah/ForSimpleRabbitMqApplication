package org.testTask;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// the abstract consumer class
abstract public class AbstractRabbitConsumer extends AbstractSimpleRabbitMessageService {

    private String queueName;

    public AbstractRabbitConsumer(String uri, String exchange, String queueName) {
        super(uri);
        this.queueName = queueName;
        this.setExchange(exchange);
        declareExchange(exchange);
    }

    public AbstractRabbitConsumer(String exchange, String queueName) {
        super();
        this.queueName = queueName;
        this.setExchange(exchange);
        declareExchange(exchange);
    }

    //logic for declare que and bind it
    public void declareAndBindQueue(String routingKey,
                                    boolean durable,
                                    boolean exclusive,
                                    boolean autoDelete,
                                    java.util.Map<String, Object> arguments) {

        try {
            this.getChannel().queueDeclare(queueName, durable, exclusive, autoDelete, arguments);
            this.getChannel().queueBind(queueName, this.getExchange(), routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reading from the que, printing into stdout
    public void basicConsuming() {
        try {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" +
                        delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };
            this.getChannel().basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

