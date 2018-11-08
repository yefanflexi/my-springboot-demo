package com.example.demo.repository;

import com.example.demo.model.AyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AyRoleRepository extends JpaRepository<AyRole,String> {

}
