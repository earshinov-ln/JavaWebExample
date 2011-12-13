package name.earshinov.WebExample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CONNECTIONS_STRING = "jdbc:derby://localhost:1527/Lesson22";

	// хотя empno и число, храним как строчку, чтобы возвращать
	// на страницу пустую строку, если значение не задано
	private String empno = "";
	private String ename = "";
	private String jobTitle = "";

	// Конструктор, геттеры, сеттеры

	public Employee() { }

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEname() {
		return ename;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobTitle() {
		return jobTitle;
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

		// Выполняем INSERT в базу
		// Connection создаём на каждый запрос...
		try {
			Connection conn = null;
			PreparedStatement st = null;
			try {
				conn = DriverManager.getConnection(CONNECTIONS_STRING);
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
