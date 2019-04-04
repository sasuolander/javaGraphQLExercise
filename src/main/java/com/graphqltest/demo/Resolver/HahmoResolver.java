package com.graphqltest.demo.Resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqltest.demo.Object.Hahmo;
import com.graphqltest.demo.Repository.HahmoRepository;

public class HahmoResolver implements GraphQLResolver<Hahmo> {

    private HahmoRepository hahmoRepository;

    public HahmoResolver(HahmoRepository hahmoRepository) {
        this.hahmoRepository = hahmoRepository;
    }
}