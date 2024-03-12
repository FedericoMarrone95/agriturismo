package com.example.agriturismo.service;

import com.example.agriturismo.dao.AdminDao;
import com.example.agriturismo.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean adminLogin(String username, String password, HttpSession session) {
        Admin admin = adminDao.findByUsernameAndPassword(username, password);
        if(admin != null){
            session.setAttribute("admin", admin);
            return true;
        }
        return false;
    }
}
