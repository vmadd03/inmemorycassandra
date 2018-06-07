package com.safeway.j4u.inmemorycassandra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.UUID;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.safeway.j4u.inmemorycassandra.model.Book;
import com.safeway.j4u.inmemorycassandra.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class InmemorycassandraApplicationTests {

	public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS testkeyspace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' };";
	public static final String KEYSPACE_ACTIVATE_QUERY = "USE testKeySpace;";

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private Cluster cluster;

	@Before
	public void startCassandraEmbedded()
			throws ConfigurationException, TTransportException, IOException, InterruptedException {
	    Session session = cluster.connect();
		session.execute(KEYSPACE_CREATION_QUERY);
		session.execute(KEYSPACE_ACTIVATE_QUERY);
	}

	@After
	public void stopCassandraEmbedded() {
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
	}

	@Test
	public void testInsertAndFetch()
			throws InterruptedException, TTransportException, ConfigurationException, IOException {

		bookRepository.createTable();

		String title = " demo test";
		String publisher = "demo test";
		UUID id = UUID.randomUUID();
		// save a record
		bookRepository.save(id,title, publisher);
		// fetch the same record
		Book savedBook = (Book) this.bookRepository.findByTitleAndPublisher(title, publisher).iterator().next();
		assertEquals(savedBook.getId(), id);
		assertEquals(savedBook.getTitle(), title);
		assertEquals(savedBook.getPublisher(), publisher);
	}

}