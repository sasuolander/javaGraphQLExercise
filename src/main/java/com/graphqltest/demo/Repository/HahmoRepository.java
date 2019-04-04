package com.graphqltest.demo.Repository;

import com.graphqltest.demo.Object.Hahmo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HahmoRepository extends CrudRepository<Hahmo, Long> {
}