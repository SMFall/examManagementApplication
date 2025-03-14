package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.User;
import org.amu.examManagement.repositories.QuizRepository;
import org.amu.examManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
