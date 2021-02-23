package com.example.demo.configuration;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {
	
	private static List<Map<String, String>> stus = Arrays.asList(
			ImmutableMap.of("id", "1", "name", "yc", "age", "24", "address", "浦东新区"),
			ImmutableMap.of("id", "2", "name", "Jacky", "age", "24", "address", "浦东新区"),
			ImmutableMap.of("id", "3", "name", "Jason", "age", "25", "address", "闵行区"),
			ImmutableMap.of("id", "4", "name", "Kelly", "age", "23", "address", "青浦区")
	);
	
	public DataFetcher getStuByIdDataFetcher() {
		return dataFetchingEnvironment -> {
			String stuId = dataFetchingEnvironment.getArgument("id");
			return stus.stream().filter(stu -> stu.get("id").equals(stuId))
					.findFirst()
					.orElse(null);
		};
	}
}
