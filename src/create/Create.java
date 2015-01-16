package create;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cName = request.getParameter("cname");
		String cIntro = request.getParameter("intro");
		String status = request.getParameter("status");
		String creater = request.getParameter("uic");
		String ts = request.getParameter("createtime");
		String loc = request.getParameter("loc");
		String startTS = request.getParameter("starttime");
		String dur = request.getParameter("duration");
		String capa = request.getParameter("cap");
		String major = request.getParameter("major");
		String len = request.getParameter("len");
		String price = request.getParameter("price");
		
		// Return XML
		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;
		
		if (!cName.equals("") && !cIntro.equals("") && !status.equals("") && !creater.equals("") && !ts.equals("") && !loc.equals("") &&
				!startTS.equals("") && !dur.equals("") && !capa.equals("") && !major.equals("") && !len.equals("") && !price.equals("") && cName != null && cIntro != null
				&& status != null && creater != null && ts != null && loc != null && startTS != null && dur != null && capa != null && major != null && len != null 
				&& price != null) {
			try {
				doc = CreateXML.buildXML(cName, cIntro, status, creater, ts, loc, startTS, dur, capa, major, len, price);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
		else if (cName.equals("") || cIntro.equals("") || status.equals("") || creater.equals("") || ts.equals("") || loc.equals("") ||
				dur.equals("") || capa.equals("") || major.equals("") || len.equals("") || price.equals("") || cName == null || cIntro == null
				|| status == null || creater == null || ts == null || loc == null || dur == null || capa == null || major == null || len == null 
				|| price == null){
			doc = CreateErrXML.getErrCode(2);
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
