# Samsung API
## Сервис взаимодействия отправлений Samsung
 
### Сервис затрагивает:
- Получение заказов на отправку
- Посылает подтверждение о получении заказа
- Посылает ошибку при получении заказ
- Посылает информацию о трекинге отправления
- Взаимодействие с внутренней системой

### Установка и запуск:

1. Скачать содержимое из папки Build
2. Задать системную переменную `JAVA_HOME`
3. Запустить run.bat

### API сервиса

| Путь | Описание |
| ---- | -------- |
| POST /*${profile}* | Создание заказа |
| GET /*${profile}*?document=errors | Получение документов с ошибками |
| GET /*${profile}*?document=*{ID документа}* | Получение XML документа заказа по его ID  |
| DELETE /*${profile}*?document=*{ID документа}* | Удаляет документа по его ID |
| PATCH /*${profile}*?document=*{ID документа}* | Посылает подтверждение вручную |

### Механики

#### Получение заказа

> Заказ получается от клиента Samsung в формате TEXT/XML и сохраняется в базу данных
> в ответ пользователю будет отправлен код `200` с сообщением `processed`

#### Отправка подтверждения на сервер Samsung

> Отправка подтверждений организованно с помощью запланированного задания - `SamsungASKANSSender`.
> В каждой итерации задания ищется записи по условию:
- "askansSend" == *false*
- "askansSend" != *null*
- "tryCount" != *null*
- "tryCount" != *{Значение}*
> Формирует ответ в виде ASKANS (см. Документацию) для каждой найденной записи.
> Если сервер Samsung присылает ответ код `200`, то выставляется флаг `askansSend` и дата подтверждения `processedDate`.
> Если сервер Samsung присылает ответ с другим кодом, то уменьшается количество попыток в БД и пытается повторить на следующей итерации.
> Если количество попыток станет равным `-1` создается запись в таблице ошибок сервиса `tErrorSamsung`.

#### Обновление состояния сервиса
> Обновление состояния сервиса производится с помощью запланированного задания - `SamsungStateUpdater`.
> В каждой итерации в таблице `tPrintLGServiceState` обновляется дата записи с полем `aName` = `SamsungApi`  

### Описание файлов

1. /config/application.yaml - Конфигурация сервиса(стандартные конфигурации Spring с информацией о БД, порта сервера, логирования и др.)
2. /config/Constants.properties - Константы сервиса
3. /run.bat - Запуск сервиса
4. /SamsungAPIService.jar - JAR сервиса
5. /docs - документы SAMSUNG 

#### Конфигурирование

1. Константы
- samsung.sender.enable - Включает/Выключает отправку ASKANS серверу SAMSUNG 
(при значении `off` происходит имитация `200` ответа, при `on` посылает запрос на сервер SAMSUNG)

- samsung.send.host - Адрес тестового сервера SAMSUNG
- samsung.send.host.prod - Адрес продуктивного сервера SAMSUNG
- samsung.askans.rate - Периодичность посылки подтверждений на сервер SAMSUNG
- samsung.update.rate - Периодичность обновления состояния сервиса
- samsung.sent.max.count - Максимальное количество попыток отправить подтверждение
- samsung.delivery.path - Путь к сохранению XML файлов заказов
- samsung.delivery.error - Путь к сохранению XML файлов ошибочных заказов.
- samsung.delivery.unserializable - Путь к сохранению XML файлов заказа с ошибкой сериализации.
- samsung.delivery.unsaved - Путь к сохранению XML файлов заказа с ошибкой сохранения.
- samsung.delivery.unknown - Путь к сохранению XML файлов заказа с неизвестной ошибкой.

2. Файл запуска
- base.path - Рабочая папка сервиса.
- config.path - Путь к конфигурациям сервиса.
- spring.profile.active - Профиль сервиса
- spring.config.location - Путь к Spring конфигурациям
- server.port - Порт сервера

### Профили

- prod - Продуктивный профиль со ссылками на продуктивный сервер SAMSUNG и подключение к продуктивной базе.
- debug - Профиль для отладки со ссылками на тестовый сервер SAMSUNG и подключение к базе в песочнице.
- test - Тестовый профиль со ссылками на продуктивный сервер SAMSUNG и подключение к базе в песочнице.
- local - Профиль для разработки.