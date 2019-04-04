package com.graphqltest.demo.Resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqltest.demo.Object.Hahmo;
import com.graphqltest.demo.Repository.HahmoRepository;

public class Query implements GraphQLQueryResolver {

    private HahmoRepository hahmoRepository;

    public Query(HahmoRepository hahmoRepository) {
        this.hahmoRepository = hahmoRepository;
    }

    public Iterable<Hahmo> findAllHahmo() {
        return hahmoRepository.findAll();
    }

    public long countHahmo() {
        return hahmoRepository.count();
    }

    /* public Mutation mutation(HahmoRepository hahmoRepository) {
        return new Mutation(hahmoRepository);
    }
     public HahmoResolver(HahmoRepository hahmoRepository) {
        this.hahmoRepository = hahmoRepository;
    }
    */

}
