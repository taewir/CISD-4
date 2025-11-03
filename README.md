# Spring Boot Car Console App

Консольное приложение для работы с сущностью "Автомобиль" с использованием Spring Boot, Spring Data JPA, PostgreSQL и Lombok.

## Запуск приложения

### 1. Поднять базу данных PostgreSQL через Docker

Выполнить команду: 
```shell
docker run --name my-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=car_db -p 5432:5432 -d postgres
```

### 2. Настроить подключение к БД

В файле `src/main/resources/application.properties` должны быть такие параметры:
- spring.datasource.url=jdbc:postgresql://localhost:5432/car_db
- spring.datasource.username=user
- spring.datasource.password=pass
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true

### 3. Собрать и запустить проект

- Открыть проект в IntelliJ IDEA.
- Убедиться, что все зависимости подтянуты.
- Выполнить команду: `mvn clean install`
- запустить приложение с помощью команды `mvn spring-boot:run` или через IDE запустить класс `DemoApplication`.

### 4. Использовать консольное меню

Приложение предложит действия:
- Добавить автомобиль
- Показать все автомобили
- Редактировать по id
- Удалить по id
- Поиск по марке
