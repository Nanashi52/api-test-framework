# API Test Framework

Educational project for API testing of a public REST API using REST Assured and JUnit 5.

## Stack

- Java 17
- REST Assured 5.4
- JUnit 5
- Hamcrest
- Jackson
- Maven

## Tested API

[JSONPlaceholder](https://jsonplaceholder.typicode.com/) - free fake API for testing and prototyping.

## Test Coverage

| Endpoint | Method | Description | Tests |
|----------|--------|-------------|-------|
| `/users` | GET | User list, filtering by id | 5 |
| `/posts` | GET | Post list, filtering by userId | 4 |
| `/posts` | POST | Create a new post | 1 |
| `/posts/{id}` | PUT | Update a post | 1 |
| `/posts/{id}` | DELETE | Delete a post | 1 |

## Approaches Used

- Status code validation
- JSON response structure and value checks
- Field extraction via `JsonPath` and `Response`
- Filtering via query parameters
- CRUD operations (POST, PUT, DELETE)
- `@DisplayName` for readable test reports

## Run

```bash
mvn test
```
