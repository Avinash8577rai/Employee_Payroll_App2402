package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;
import com.bridgelabz.employeepayroll.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {
        private List<EmployeeDTO> employeeDTOList = new ArrayList<>();


        private Map<Integer, String> employees = new HashMap<>();
        @Autowired
        private IEmployeeService employeeService;

        @PostMapping
        public String createEmployee(@RequestParam int id, @RequestParam String name) {
            employees.put(id, name);
            return "Employee added successfully!";
        }

        @GetMapping("/{id}")
        public String getEmployee(@PathVariable int id) {
            return employees.getOrDefault(id, "Employee not found!");
        }

        @PutMapping("/{id}")
        public String updateEmployee(@PathVariable int id, @RequestParam String name) {
            if (employees.containsKey(id)) {
                employees.put(id, name);
                return "Employee updated successfully!";
            }
            return "Employee not found!";
        }

        @DeleteMapping("/{id}")
        public String deleteEmployee(@PathVariable int id) {
            if (employees.remove(id) != null) {
                return "Employee deleted successfully!";
            }
            return "Employee not found!";
        }


        // ========== DTO Handling (First) ==========

        @PostMapping("/dto")
        public ResponseEntity<String> createEmployeeDTO(@RequestBody EmployeeDTO employeeDTO) {
            employeeDTOList.add(employeeDTO);
            return ResponseEntity.ok("Employee added using DTO: " + employeeDTO.getName() + ", Salary: " + employeeDTO.getSalary());
        }

        @GetMapping("/dto/sample")
        public EmployeeDTO getSampleEmployeeDTO() {
            return new EmployeeDTO("Sample Employee", 50000.0);
        }

        @GetMapping("/dto/all")
        public List<EmployeeDTO> getAllEmployeesDTO() {
            return employeeDTOList;
        }
        // ========== New Code for DTO Handling ==========

        @PostMapping("/service/dto")
        public ResponseEntity<String> createEmployeesDTO(@RequestBody EmployeeDTO employeeDTO) {
            EmployeeDTO savedEmployee = employeeService.addEmployeeDTO(employeeDTO);
            return ResponseEntity.ok("Employee added using DTO: " + savedEmployee.toString());
        }

        @GetMapping("/service/dto")
        public ResponseEntity<EmployeeDTO> getSampleEmployeesDTO() {
            return ResponseEntity.ok(employeeService.getSampleEmployeeDTO());
        }

        @GetMapping("/service/dto/all")
        public List<EmployeeDTO> getAllEmployeessDTO() {
            return employeeService.getAllEmployeesDTO();
        }

        // ========== New Code for Employee Entity Handling ==========

        @PostMapping("/entity")
        public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
            Employee savedEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.ok("Employee added successfully: " + savedEmployee.toString());
        }

        @GetMapping("/entity/{id}")
        public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
            Employee employee = employeeService.getEmployeeById(id);
            return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.badRequest().body("Employee not found!");
        }

        @PutMapping("/entity/{id}")
        public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
            Employee employee = employeeService.updateEmployee(id, updatedEmployee);
            return employee != null ? ResponseEntity.ok("Employee updated successfully!") : ResponseEntity.badRequest().body("Employee not found!");
        }

        @DeleteMapping("/entity/{id}")
        public ResponseEntity<String> deleteEmployees(@PathVariable int id) {
            boolean deleted = employeeService.deleteEmployee(id);
            return deleted ? ResponseEntity.ok("Employee deleted successfully!") : ResponseEntity.badRequest().body("Employee not found!");
        }
    }


