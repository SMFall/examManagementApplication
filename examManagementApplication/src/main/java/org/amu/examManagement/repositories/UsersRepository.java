package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public  interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.role = ?1")
    List<Users> findAllUsersWithRole(String role);

    Optional<Users> findByUsername(String username);
}
