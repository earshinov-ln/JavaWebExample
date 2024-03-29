# JavaWebExample

Демонстрация Java-технологий для программирования веб-интерфейса, пока только сервлетов и JSP.
Проект предназначен для запуска в контейнере сервлетов.

**Задание 1**: Сервлет. Калькулятор, который суммирует два числа, вводимые через параметры URL.

Для этого создана страница `/calc`, обрабатываемая классом `ImmediateCalculationServlet`.
Страница отвечает на метод `GET`, принимая параметры URL под названиями `1` и `2`, и отображает результат суммирования.  Пример использования: `/calc?1=2&2=4` (на странице должен отобразиться результат: `6`).

**Задание 2**: JSP. Калькулятор, который суммирует два числа, вводимые через форму с двумя полями на JSP. Результат выводится на той же странице.

Для этого создана страница `/Form.jsp`.  Код суммирования зашит непосредственно в JSP.

**Задание 3**: Использование сессий. То же самое, что и 2, но результат вычисления сохраняется в сессии и выводится на другой странице, нежели форма ввода параметров.

Для этого созданы две страницы: `/store` для ввода значений и `/view` для просмотра сохранённого значения.  Страницы обрабатываются сервлетами `StoringServlet` и `ViewingServlet`, которые внутри себя для генерации HTML-разметки вызывают JSP-страницы `Store.jsp` и `View.jsp` посредством `RequestDispatcher`'а (объекта, возвращаемого методом `request.getRequestDispatcher()`.  JSP-страницы помещены в папку `WEB-INF/` и недоступны для просмотра через браузер.  Таким образом продемонстировано взаимодействие сервлетов с JSP.

Для работы с сессией используется объект, возвращаемый методом `request.getSession()`.  В случае, если в HTML-форме на странице `/store` пользователь ввёл некорректные аргументы, значение, хранящееся в сессии, очищается.  Это демонстирует очистку значения, хранящегося в сессии.

**Задание 4**: JSP + БД. Форма для создания сотрудников в таблице БД Employee. Нужно использовать БД из второго задания.

Для этого создана страница `/AddEmployee.jsp`.  На странице используется Java bean `Employee`, который умеет сохранять данные в базу.  Использование Java bean'ов из JSP я считаю нерациональным (см. большой комментарий в `Employee.java`); будем считать, что на этой странице bean `Employee` — для демонстрации.

Для подключения БД на уровне сервера приложений должен быть настроен JDBC Resource с именем `jdbc/Lesson22`.  В Glassfish для этого необходимо:

  * Зайти в Admin Console (обычно http://localhost:4848/)
  * В левой панели раскрыть узел *Resources* > *JDBC*
  * Выбрать дочерний узел *Connection Pools* (*Пулы подключений*) и добавить новый пул для соединения с базой Derby, созданной в рамках задания по JDBC.  Название пула может быть произвольным, например, `DerbyPool-Lesson22`.  В случае, если для БД не настраивалась аутентификация, в *Additional Properties* пула подключений необходимо добавить свойство `Password` с произвольным значением, иначе Glassfish не согласится выполнять соединение с базой.
  * Выбрать дочерний узел *JDBC Resources* и добавить новый ресурс.  В поле *JNDI Name* ввести `jdbc/Lesson22`, в поле *Pool Name* выбрать вновь созданный пул.

## Что необходимо для работы над проектом

  * Eclipse
  * Контейнер сервлетов.  Я использовал сервер приложений Glassfish, очевидным образом включающий контейнер сервлетов.

Чтобы иметь возможность удобно деплоить проект в Glassfish, не выходя из Eclipse, пришлось установить в Eclipse т.н. коннектор к Glassfish.  Примечания:

  * У меня уже был установленный Glassfish и мне хотелось натравить коннектор на него, а не скачивать Glassfish повторно.
  * Я использую «ванильный» Eclipse.  Для сборок, например [Eclipse IDE for JavaEE developers][1], возможно требуются действия, отличные от описанных ниже.

Установку коннектора я выполнял по инструкции [Настраиваем взаимодействие Eclipse - GlassFish v3][2].  Перед установкой дополнений для Eclipse крайне рекомендуется открывать пустой workspace и отключать неиспользуемые update sites (Preferences > Install/Update > Available Software Sites).  Возможно, если бы я об этом знал, при установке коннектора не возникала бы ошибка и не пришлось бы устанавливать DTP (см. ниже), но навряд ли.

При установке коннектора у меня возникала ошибка, говорящая о том, что не найдена требуемая версия бандла `org.eclipse.datatools.connectivity`.  По названию бандла я решил, что необходимо установить [Eclipse Data Tools Platform][3] (DTP), к тому же он предоставляет полезную перспективу «Database Development».  DTP можно установить через update site http://download.eclipse.org/datatools/updates.  Для установки лучше выбирать последнюю версию, в моём случае  «Eclipse Data Tools Platform SDK 1.9.1».  При этом, пока я не отключил другие update sites на время установки, установка DTP завершалась с ошибкой.

После установки DTP и коннектора к Glassfish остаётся при создании «сервера» на вкладке Servers правильно выбрать тип коннектора и версию Glassfish.  В моём случае это «Glassfish Server Open Source Edition», а не «Glassfish 3.1», хотя собственно версия Glassfish у меня именно такая.  Выбор неправильной версии Glassfish приводит к выдаче пустого сообщения об ошибке (по которому трудно что-то понять) при деплое проекта.

[1]: http://eclipse.org/downloads/moreinfo/jee.php
[2]: http://samolisov.blogspot.com/2010/08/eclipse-glassfish-30.html
[3]: http://eclipse.org/datatools/
