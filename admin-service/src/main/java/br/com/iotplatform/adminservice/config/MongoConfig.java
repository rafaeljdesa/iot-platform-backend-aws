package br.com.iotplatform.adminservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.iotplatform.adminservice.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${mongo.databaseName}")
    private String databaseName;

    @Value("${mongo.connectionString}")
    private String connectionString;

    @Value("${mongo.basePackage}")
    private String basePackage;

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton(this.basePackage);
    }

}
