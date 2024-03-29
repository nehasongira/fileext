package com.stackextend.websocketbackendexample;


import com.stackextend.websocketbackendexample.model.Filess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends MongoRepository<Filess, String> {

    @Query("{'filename': ?0}")
    public Filess findpath(String fileName);

    @Query("{'filename': ?0}")
    public Filess findext(String fileName);


//    @Query(value = "{'fromId': ?0,'toId': ?1,'fileName': ?2}")
}
