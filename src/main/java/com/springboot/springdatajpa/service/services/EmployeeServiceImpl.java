package com.springboot.springdatajpa.service.services;


import com.springboot.springdatajpa.entity.Employee;
import com.springboot.springdatajpa.service.DAO.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Переименуем это поле. Теперь уберем со всех методом аннотацию @Transactional
    @Autowired
    private EmployeeRepository employeeRepository;
    // А теперь смотрим как записываются CRUD операции:
    // findAll(), findById(),save() - добавляет\изменяет работника, deleteById(),
    //

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        // Этот метод возвращает Employee, а метод findById() возвращает объект Optional.
        // Для начала мы проверим не пустой ли это объект и возвращаем либо объект, либо null.
        // Сделаем это мы вот такой красивой записью. Перейдем обратно в интерфейс EmployeeRepository.
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByName(String name) {
        return employeeRepository.findAllByName(name);
    }
}
