package com.springboot.springdatajpa.controller;


import com.springboot.springdatajpa.entity.Employee;
import com.springboot.springdatajpa.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
// Контроллер изменяться не будет, просто взглянем на него снова и запустим наш проект.
// Сами Tomcat мы теперь не конфигурируем, для доступа к определенным методам контроллера
// мы прописываем http://localhost:8080/api +ссылку метода который нас интересует.

// В конце лекции необходимо отметить важность файла application.properties, весь проект можно настроить
// с помощью этого файла, полный список настроек огромен необходимо будет изучать самому,
// а сейчас перейдем в него и посмотрим как мы можем настроить путь к сервлету и порт подключения./
@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = service.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }

    // Изменение существующего работника./
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }
    // Удаление работника
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        service.deleteEmployee(id);
        return "Employee with ID="+id+" was deleted.";
    }

}
