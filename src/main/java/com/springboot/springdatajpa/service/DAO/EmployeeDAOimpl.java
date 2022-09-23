package com.springboot.springdatajpa.service.DAO;


import com.springboot.springdatajpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
// SpringBoot работает с JPA, как мы помним, это спецификация, которая описывает взаимодействие
// с базой данных, а самой популярной реализацией является Hibernate. Прежде чем описывать CRUD
// операции, мы описывали в конфигурации бин для создания SessionFactory, создавали здесь поле
// с типом этого бина и с помощью внедрения зависимости инициализировали это поле.
// Это нужно было нам для получения сессии. Однако все эти операции относятся именно к Hibernate,
// SpringBoot использует для этих операций другой объект - EntityManager.
@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    // Для начала создаем поле EntityManager и внедряем зависимость, для дальнейшей работы.
    // Забавный момент, IDE ругается, что не видит бина для внедрения зависимости, однако
    // это происходит из-за аннотации @Repository. Если мы ее закомментируем, ошибка пропадет.
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        // Нам все так же необходимо получить объект сессии, entityManager является оберткой
        // для сессии и методом unwrap() с переданным внутрь классом Session мы и получаем
        // объект сессии с которым уже работаем. Позже мы рассмотрим как это делать
        // только с помощью SpringBoot, а тут у нас будет некий гибрид с Hibernate.
        // Пока не будем трогать этот класс, вспомним, что нам необходимо сделать далее.
        // А далее нам нужно создать Service, который будет посредником между DAO и Controller.
        // Мы его так же скопируем из предыдущего проекта. Как и сам контроллер.
        // Мы закомментируем все методы, которые нам в данный момент не нужны и удалим обработку
        // исключений. На данной лекции у нас будет только получение всех работников.
        // Перейдем в MyRestController.
        //
        // Мы закомментировали код и будем его переписывать. Нам более не нужна сессия
        // и объект Query из Hibernate. Смотрим как теперь мы получаем нужные данные.
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        return query.getResultList();
        // Теперь Query мы импортируем из import javax.persistence, код фактически не поменялся,
        // но никаких сессий нам уже не нужно.
        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }
    // Теперь меняем остальные методы.
    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);
        // Ранее мы сохраняли работника методом saveOrUpdate(), теперь мы делаем
        // это методом merge(). Немного погрузимся в теорию
        // JPA. EntityManager представляет собой контекст для работы с хранимыми сущностями.
        // Entity-сущности имеют четыре состояния: transient - экземпляр был создан, но
        // пока не связан с persistence контекстом, persistent - сущность связана с контекстом
        // и имеет идентификатор, detached - у сущности есть идентификатор, но она не связана с
        // контекстом, removed - сущность находится в контексте, имеет идентификатор, но
        // запланирована к удалению. Наша  сущность может быть как transient так и detached
        // это зависит от того, существует ли объект в БД.

        // Если мы добавляем нового работника id в контексте ему добавлен не будет, поэтому
        // мы сделаем это так:
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());

    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        return session.get(Employee.class, id);
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        session.delete(session.get(Employee.class, id));
        // Здесь запись похожа на Hibernate, создаем запрос, в котором id передаем как параметр,
        // ниже определяем этот параметр и выполняем запрос. Теперь все наши методы написаны
        // с помощью спецификации JPA, а значит мы в любой момент можем поменять реализацию./
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
