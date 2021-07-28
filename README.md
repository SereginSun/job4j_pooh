# job4j_pooh
[![Build Status](https://travis-ci.com/SereginSun/job4j_pooh.svg?branch=main)](https://travis-ci.com/SereginSun/job4j_pooh)
[![codecov](https://codecov.io/gh/SereginSun/job4j_pooh/branch/main/graph/badge.svg?token=NMYZBCB2AS)](https://codecov.io/gh/SereginSun/job4j_pooh)

Проект является простой реализацией асинхронной очереди. Приложение запускает Socket и ждёт клиентов. Клиетами могут
быть как отправители (publisher), так и получатели (subscriber). Приложение имеет режимы queue и topic и использует
протокол обмена сообщениями http. В качестве клиента используется cURL.

### Queue
Отправитель посылает сообщение с указанием имени очереди и текстом. Получатель читает первое сообщение и удаляет его из
очереди. У сервиса единая очередь для всех получателей. Если приходят несколько получателей, то они читают из одной
очереди. Уникальное сообщение может быть прочитано, только одним получателем.

### Topic
Отправитель посылает сообщение с названием темы и текстом. У сервиса есть уникальная очередь для каждого подписчика темы.
Подписчик читает первое сообщение в своей очереди и удаляет его из очереди.

### Пример использования:
#### Queue-mode POST:
```
curl -i -X POST -d "temperature=18" http://localhost:9000/queue/weather
```
_queue_ - режим queue;

_weather_ - уникальное имя очереди, если очереди нет в сервисе, то создаётся новая очередь;

_"temperature=18"_ - текст сообщения.

#### Queue-mode GET:
```
curl -i http://localhost:9000/queue/weather
```
Response:
```
HTTP/1.1 200 OK
temperature=18
```
#### Topic-mode POST:
```
curl -i -X POST -d "temperature=20" http://localhost:9000/topic/weather
```
_topic_ - режим topic;

_weather_ - уникальное имя темы, если темы нет в сервисе, то создаётся новая тема;

_"temperature=20"_ - текст сообщения.

#### Topic-mode GET:
```
curl -i http://localhost:9000/topic/weather/1
```
_1_ - указывает на ID клиента.

Response:
```
HTTP/1.1 200 OK
temperature=20
```

### Архитектура проекта

![img](src/main/resources/poohServer.png)