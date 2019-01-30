public class Main {

    public static void main(String[] args) {
        SimpleProducerImpl simpleProducer = new SimpleProducerImpl("amqp://guest:guest@localhost:5672/%2f", "TestExchange");
        simpleProducer.sendSomeRandomMessages(15,"TestKey");
        simpleProducer.close();
    }


}
