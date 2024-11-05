package com.festival.Service;

import com.festival.Entity.Login;
import com.festival.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean validateLogin(String userId, String password) {
        Login login = loginRepository.findByUserId(userId);
        return login != null && login.getPassword().equals(password);
    }
}
