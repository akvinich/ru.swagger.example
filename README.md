Экспериментальный проект по внедрению swagger-coverage
========================================================

##Документация на тестируемое API
    https://testbase.atlassian.net/wiki/spaces/USERS/overview

##Создание и запуск (локально) проекта:
    1) Добавить в проект зависимость (с последней версией swagger-coverage-rest-assured):
    <repositories>
            <repository>
                <id>viclovsky</id>
                <url>https://dl.bintray.com/viclovsky/maven/</url>
            </repository>
     </repositories>
    <dependency>
        <groupId>com.github.viclovsky.swagger.coverage</groupId>
        <artifactId>swagger-coverage-rest-assured</artifactId>
        <version>1.3.1</version>
    </dependency>
    
    2) Добавить фильтр при конфигурировании rest-assured:
    given().filter(new SwaggerCoverageRestAssured())
    
    3) Запустить тесты используя testNG-плагин, выбрав класс UsersTest
    
    4) Загрузить приложение swagger-coverage-commandline
    wget https://dl.bintray.com/viclovsky/maven/com/github/viclovsky/swagger/coverage/swagger-coverage-commandline/1.3.1/swagger-coverage-commandline-1.3.1.zip
    4.1) Загрузить wget для Windows можно перейдя по ссылке и выбрав последнюю версию
    https://eternallybored.org/misc/wget/
    После загрузки нужно командой path установить путь, где лежит wget.exe, например: path c:\
    
    5) Распаковать скачанное приложение
    
    6) Во внутреннюю папку bin, распакованного приложения, добавить:
        - папку swagger-coverage-output, сгенерированную после прогона тестов
        - файлы conf.json, petstory.json из ресурсов
    
    7) Перейти в папку bin и запустить генерацию отчета:
        C:\swagger-coverage-commandline-1.3.1\bin>swagger-coverage-commandline -s petstory.json -i swagger-coverage-output -c conf.json
        В папке пояаится html-отчет
        
##Текущие проблемы:
    1) Не работает команда:
        -c, --configuration
          Path to file with report configuration.
       Отчет генерируется несмотря на файл conf.json. Ожидаемое имя отчета - custom-report.html, фактически - swagger-coverage-report.html
    
    2) Не работает указатель "$ref": "#/definitions/Register" на схему валидации из файла petstory.json
    
    3) Исследовать возможность работы swagger-comandline через добавленную зависимость:
        <dependency>
            <groupId>com.github.viclovsky.swagger.coverage</groupId>
            <artifactId>swagger-coverage-commandline</artifactId>
            <version>1.3.1</version>
         </dependency>