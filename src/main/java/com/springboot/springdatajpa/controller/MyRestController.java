//package com.springboot.springdatajpa.controller;
//
//
//import com.springboot.springdatajpa.entity.Employee;
//import com.springboot.springdatajpa.service.services.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//@RestController
//@RequestMapping("/api")
//public class MyRestController {
//    @Autowired
//    private EmployeeService service;
//
//    @GetMapping("/employees")
//    public List<Employee> showAllEmployees() {
//        List<Employee> allEmployees = service.getAllEmployees();
//        return allEmployees;
//    }
//
//    @GetMapping("/employees/{id}")
//    public Employee getEmployee(@PathVariable int id) {
//        Employee employee = service.getEmployee(id);
//        return employee;
//    }
//
//    @PostMapping("/employees")
//    public Employee addNewEmployee(@RequestBody Employee employee) {
//        service.saveEmployee(employee);
//        return employee;
//    }
//
//    // Изменение существующего работника./
//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee employee) {
//        service.saveEmployee(employee);
//        return employee;
//    }
//    // Удаление работника
//    @DeleteMapping("/employees/{id}")
//    public String deleteEmployee(@PathVariable int id) {
//        Employee employee = service.getEmployee(id);
//        service.deleteEmployee(id);
//        return "Employee with ID="+id+" was deleted.";
//    }
//    // Как можно догадаться URL передается методом GET, после /employees идет критерий
//    // по которому ищем, а далее используем PathVariable, имя аргумента находится в
//    // фигурных скобках, оно должно совпадать с именем параметра, который мы передаем в метод./
//    @GetMapping("/employees/name/{name}")
//    public List<Employee> findAllByName(@PathVariable String name) {
//        return  service.findAllByName(name);
//    }
//}
