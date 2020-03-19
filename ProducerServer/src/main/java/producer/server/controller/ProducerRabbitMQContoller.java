package producer.server.controller;

import com.rabbitmq.client.Channel;
import com.sun.xml.fastinfoset.EncodingConstants;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import producer.server.models.MessageModel;
import producer.server.utils.SettingConnectionRabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/send-message")
public class ProducerRabbitMQContoller {
    private final String messageSender = "VO VAN TRINH";
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String sendMessage(@RequestBody MessageModel messageModel) throws IOException, TimeoutException {
        Channel channel = SettingConnectionRabbitMQ.setConnection();
        channel.basicPublish("Event-SendMassage",
                "send-message",
                null,
                 messageSender.getBytes());
        //
        channel.close();
        return "Successefully !!";
    }
}
