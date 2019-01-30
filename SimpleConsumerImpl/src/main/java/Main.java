


public class Main {

    public static void main(String[] args) {

        // for using by console (4 arguments -> uri,exchangeName,QueueName,routingKey)
        // testFromConsole(args);


        // test by IDE
        test();
    }

    public static void testFromConsole(String[] args) {
        if (args.length != 4) throw new IllegalArgumentException();
        SimpleConsumerImpl simpleConsumer = new SimpleConsumerImpl(args[0], args[1], args[2]);
        simpleConsumer.declareAndBindQueue(args[3], false, false, false, null);
        simpleConsumer.basicConsuming();

    }

    public static void test() {
        SimpleConsumerImpl simpleConsumer = new SimpleConsumerImpl("amqp://guest:guest@localhost:5672/%2f", "TestExchange", "TestQueue");
        simpleConsumer.declareAndBindQueue("TestKey", false, false, false, null);
        simpleConsumer.basicConsuming();
    }


}
