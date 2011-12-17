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

	// хотя empno и число, храним как строчку, чтобы иметь возможность
	// самим обрабатывать NumberFormatException
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
