package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

  private EntityManager entityManager;

  @Autowired
  public EmployeeDAOJpaImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Employee> findAll() {
    TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

    return query.getResultList();
  }

  @Override
  public Employee findById(int id) {
    return entityManager.find(Employee.class, id);
  }

  @Override
  public void save(Employee employee) {
    Employee dbEmployee = entityManager.merge(employee);
    employee.setId(dbEmployee.getId());
  }

  @Override
  public void deleteById(int id) {
    Employee employee = findById(id);

    if (employee != null) {
      entityManager.remove(employee);
    }
  }
}
