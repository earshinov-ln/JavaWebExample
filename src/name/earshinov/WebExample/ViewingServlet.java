package name.earshinov.WebExample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {

    	Object value = request.getSession().getAttribute("CalculationResult");
	   	String message = value != null ? value.toString() : "Нет сохранённого значения";
	   	request.setAttribute("message", message);
	   	request.getRequestDispatcher("/WEB-INF/View.jsp").forward(request, response);
	}

}
