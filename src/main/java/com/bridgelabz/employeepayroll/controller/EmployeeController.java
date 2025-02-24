package com.bridgelabz.employeepayroll.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {

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
    }
