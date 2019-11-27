package com.stackextend.websocketbackendexample;


import com.stackextend.websocketbackendexample.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<File, String> {



}
