package com.edwardoboh.productservicebcommerce.repository;

import com.edwardoboh.productservicebcommerce.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
}
