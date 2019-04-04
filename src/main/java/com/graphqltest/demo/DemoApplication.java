package com.graphqltest.demo;

import com.graphqltest.demo.Exception.GraphQLErrorAdapter;
import com.graphqltest.demo.Object.Hahmo;
import com.graphqltest.demo.Repository.HahmoRepository;
import com.graphqltest.demo.Resolver.HahmoResolver;
import com.graphqltest.demo.Resolver.Mutation;
import com.graphqltest.demo.Resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public HahmoResolver hahmoResolver(HahmoRepository hahmoRepository) {
        return new HahmoResolver(hahmoRepository);
    }

    @Bean
    public Query query(HahmoRepository hahmoRepository) {
        return new Query(hahmoRepository);
    }

    @Bean
    public Mutation mutation(HahmoRepository hahmoRepository) {
        return new Mutation(hahmoRepository);
    }

    @Bean
    public CommandLineRunner demo(HahmoRepository hahmoRepository) {
        return (args) -> {
            ArrayList<Hahmo> list = new ArrayList<Hahmo>();
            Collections.addAll(list,
                    new Hahmo.Builder().setNimi("tet1").setKoko("100").setIka("106").build(),
                    new Hahmo.Builder().setNimi("tet2").setKoko("150").setIka("104").build(),
                    new Hahmo.Builder().setNimi("tet3").setKoko("106").setIka("102").build(),
                    new Hahmo.Builder().setNimi("tet4").setKoko("104").setIka("107").build(),
                    new Hahmo.Builder().setNimi("tet5").setKoko("108").setIka("103").build());
            hahmoRepository.saveAll(list);
        };
    }
}