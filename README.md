# pianoBackend
Тестовое задание для компании piano. <br/> 
Проект написан на **java 17** с использованием **SpringBoot**, **Web flux**, и **Lombok**.<br/> 
Для тестов использовал **Jupiter** и **Wire Mock**. <br/>
Для документации использовал **Swagger** который после разворачивания приложения в Docker находится по ссылке: http://localhost:8090/api/v1/swagger-ui и сгенерировал java-doc, которые лежат  в корне проекта. Также была реализована  Аутентификация с помощью WebFluxSecurity, которая представляет из себя Basic Аутентификацию с единственным in memory пользователем у которого логин и пароль user:password. 
<br/> Для разворачивания тестового проекта нужно:
<br/>1 скачать архивы работ с гитхаба (фронт и бэк) в одну папку
<br/>2  достать из корня данного проекта файл под названием docker-compose.yaml на уровень выше – так, чтобы  он лежал на одном уровне с папками проектов.
<br/>3 запустить командную строку или терминал из этой директории и ввести команду ```docker-compose up```
После того как скачается интернет будет доступны фронт по адресу: http://localhost:4200/
и бэк по адресу: http://localhost:8090/api/v1/
<br/>Для проверки приложения нужно зайти на адрес фронтовой части ввести в поле поиска искомое значение, ввести логин и пароль для   Аутентификации и нажать кнопку искать, после чего можно через радиобатоны произвести сортировку. 
Тесты выполнить можно через Intellij Idea или вызвать команду ```./gradlew bootRun``` если он установлен. Понимаю что некоторые вещи  в тестовом были избыточны.
