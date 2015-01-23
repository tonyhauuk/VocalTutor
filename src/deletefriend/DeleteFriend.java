package deletefriend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class DeleteFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uic = request.getParameter("uic");
		String who = request.getParameter("who");
		
		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;
		
		if (!uic.equals("") && !who.equals("") && uic != null && who != null) {
			try {
				doc = DeleteXML.buildXML(uic, who);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
		else if (uic.equals("") || who.equals("") || uic == null || who == null) {
			doc = DeleteErrXML.getErrCode(2);
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
