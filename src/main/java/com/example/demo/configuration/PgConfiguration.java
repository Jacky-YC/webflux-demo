//package com.example.demo.configuration;
//
//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
//import io.r2dbc.spi.ConnectionFactoryOptions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.core.DatabaseClient;
//
//import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;
//import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
//import static io.r2dbc.spi.ConnectionFactoryOptions.HOST;
//import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
//import static io.r2dbc.spi.ConnectionFactoryOptions.PORT;
//import static io.r2dbc.spi.ConnectionFactoryOptions.USER;
//
///**
// * @author: YeCheng
// * @date: 2020/11/10
// * @time: 17:20
// */
//@Configuration
//public class PgConfiguration extends AbstractR2dbcConfiguration {
//
////	@Bean
////	public DatabaseClient databaseClient() {
////		ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
////				.option(DRIVER, "postgresql")
////				.option(HOST, "localhost")
////				.option(PORT, 5432)
////				.option(USER, "sa")
////				.option(PASSWORD, "123456")
////				.option(DATABASE, "store")
////				.build());
////		DatabaseClient client = DatabaseClient.create(connectionFactory);
////		return client;
////	}
//
////	@Override
////	public ConnectionFactory connectionFactory() {
////		return ConnectionFactories.get(ConnectionFactoryOptions.builder()
////				.option(DRIVER, "postgresql")
////				.option(HOST, "localhost")
////				.option(PORT, 5432)
////				.option(USER, "sa")
////				.option(PASSWORD, "123456")
////				.option(DATABASE, "store")
////				.build());
////	}
//}
