package org.testTask;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

// Main abstract class, encapsulates the common behavior of Producer and Consumer
@Getter
@Setter
abstract public class AbstractSimpleRabbitMessageService {

    //type is topic to simplify the application for the first time, can cause issues if you try to use already existing exchange with another type but the same name
    private ConnectionFactory factory;
    private Channel channel;
    private Connection connection;
    private String exchange;
    private String type = "topic";

    // Constructor
    public AbstractSimpleRabbitMessageService() {

        //Create a connection factory
        this.factory = new ConnectionFactory();

        //getting a connection
        try {
            this.connection = this.factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        //creating a channel
        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor
    public AbstractSimpleRabbitMessageService(String uri) {

        //Create a connection factory
        this.factory = new ConnectionFactory();

        //Set properties
        try {
            this.factory.setUri(uri);
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        //getting a connection
        try {
            this.connection = this.factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        //creating a channel
        try {
            this.channel = this.connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    protected void declareExchange(String exchange)  {
        try {
            this.getChannel().exchangeDeclare(exchange,this.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close method cause of try without resources, so channel and connection must be closed after work will be done
    public void close() {
        try {
            this.channel.close();
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

        try {
            this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
