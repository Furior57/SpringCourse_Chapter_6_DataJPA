package com.springboot.springdatajpa.service.DAO;



import com.springboot.springdatajpa.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);


    void deleteEmployee(int id);
}