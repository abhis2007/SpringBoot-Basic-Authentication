package com.example.Spring.Boot.Basic.Authentication.Repository;

import com.example.Spring.Boot.Basic.Authentication.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
