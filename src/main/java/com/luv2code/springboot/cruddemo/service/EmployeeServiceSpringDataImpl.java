package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceSpringDataImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceSpringDataImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public Employee findById(int id) {
    Optional<Employee> employeeOpt = employeeRepository.findById(id);
    if (employeeOpt.isPresent()) {
      return employeeOpt.get();
    } else {
      throw new RuntimeException("Employee not found by id: " + id);
    }
  }

  @Override
  @Transactional
  public void save(Employee employee) {
    employeeRepository.save(employee);
  }

  @Override
  @Transactional
  public void deleteById(int id) {
    employeeRepository.deleteById(id);
  }
}
