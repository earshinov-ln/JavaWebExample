package name.earshinov.WebExample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/store")
public class StoringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {

		response.setContentType("text/plain; charset=UTF-8");
	   	PrintWriter out = response.getWriter();

		try {
			int first = Utils.getIntegerArgument("1", request.getParameter("1"));
			int second = Utils.getIntegerArgument("2", request.getParameter("2"));
			request.getSession().setAttribute("CalculationResult", first + second);
			out.println("Новое значение было сохранено");
		}
		catch (HandlingException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("Ошибка: " + e.getMessage());
			request.getSession().removeAttribute("CalculationResult");
			out.println("Сохранённое значение было удалено");
		}
	}

}
