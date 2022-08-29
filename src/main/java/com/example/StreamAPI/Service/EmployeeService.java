package com.example.StreamAPI.Service;

import com.example.StreamAPI.Exception.EmployeeAlreadyAddedException;
import com.example.StreamAPI.Exception.EmployeeNotFoundException;
import com.example.StreamAPI.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public Employee add(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employees.put(getKey(firstName, lastName), employee);
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        if (employees.containsKey(getKey(firstName, lastName))) {
            employees.remove(getKey(firstName, lastName));
            return employees.remove(getKey(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        if (employees.containsKey(getKey(firstName, lastName))) {
            return employees.get(getKey(firstName, lastName));
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> printAll() {
        return new ArrayList<>(employees.values());
    }
}
