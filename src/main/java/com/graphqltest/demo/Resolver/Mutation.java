package com.graphqltest.demo.Resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqltest.demo.Exception.HahmoEiLoydy;
import com.graphqltest.demo.Object.Hahmo;
import com.graphqltest.demo.Repository.HahmoRepository;

public class Mutation implements GraphQLMutationResolver {
    private HahmoRepository hahmoRepository;

    public Mutation(HahmoRepository hahmoRepository) {
        this.hahmoRepository = hahmoRepository;
    }

    public Hahmo newHahmo(Long id, String nimi, String ika, String koko) {

        Hahmo hahmo = new Hahmo.Builder().
                setId(id).
                setNimi(nimi).
                setIka(ika).
                setKoko(koko).build();
        hahmoRepository.save(hahmo);
        return hahmo;

    }

    public boolean deleteHahmo(Long id) {
        hahmoRepository.deleteById(id);
        return true;
    }

    public Hahmo updateHahmo(String nimi, Long id) {
        Hahmo hahmo = hahmoRepository.findById(id).get();
        if (hahmo == null) {
            throw new HahmoEiLoydy("The hahmo to be updated was not found", id);
        }

        hahmo.setNimi(nimi);
        hahmoRepository.save(hahmo);
        return hahmo;

    }
}