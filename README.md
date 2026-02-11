# Megatop-Test-Automation-Framework

# Тестовая автоматизация для сайта MEGATOP.by

## Описание проекта

Тестовая автоматизация (TAF) для веб-сайта [MEGATOP.by](https://megatop.by/) и API сервиса авторизации.  
Проект включает в себя:
- UI-тесты на главную страницу, категории (мужчины, женщины, дети), форму логина.
- API-тесты на авторизацию (негативные сценарии).
- Интеграцию с логированием, отчётами и Jenkins.

---

## Технологии и инструменты

- **Java 17**
- **Selenium WebDriver 4**
- **JUnit 5**
- **REST Assured**
- **Log4j2**
- **Maven**
- **ChromeDriver**
- **JavaFaker**

---

## Архитектура проекта
   ```bash

src/
├── main/java/by/megatop/
│ ├── api/ # API-тесты
│ ├── driver/ # WebDriver Singleton
│ ├── enums/ # Перечисления (ClientCategory)
│ ├── pages/ # Page Object модели
│ ├── ui/tests/ # UI-тесты
│ ├── api/tests/ # API-тесты
│ └── utils/ # Вспомогательные классы (WaitUtils, UnicodeUtils)
└── resources/
└── log4j2.xml # Конфигурация логирования
```
---

## Установка и запуск

### Требования:
- JDK 17
- Maven 3.6+

### Шаги:
1. Клонировать репозиторий:
   ```bash
   git clone https://github.com/nay40k/Megatop-Test-Automation-Framework.git
   cd Megatop_TAF
   ```
2. Собрать проект:
   ```bash
   mvn clean compile
   ```
3. Запустить все тесты:
   ```bash
   mvn clean test
   ```
4. Запустить UI-тесты:
    ```bash
    mvn clean test -Dtest="**/ui/tests/*"
    ```
5. Запустить API-тесты:
    ```bash
    mvn clean test -Dtest="**/api/tests/*"
    ```
   
## Отчёты
#### Логи сохраняются в logs/test.log

## Покрытие тестами
### UI-тесты:
- Проверка текстов категорий (мужчины, женщины, дети)
- Проверка модального окна логина
- Проверка "Зоны трендов" на странице мужчин
### API-тесты:
- Проверка ошибок авторизации (422, 500)
- Использование Faker для генерации данных

## Автор
### _Виктор Третьяк_
_Слушатель курсов IT-Academy_

