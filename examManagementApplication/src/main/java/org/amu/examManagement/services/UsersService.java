package org.amu.examManagement.services;

import org.amu.examManagement.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    public Optional<Users> getUsers(Long id);

    public List<Users> getAllUsers();

    public void saveUsers(Users users);

    public void saveAllUsers(List<Users> allUsers);

    public List<Users> getAllUsersWithRole(String role);

    public Optional<Users> findByUsername(String username);

    public void deleteUsers(Long id);
}
