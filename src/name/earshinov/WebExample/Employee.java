package name.earshinov.WebExample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	// Хотя empno и число, храним как строчку, чтобы иметь возможность самим
	// обрабатывать NumberFormatException.  Более конкретно, существует три варианта:
	//
	//   а) сделать поле и сеттер типа int
	//   б) сделать поле int, в сеттере принимать строчку и самим преобразовывать в число
	//   в) сделать поле и сеттер строковыми, преобразование выполнять в методе save()
	//
	// В JSP для установки свойств bean'а удобно использовать конструкцию <jsp:setProperty>
	// (позволяющую инициализировать bean значениями из параметров запроса), а не скриптлет.
	// В противном случае проще и правильнее было бы код скриптлета перенести в сервлет.
	//
	// Для отлова исключений, возникающих при присваивании значений посредством <jsp:setProperty>,
	// насколько мне известно, есть только одно стандартное решение — <c:catch> из JSTL.  Но там
	// исключения нельзя отфильтровать по типу, и rethrow сделать тоже невозможно.  Я считаю,
	// что неправильно ловить (и блокировать, пусть и с выдачей сообщения) все возможные
	// исключения, когда нужен только NumberFormatException.  Поэтому <c:catch> отбрасывается.
	//
	// Таким образом, не остаётся способов нормально обработать исключение, возникающее
	// при присваивании свойств bean'у, то есть в сеттере.  Выход — выполнять преобразование
	// в методе save(), который по-любому вызывается в скриптлете.  Это вариант в).
	//
	// На мой взгляд, этих проблем достаточно, чтобы отказаться от использования
	// Java Bean'ов в JSP (а вместе с ними — и от скриптлетов).  Лучше основной
	// код страницы переносить в сервлет, а JSP вызывать только для вывода
	// разметки.  Удобную инициализацию bean'ов из запроса использовать при этом
	// подходе не получится, ну и ладно…
	//
	private String empno = "";

	private String ename = "";
	private String jobTitle = "";

	// Конструктор, геттеры, сеттеры

	public Employee() { }

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	// Полезные методы

	public void save() throws HandlingException {

		// проверяем, что empno - число
		int empnoNumber;
		try {
			empnoNumber = Integer.parseInt(empno);
		}
		catch (NumberFormatException e) {
			throw new HandlingException("Номер сотрудника должен быть числом", e);
		}

		// Получаем DataSource для взаимодействия с базой.
		//
		// Не можем использовать аннотацию @Resource, потому что она работает
		// только внутри сущностей, управляемых сервером приложений, таких как
		// EJB и сервлеты. Java Bean'ы к этим сущностям не относятся.
		//
		DataSource dataSource = null;
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/Lesson22");
		}
		catch (NamingException e) {
			throw new HandlingException("На уровне сервера приложений не настроено подключение к БД: " + e.getMessage());
		}

		// Выполняем INSERT в базу
		try {
			Connection conn = null;
			PreparedStatement st = null;
			try {
				conn = dataSource.getConnection();
				st = conn.prepareStatement("INSERT INTO Employee (empno, ename, job_title) VALUES (?, ?, ?)");
				st.setInt(1, empnoNumber);
				st.setString(2, ename);
				st.setString(3, jobTitle);
				st.executeUpdate();
			}
			finally {
				if (st != null) st.close();
				if (conn != null) conn.close();
			}
		}
		catch (SQLException e) {
			throw new HandlingException("Ошибка базы данных: " + e.getMessage(), e);
		}
	}
}
