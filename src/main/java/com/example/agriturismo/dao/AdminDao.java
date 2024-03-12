package com.example.agriturismo.dao;

import com.example.agriturismo.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminDao extends CrudRepository<Admin, Integer> {
    Admin findByUsernameAndPassword(String username, String password);
}
