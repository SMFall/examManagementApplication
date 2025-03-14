package org.amu.examManagement.repositories;

import org.amu.examManagement.model.Quiz;
import org.amu.examManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UserRepository extends JpaRepository<User, Long> {

}
