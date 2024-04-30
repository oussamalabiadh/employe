package com.example.JuniorWebite.userRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JuniorWebite.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{


}
