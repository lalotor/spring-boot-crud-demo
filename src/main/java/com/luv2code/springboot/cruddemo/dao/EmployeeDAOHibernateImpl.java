package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

  private EntityManager entityManager;

  @Autowired
  public EmployeeDAOHibernateImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
//  @Transactional
  public List<Employee> findAll() {
    Session session = entityManager.unwrap(Session.class);

    Query<Employee> query = session.createQuery("from Employee", Employee.class);

    return query.getResultList();
  }

  @Override
//  @Transactional
  public Employee findById(int id) {
    Session session = entityManager.unwrap(Session.class);

    return session.get(Employee.class, id);
  }

  @Override
//  @Transactional
  public void save(Employee employee) {
    Session session = entityManager.unwrap(Session.class);

    session.saveOrUpdate(employee);
  }

  @Override
//  @Transactional
  public void deleteById(int id) {
    Session session = entityManager.unwrap(Session.class);

    Employee employee = findById(id);

    if (employee != null) {
      session.delete(employee);
    }
  }
}
