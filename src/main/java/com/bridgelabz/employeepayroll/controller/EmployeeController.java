package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {
        private List<EmployeeDTO> employeeDTOList = new ArrayList<>();


        private Map<Integer, String> employees = new HashMap<>();

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

       }

