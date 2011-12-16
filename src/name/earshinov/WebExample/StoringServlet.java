package name.earshinov.WebExample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/store")
public class StoringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Store.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {

		String error = null;
		String message = null;

		try {
			int first = Utils.getIntegerArgument("1", request.getParameter("1"));
			int second = Utils.getIntegerArgument("2", request.getParameter("2"));
			request.getSession().setAttribute("CalculationResult", first + second);
			message = "Новое значение было сохранено";
		}
		catch (HandlingException e) {
			error = "Ошибка: " + e.getMessage();
			request.getSession().removeAttribute("CalculationResult");
			message = "Сохранённое значение было удалено";
		}

		request.setAttribute("error", error);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/Store.jsp").forward(request, response);
	}
}
