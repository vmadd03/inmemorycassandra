/*package com.safeway.j4u.inmemorycassandra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.UUID;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.safeway.j4u.inmemorycassandra.model.Book;
import com.safeway.j4u.inmemorycassandra.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BookControllerTest {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	Cluster cluster;

	public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS testkeyspace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' };";
	public static final String KEYSPACE_ACTIVATE_QUERY = "USE testkeyspace;";
	public static final String DATA_TABLE_NAME = "book";

	@Before
	public void startCassandraEmbedded()
			throws ConfigurationException, TTransportException, IOException, InterruptedException {

		Session session = cluster.connect();
		session.execute(KEYSPACE_CREATION_QUERY);
		session.execute(KEYSPACE_ACTIVATE_QUERY);
		bookRepository.createTable();
	}

	@Test
	public void testInsertAndFetch()
			throws InterruptedException, TTransportException, ConfigurationException, IOException {

		final String uri = "http://localhost:8080/book/insertBook";
		UUID uuid = UUID.randomUUID();
		String title = "nav rest";
		String publisher = "nav rest";
		Book book = new Book(uuid, title, publisher);

		RestTemplate restTemplate = new RestTemplate();
		Book savedBook = restTemplate.postForObject(uri, book, Book.class);

		System.out.println(" result publisher: "+savedBook.getPublisher());

		 assertEquals(savedBook.getTitle(),title);
		 assertEquals(savedBook.getPublisher(),publisher);
	}

}
*/