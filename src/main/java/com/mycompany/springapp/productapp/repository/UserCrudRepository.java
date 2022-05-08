package com.mycompany.springapp.productapp.repository;

import com.mycompany.springapp.productapp.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends CrudRepository<UserModel, Long> {

    Optional<UserModel> findByEmailAndPassword(String email, String password);

    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
