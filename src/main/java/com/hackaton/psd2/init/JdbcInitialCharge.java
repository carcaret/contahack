/**
 * 
 */
package com.hackaton.psd2.init;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * @author cesarrodriguezmedina
 * Initialization of database
 */
@Configuration
public class JdbcInitialCharge implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcInitialCharge.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... strings) throws Exception {

		LOG.info("Creating tables");
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		String schemaLocation = "/CONTAHACK.sql";
		populator.addScript(new ClassPathResource(schemaLocation));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, this.dataSource);
	}

}
