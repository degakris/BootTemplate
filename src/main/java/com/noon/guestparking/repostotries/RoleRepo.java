package com.noon.guestparking.repostotries;

import com.noon.guestparking.models.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends MongoRepository<Roles, String> {
}
