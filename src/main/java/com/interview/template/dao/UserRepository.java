package com.interview.template.dao;

import com.interview.template.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByUsername(String username);

    public Optional<UserEntity> findByEmail(String email);

    public List<UserEntity> findByUsernameContainingIgnoreCase(String username);

}
