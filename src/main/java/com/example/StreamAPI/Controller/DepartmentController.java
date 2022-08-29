package com.example.StreamAPI.Controller;

import com.example.StreamAPI.Model.Employee;
import com.example.StreamAPI.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int department) {
        return departmentService.findMaxSalary(department);
    }

    @GetMapping(path = "min-salary")
    public Employee minSalary(@RequestParam("departmentId") int department) {
        return departmentService.findMinSalary(department);
    }

    @GetMapping(path = "all", params = "departmentId")
    public List<Employee> getAllInDep(@RequestParam("departmentId") int department) {
        return departmentService.findAll(department);
    }

    @GetMapping(path = "all")
    public Map<Integer, List<Employee>> getAllByDeps() {
        return departmentService.findAllByDep();
    }
}
