package producer.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import producer.server.entities.RabbitSetting;
import producer.server.models.RabbiMQModel;
import producer.server.repository.RabbitMQRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitMQServices {
    @Autowired
    RabbitMQRepository rabbitMQRepository;

    public RabbitSetting registerQueue(RabbiMQModel rabbiMQModel) {
        RabbitSetting rabbitSetting = new RabbiMQModel().setRabbitSetting(rabbiMQModel);
        if(rabbitSetting !=null){
            rabbitMQRepository.save(rabbitSetting);
            return rabbitSetting;
        }
        return null;
    }

    public List<String> getListExchange (){
        List<RabbitSetting> rabbitMQs = rabbitMQRepository.findAll();
        if(!rabbitMQs.isEmpty()){
            List<String> exchanges = new ArrayList<>();
            for(RabbitSetting item : rabbitMQs){
                exchanges.add(item.getExchange());
            }
            return exchanges;
        }
        return  null;
    }

    public List<String> getListQueue (){
        List<RabbitSetting> rabbitMQs = rabbitMQRepository.findAll();
        if(!rabbitMQs.isEmpty()){
            List<String> queues = new ArrayList<>();
            for(RabbitSetting item : rabbitMQs){
                queues.add(item.getQueue());
            }
            return queues;
        }
        return  null;
    }

    public List<String> getListRoutingKey (){
        List<RabbitSetting> rabbitMQs = rabbitMQRepository.findAll();
        if(!rabbitMQs.isEmpty()){
            List<String> routingKeys = new ArrayList<>();
            for(RabbitSetting item : rabbitMQs){
                routingKeys.add(item.getRoutingKey());
            }
            return routingKeys;
        }
        return  null;
    }

    public boolean checkExitExhange(String queue){
        List<String> rabbitMQs = getListExchange();
        if(rabbitMQs.contains(queue)){
            return true;
        }
        return false;
    }

    public boolean checkEmpty(){
        List<RabbitSetting> rabbitMQs = rabbitMQRepository.findAll();
        if(rabbitMQs.isEmpty()){
            return true;
        }
        return false;
    }
}
