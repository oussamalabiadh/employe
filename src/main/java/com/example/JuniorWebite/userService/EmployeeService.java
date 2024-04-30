package com.example.JuniorWebite.userService;
import com.example.JuniorWebite.Entity.Employee;
import com.example.JuniorWebite.Entity.Project;
import com.example.JuniorWebite.userRepository.ProjectRepository;
import com.example.JuniorWebite.userRepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Employee addProjectToEmployee(Long employeeId, Project project) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        project.setEmployee(employee);
        projectRepository.save(project);

        employee.getProjects().add(project);
        return employeeRepository.save(employee);
    }
}
