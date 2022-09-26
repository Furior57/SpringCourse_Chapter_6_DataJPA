public class Lesson_76_SpringDataREST {
    // Мы использовали возможности Spring Data, чтобы переписать DAO и Service, пришла очередь
    // переделать REST функционал.
    // Когда мы переписывали интерфейс-репозиторий, мы расширяли его JpaRepository, а тот параметризировали
    // двумя типами, Entity-сущностью и типом id. Однако в MyRestController у нас весь код
    // написан строго под работу с Employee. А что если у нас несколько сущностей?
    // Определять несколько REST контроллеров? Нет. Spring Data поможет нам и здесь.

    // Первое, что мы сделаем, это полностью закомментируем весь код в MyRestController и
    // в сервисах. Они больше не понадобятся.
    // Далее нам необходимо добавить зависимость от spring-boot-starter-data-rest.
    // Spring Data REST предоставляет механизм автоматического создания REST API на основе
    // типа Entity, прописанного в репозитории проекта. То есть Spring будет сканировать
    // репозиторий, в нашем случае интерфейс EmployeeRepository. URL для методов
    // будет создан автоматически в соответствии с конвенцией, вспоминаем таблицу
    // методов и адресов которую мы на прошлых лекциях записывали.
    // Снова приступаем к вкусностям :) Нам нет никакой необходимости руками описывать контроллер.
    // Все генерируется автоматически. Путь к контексту(оно же адрес диспетчера сервлетов)
    // прописывается в файле настроек, у нас это /spring-boot. В ответе придет несколько
    // видоизмененный json, помимо данных о работниках будет указана ссылка на каждого работника,
    // а там же информация о максимальном количестве отображенных работников в одной
    // странице, общее количество страниц и номер текущей страницы.

    // Так же имеется отличие при обновлении данных о работнике, теперь в url
    // мы передаем id работника PUT/employees/{employeeId}, а в теле запроса
    // передаем json с новыми данными, но без id./
}