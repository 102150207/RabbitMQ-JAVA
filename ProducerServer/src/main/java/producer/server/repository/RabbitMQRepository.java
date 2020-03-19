package producer.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import producer.server.entities.RabbitSetting;

@Repository
public interface RabbitMQRepository extends JpaRepository<RabbitSetting, String> {

}
