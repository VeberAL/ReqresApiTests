# Проект по автоматизации тестирования API сервиса Reqres
<img  src="media/logo/reqresin.jpg">

## Содержание:

<a id="наверх"></a>
- <a href="#tools"> Технологии и инструменты.</a>
- <a href="#checking"> Реализованные проверки.</a>
- <a href="#console"> Запуск тестов из терминала.</a>
- <a href="#jenkins"> Сборка в Jenkins.</a>
- <a href="#allureReport"> Allure - отчет.</a>
- <a href="#allure"> Интеграция с Allure TestOps.</a>
- <a href="#jira"> Задача в Jira с отображением тест-кейсов и запусками.</a>  
- <a href="#tg"> Уведомление о пройденных тестах в Telegram.</a>
---

<a id="tools"></a>
## Технологии и инструменты:

Автотесты написаны на языке `Java` с использованием `JUnit 5`, `Selenide`, `Rest-Assured`. Сборщик проекта - `Gradle`. Для удаленного запуска реализована задача в `Jenkins` с формированием `Allure-отчета` и
отправкой результатов в `Telegram канал` при помощи бота. Так же осуществлена интеграция с `Allure TestOps` и `Jira`.

---

<a id="checking"></a>
## Реализованные проверки:

- Проверка наличия email-адреса пользователя.
- Проверка валидации пользователя.
- Проверка информации о пользователе.
- Проверка наличия выбранного наименования цвета.
- Проверка отсутствия ресурса.
- Проверка успешного создания нового пользователя.
- Проверка успешного удаления данных о пользователе.


<a id="console"></a>
### Локальный запуск тестов из терминала
`gradle clean test`

---

### Варианты запуска тестов

Для запуска тестов можно выбрать следующие способы:

`gradle clean ...`
```mermaid
flowchart LR
    A[Test Suite] --> B[Тесты валидации] --> E[account_test]
    A[Test Suite] --> D[Тесты данных] --> I[user_test]
    A[Test Suite] --> H[Все тесты] --> C[test]
```
---

<a id="jenkins"></a>
## Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/job/VeberApiDiplom/"> Jenkins </a>
Для запуска сборки необходимо перейти в раздел <code>Собрать с параметрами</code>, выбрать необходимые параметры и нажать кнопку <code>Собрать</code>.
<p align="center">
<a href="https://jenkins.autotests.cloud/job/VeberApiDiplom/"><img src="media/screenshots/jenkins.png" alt="Jenkins main"/></a>
</p>
После выполнения сборки, в блоке История сборок напротив номера сборки появятся значки Allure Report и Allure TestOps, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.

---
<a id="allureReport"></a>
## [Allure](https://jenkins.autotests.cloud/job/VeberApiDiplom/allure/) отчет

### Главная страница отчета

<p align="center">
<img src="media/screenshots/allure main.png" alt="Allure report">
</p>

### Тест-кейсы

<p align="center">
<img src="media/screenshots/alluresuites.png" alt="Test Case">
</p>

---
<a id="allure"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4287/dashboards">Allure TestOps</a>
На *Dashboard* в <code>Allure TestOps</code> видна статистика количества тестов. Новые тесты, а так же результаты прогона приходят по интеграции при каждом запуске сборки.
## Основная страница отчёта

<p align="center">  
<img title="Allure TestOps Dashboard" src="media/screenshots/testopsmain.png">  
</p>  

## Пример тест-кейса
<p align="center">
<img title="AllureTC" src="media/screenshots/testops.png">
</p>

---

<a id="jira"></a>
## Задача в [Jira](https://jira.autotests.cloud/projects/HOMEWORK/issues/HOMEWORK-1264).

Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в тикете отображается, какие тест-кейсы были написаны в рамках задачи и результат их прогона.
### Задача в Jira

<p align="center">
<img src="media/screenshots/jira.png">
</p>

#### Содержание задачи

- Цель
- Задачи для выполнения
- Тест-кейсы из Allure TestOps
- Результат прогона тестов в Allure TestOps

---

<a id="tg"></a>
## Уведомление о пройденных тестах в Telegram.

После завершения сборки, бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом
о прогоне тестов в специально настроенный чат.

### Уведомление из переписки с чат ботом

<p align="center">
<img src="media/screenshots/tg.png" alt="TestOps launch">
</p>


#### Содержание уведомления в Telegram

- Окружение
- Комментарий
- Длительность прохождения тестов
- Общее количество сценариев
- Процент прохождения тестов
- Ссылка на Allure отчет

---
[Наверх ⬆](#наверх)