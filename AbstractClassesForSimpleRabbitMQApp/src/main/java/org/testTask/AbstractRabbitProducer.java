package org.testTask;

import com.rabbitmq.client.AMQP;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
// the abstract producer class
abstract public class AbstractRabbitProducer extends AbstractSimpleRabbitMessageService {

    public AbstractRabbitProducer(String uri, String exchange) {
        super(uri);
        this.setExchange(exchange);
        declareExchange(exchange);
    }

    public AbstractRabbitProducer(String exchange) {
        super();
        this.setExchange(exchange);
        declareExchange(exchange);
    }


    // encapsulate a send logic
    public void send(String routingKey, byte[] message, AMQP.BasicProperties properties) {
        try {
            this.getChannel().basicPublish(this.getExchange(), routingKey, properties, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
