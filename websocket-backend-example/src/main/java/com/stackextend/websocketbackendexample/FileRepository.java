package com.stackextend.websocketbackendexample;


import com.stackextend.websocketbackendexample.model.Filess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends MongoRepository<Filess, String> {

    @Query(value = "{'fileName': ?0}")
    Filess findext(String filename);

//    @Query(value = "{'fromId': ?0,'toId': ?1,'fileName': ?2}")
}
