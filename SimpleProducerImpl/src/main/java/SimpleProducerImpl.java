import org.apache.commons.lang3.RandomStringUtils;
import org.testTask.AbstractRabbitProducer;

public class SimpleProducerImpl extends AbstractRabbitProducer {

        public SimpleProducerImpl(String uri, String exchange) {
            super(uri, exchange);
        }

        public void sendSomeRandomMessages(int amountOfMessages, String routingKey) {
            for (int i = 0; i < amountOfMessages; i++) {
                String message = this.generateRandomString();
                this.send(routingKey, message.getBytes(), null);
                System.out.println(" [x] Sent '" + message + "'");
            }
        }

        public String generateRandomString() {
            return RandomStringUtils.randomAlphabetic(20);
        }

    }








