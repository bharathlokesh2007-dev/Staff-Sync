package com.example.rest.services;

import com.example.rest.model.Employee;
import com.example.rest.repository.EmployeeRepository;
import com.example.rest.exception.resourcenotfoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new resourcenotfoundException("Employee not found with id: " + id));
    }

    // Save new employee
    public Employee saveEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee emp) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new resourcenotfoundException("Employee not found with id: " + id));

        existing.setName(emp.getName());
        existing.setDept(emp.getDept());
        existing.setSalary(emp.getSalary());

        return employeeRepository.save(existing);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new resourcenotfoundException("Employee not found with id: " + id));
        employeeRepository.delete(existing);
    }
}
