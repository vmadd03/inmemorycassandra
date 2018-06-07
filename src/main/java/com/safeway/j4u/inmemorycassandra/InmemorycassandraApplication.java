	package com.safeway.j4u.inmemorycassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;

@SpringBootApplication/*(exclude={CassandraDataAutoConfiguration.class})*/
public class InmemorycassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmemorycassandraApplication.class, args);
	}
}
