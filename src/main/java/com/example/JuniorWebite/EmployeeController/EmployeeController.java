package com.example.JuniorWebite.EmployeeController;
import com.example.JuniorWebite.Entity.Project;
import com.example.JuniorWebite.userRepository.ProjectRepository;
import com.example.JuniorWebite.userService.EmployeeService;
import com.example.JuniorWebite.Entity.Employee;
import com.example.JuniorWebite.userRepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.example.JuniorWebite.Dto.EmployeeDTO;
import com.example.JuniorWebite.Dto.ProjectDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeService employeeService;

    // Endpoint pour créer un employé
    @PostMapping("add/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        try {
            employeeRepository.save(employee);
            Map<String, String> response = new HashMap<>();
            response.put("message", "success");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/add/employees/{employeeId}/projects")
    public ResponseEntity<Object> addProjectToEmployee(@PathVariable Long employeeId, @RequestBody Project project) {
        try {
            Employee updatedEmployee = employeeService.addProjectToEmployee(employeeId, project);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "success");
            response.put("employee", updatedEmployee);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Endpoint pour obtenir un employé par ID
    @GetMapping("/get/employees/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
            return ResponseEntity.ok().body(employee);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint pour mettre à jour un employé
    @PutMapping("/update/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setNumero(employeeDetails.getNumero());
            employee.setImagePath1(employeeDetails.getImagePath1());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPosition(employeeDetails.getPosition());
            employee.setEvaluation(employeeDetails.getEvaluation());

            Employee updatedEmployee = employeeRepository.save(employee);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "success");
            response.put("employee", updatedEmployee);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Endpoint pour supprimer un employé
    @DeleteMapping("/delete/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
            employeeRepository.delete(employee);
            Map<String, String> response = new HashMap<>();
            response.put("message", "success");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint pour supprimer un projet d'un employé
    @DeleteMapping("/delete/employees/{employeeId}/projects/{projectId}")
    public ResponseEntity<Object> deleteProjectFromEmployee(@PathVariable Long employeeId, @PathVariable Long projectId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

            if (!employee.getProjects().contains(project)) {
                throw new IllegalArgumentException("Project with id " + projectId + " does not belong to employee with id " + employeeId);
            }

            employee.getProjects().remove(project);
            project.setEmployee(null);
            projectRepository.delete(project);

            Map<String, String> response = new HashMap<>();
            response.put("message", "success");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Endpoint pour mettre à jour un projet d'un employé
    @PutMapping("update/employees/{employeeId}/projects/{projectId}")
    public Project updateProjectForEmployee(@PathVariable Long employeeId, @PathVariable Long projectId, @RequestBody Project projectDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        if (!employee.getProjects().contains(project)) {
            throw new IllegalArgumentException("Project with id " + projectId + " does not belong to employee with id " + employeeId);
        }

        project.setName(projectDetails.getName());
        project.setGithubLink(projectDetails.getGithubLink());
        project.setDescription(projectDetails.getDescription());
        project.setImage1(projectDetails.getImage1());
        project.setImage2(projectDetails.getImage2());
        project.setImage3(projectDetails.getImage3());
        project.setImage4(projectDetails.getImage4());

        return projectRepository.save(project);
    }

    @GetMapping("/getall/employees/{employeeId}/projects")
    public List<ProjectDTO> getAllProjectsForEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + employeeId));

        List<ProjectDTO> projectDTOs = employee.getProjects().stream()
                .map(project -> {
                    ProjectDTO projectDTO = new ProjectDTO();
                    projectDTO.setId(project.getId());
                    projectDTO.setName(project.getName());
                    projectDTO.setImage1(project.getImage1());
                    projectDTO.setImage2(project.getImage2());
                    projectDTO.setImage3(project.getImage3());
                    projectDTO.setImage4(project.getImage4());

                    projectDTO.setDescription(project.getDescription());
                    projectDTO.setGithubLink(project.getGithubLink());

                    return projectDTO;
                })
                .collect(Collectors.toList());

        return projectDTOs;
    }


    @GetMapping("/getall/projects")
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());
            projectDTO.setImage1(project.getImage1());
            projectDTO.setImage2(project.getImage2());
            projectDTO.setImage3(project.getImage3());
            projectDTO.setImage4(project.getImage4());

            projectDTO.setDescription(project.getDescription());
            projectDTO.setGithubLink(project.getGithubLink());

            // Get the employee details associated with this project
            Employee employee = project.getEmployee();
            if (employee != null) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(employee.getId());
                employeeDTO.setFirstName(employee.getFirstName());
                employeeDTO.setLastName(employee.getLastName());
                employeeDTO.setEmail(employee.getEmail());
                employeeDTO.setNumero(String.valueOf(employee.getNumero()));
                employeeDTO.setPosition(employee.getPosition());
                employeeDTO.setEvaluation(String.valueOf(employee.getEvaluation()));
                employeeDTO.setImagePath1(employee.getImagePath1());

                projectDTO.setEmployee(employeeDTO);
            }

            projectDTOs.add(projectDTO);
        }

        return projectDTOs;
    }



    @GetMapping("/getall/employees")
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setId(employee.getId());
                    employeeDTO.setFirstName(employee.getFirstName());
                    employeeDTO.setLastName(employee.getLastName());
                    employeeDTO.setEmail(employee.getEmail());
                    employeeDTO.setEvaluation(String.valueOf(employee.getEvaluation()));
                    employeeDTO.setNumero(String.valueOf(employee.getNumero()));
                    employeeDTO.setPosition(employeeDTO.getPosition());
                    employeeDTO.setImagePath1(employee.getImagePath1());
                    employeeDTO.setCreationDate(employee.getCreationDate());
                    employeeDTO.setNombreDeProjet(employee.getProjects().size()); // Définition de la valeur de nombreDeProjet

                    return employeeDTO;
                })
                .collect(Collectors.toList());

        return employeeDTOs;
    }
}

