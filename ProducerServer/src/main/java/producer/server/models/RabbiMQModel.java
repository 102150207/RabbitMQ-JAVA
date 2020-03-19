package producer.server.models;

import producer.server.entities.RabbitSetting;

public class RabbiMQModel {
    private String exchange;
    private String queue;
    private String typeExchange;
    private String routingKey;

    public RabbiMQModel() {

    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getTypeExchange() {
        return typeExchange;
    }

    public void setTypeExchange(String typeExchange) {
        this.typeExchange = typeExchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public RabbitSetting setRabbitSetting(RabbiMQModel rabbiMQModel){
        RabbitSetting rabbitSetting = new RabbitSetting();
        rabbitSetting.setExchange(rabbiMQModel.exchange);
        rabbitSetting.setTypeExchange(rabbiMQModel.typeExchange);
        rabbitSetting.setQueue(rabbiMQModel.queue);
        rabbitSetting.setRoutingKey(rabbiMQModel.routingKey);
        return  rabbitSetting;
    }
}
