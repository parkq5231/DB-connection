package exam1;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/insertSlip")
public class SlipInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SlipInsert() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = request.getParameter("param");
		//API
		SlipAPI slipAPI = new SlipAPI();
		Map map = slipAPI.insertSlip(param);
		// Gson
		Gson gson = new Gson();
		String result = gson.toJson(map);

		response.getWriter().print(result);
	}

}
