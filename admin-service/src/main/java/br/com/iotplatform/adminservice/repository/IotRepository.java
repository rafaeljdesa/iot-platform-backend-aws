package br.com.iotplatform.adminservice.repository;

import br.com.iotplatform.adminservice.entity.Iot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IotRepository extends MongoRepository<Iot, String> {
}
