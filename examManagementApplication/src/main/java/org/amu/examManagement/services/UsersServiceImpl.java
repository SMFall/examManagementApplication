package org.amu.examManagement.services;

import jakarta.transaction.Transactional;
import org.amu.examManagement.model.Users;
import org.amu.examManagement.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Optional<Users> getUsers(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveUsers(Users users) {
        usersRepository.save(users);
    }

    @Override
    @Transactional
    public void saveAllUsers(List<Users> allUsers) {
        usersRepository.saveAll(allUsers);
    }

    @Override
    public List<Users> getAllUsersWithRole(String role) {
        return usersRepository.findAllUsersWithRole(role);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
