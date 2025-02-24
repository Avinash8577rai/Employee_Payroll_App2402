package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

    @Service
    public class EmployeeService implements IEmployeeService {

        private List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        private List<Employee> employeeList = new ArrayList<>();

        // ========== DTO Handling (First) ==========

        @Override
        public EmployeeDTO addEmployeeDTO(EmployeeDTO employeeDTO) {
            employeeDTOList.add(employeeDTO);
            return employeeDTO;
        }

        @Override
        public List<EmployeeDTO> getAllEmployeesDTO() {
            return employeeDTOList;
        }

        @Override
        public EmployeeDTO getSampleEmployeeDTO() {
            return new EmployeeDTO("Sample Employee", 50000.0);
        }

        // ========== Employee Entity Handling (Second) ==========

        @Override
        public Employee addEmployee(Employee employee) {
            employeeList.add(employee);
            return employee;
        }

        @Override
        public Employee getEmployeeById(int id) {
            return employeeList.stream()
                    .filter(emp -> emp.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Employee updateEmployee(int id, Employee updatedEmployee) {
            for (Employee employee : employeeList) {
                if (employee.getId() == id) {
                    employee.setName(updatedEmployee.getName());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    return employee;
                }
            }
            return null;
        }

        @Override
        public boolean deleteEmployee(int id) {
            return employeeList.removeIf(emp -> emp.getId() == id);
        }

        // ✅ Add EmployeeDTO to the in-memory list
        public EmployeeDTO addEmployeesDTO(EmployeeDTO employeeDTO) {
            employeeDTOList.add(employeeDTO);
            return employeeDTO;
        }

        // ✅ Get all EmployeeDTOs from the list
        public List<EmployeeDTO> getAllEmployeeDTOs() {
            return employeeDTOList;
        }

        // ✅ Add Employee entity to the in-memory list (Simulating DB storage)
        public Employee addEmployees(Employee employee) {
            employeeList.add(employee);
            return employee;
        }

        // ✅ Get all Employees from the list
        public List<Employee> getAllEmployees() {
            return employeeList;
        }
    }
