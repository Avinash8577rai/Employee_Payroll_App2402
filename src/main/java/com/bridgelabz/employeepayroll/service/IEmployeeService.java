package com.bridgelabz.employeepayroll.service;
import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.Employee;

import java.util.List;

public interface IEmployeeService {
    // DTO Methods
    EmployeeDTO addEmployeeDTO(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployeesDTO();
    EmployeeDTO getSampleEmployeeDTO();

    // Employee Entity Methods
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int id);
    Employee updateEmployee(int id, Employee updatedEmployee);
    boolean deleteEmployee(int id);
}