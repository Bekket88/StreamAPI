package com.example.StreamAPI.Service;

import com.example.StreamAPI.Exception.EmployeeNotFoundException;
import com.example.StreamAPI.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int department) {
        return employeeService.printAll().stream().
                filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findMinSalary(int department) {
        return employeeService.printAll().stream().
                filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findAll(int department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findAllByDep() {
        return employeeService.printAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
