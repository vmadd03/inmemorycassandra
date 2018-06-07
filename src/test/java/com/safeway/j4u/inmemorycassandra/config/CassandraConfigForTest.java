package com.safeway.j4u.inmemorycassandra.config;

import java.io.IOException;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

import com.datastax.driver.core.Cluster;

@Configuration
public class CassandraConfigForTest {
 
    @Bean
    public Cluster cluster() throws ConfigurationException, TTransportException, IOException, InterruptedException {
    	EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		Cluster cluster = new Cluster.Builder().addContactPoints("127.0.0.1").withPort(9142).build();
        return cluster;
    }
 
    @Bean
    public CassandraMappingContext cassandraMapping() 
      throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}