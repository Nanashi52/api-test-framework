# API Test Framework

Учебный проект — API-тестирование публичного REST API с использованием REST Assured и JUnit 5.

## Стек

- Java 17
- REST Assured 5.4
- JUnit 5
- Hamcrest
- Maven

## Тестируемый API

[JSONPlaceholder](https://jsonplaceholder.typicode.com/) — бесплатный фиктивный API для тестирования и прототипирования.

## Покрытие тестами

| Endpoint | Метод | Описание | Тестов |
|----------|-------|----------|--------|
| `/users` | GET | Список пользователей, фильтрация по id | 5 |
| `/posts` | GET | Список постов, фильтрация по userId | 4 |
| `/posts` | POST | Создание нового поста | 1 |
| `/posts/{id}` | PUT | Обновление поста | 1 |
| `/posts/{id}` | DELETE | Удаление поста | 1 |

## Используемые подходы

- Валидация статус-кодов
- Проверка структуры и значений JSON-ответа
- Извлечение полей через `JsonPath` и `Response`
- Фильтрация через query-параметры
- CRUD-операции (POST, PUT, DELETE)
- `@DisplayName` для читаемости отчётов

## Запуск

```bash
mvn test
```
