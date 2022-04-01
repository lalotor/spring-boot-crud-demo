package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeRestController(@Qualifier("employeeServiceSpringDataImpl") EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @RequestMapping("/employees")
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  @RequestMapping("/employees/{employeeId}")
  public Employee getCustomer(@PathVariable int employeeId) {
    Employee employee = employeeService.findById(employeeId);

    if (employee == null) {
      throw new RuntimeException("Employee not found by id: " + employeeId);
    }

    return employee;
  }

  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee employee) {
    employee.setId(null);
    employeeService.save(employee);

    return employee;
  }

  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee employee) {
    employeeService.save(employee);

    return employee;
  }

  @DeleteMapping("/employees/{employeeId}")
  public String deleteCustomer(@PathVariable int employeeId) {
    Employee employee = employeeService.findById(employeeId);

    if (employee == null) {
      throw new RuntimeException("Employee not found by id: " + employeeId);
    }

    employeeService.deleteById(employeeId);

    return "Deleted employee with id: " + employeeId;
  }
}
