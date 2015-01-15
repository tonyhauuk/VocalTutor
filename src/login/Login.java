package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");

		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;

		if (phone != null && pwd != null && !phone.equals("") && !pwd.equals("")) {
			try {			
				doc = LoginXML.buildXML(phone, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.close();
		}
		else if (phone == null || pwd == null || phone.equals("") || pwd.equals("")) {
			doc = LoginErrXML.getErrCode(2);
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
