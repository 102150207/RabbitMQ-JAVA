package producer.server.controller;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import producer.server.entities.RabbitSetting;
import producer.server.models.RabbiMQModel;
import producer.server.services.RabbitMQServices;
import producer.server.utils.SettingConnectionRabbitMQ;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("queue")
public class RegisterRabitMQController {

    final
    RabbitMQServices rabbitMQServices;

    public RegisterRabitMQController(RabbitMQServices rabbitMQServices) {
        this.rabbitMQServices = rabbitMQServices;
    }

    @RequestMapping(value = "/resger", consumes = "application/json", method =  RequestMethod.POST)
    ResponseEntity<RabbitSetting> registerQueue(@RequestBody RabbiMQModel rabbiMQModel) throws IOException, TimeoutException {
        RabbitSetting rabbitSetting = rabbitMQServices.registerQueue(rabbiMQModel);
        if(rabbitSetting != null && (rabbitMQServices.checkExitExhange(rabbitSetting.getExchange()) || rabbitMQServices.checkEmpty())){

            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection(); // HostName : localhost
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(rabbiMQModel.getExchange(), rabbiMQModel.getTypeExchange(),true,false,null);
            channel.queueDeclare(rabbiMQModel.getQueue(),true,false,false,null);
            channel.queueBind(rabbiMQModel.getQueue(),rabbiMQModel.getExchange(),rabbiMQModel.getRoutingKey());
            channel.close();
            return  new ResponseEntity<>(rabbitSetting, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
    }

    @RequestMapping(value = "/exchanges", method =  RequestMethod.GET)
    ResponseEntity<List<String>> getExchanger(){

        List<String> exchangers = rabbitMQServices.getListExchange();
        if(exchangers != null){
            return  new ResponseEntity<>(exchangers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
    }

    @RequestMapping(value = "/queues",method =  RequestMethod.GET)
    ResponseEntity<List<String>> getQueue(){
        List<String> queues = rabbitMQServices.getListQueue();
        if(queues != null){
            return  new ResponseEntity<>(queues, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
    }

    @RequestMapping(value = "/routing-keys",method =  RequestMethod.GET)
    ResponseEntity<List<String>> getRoutingKeys(){
        List<String> routingKeys = rabbitMQServices.getListRoutingKey();
        if(routingKeys != null){
            return  new ResponseEntity<>(routingKeys, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
    }
}
