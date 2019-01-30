import org.testTask.AbstractRabbitConsumer;


public class SimpleConsumerImpl extends AbstractRabbitConsumer {

    // only constructor needed in my simple application
    public SimpleConsumerImpl(String uri, String exchange, String queueName) {
        super(uri, exchange, queueName);
    }
}
