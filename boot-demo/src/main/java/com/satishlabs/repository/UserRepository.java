package com.satishlabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satishlabs.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    Optional<UserEntity> findByIdAndStatus(Integer id, UserEntity.Status status);

}
