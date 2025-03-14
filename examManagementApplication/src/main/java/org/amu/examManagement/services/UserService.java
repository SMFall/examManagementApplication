package org.amu.examManagement.services;

import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public void saveUser(User user);

}
