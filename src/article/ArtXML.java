package article;

import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ArtXML {
	public static Document buildXML(String uic, String level, String mount, String s) throws ClassNotFoundException, NullPointerException, SQLException {
		int amount = Integer.parseInt(mount);
		Document doc = DocumentHelper.createDocument();

		if (uic.length() == 32 && amount > 0 && level.equals("t") || level.equals("s")) {			
			Element root = doc.addElement("articles"); // root node
			if (level.equals("t")) {				
				for (int i = 1; i <= amount; i++) {
					String ret  = ArticleDBConn.obtainInfos(uic, level, i, s).replaceAll("null", " ");
					String[] r = ret.split("@!#");
					
					Element errno = root.addElement("errno");
					errno.setText("0");
					Element msg = root.addElement("msg");
					msg.setText("成功");
										
					Element article = root.addElement("article"); // article node 
					Element course = article.addElement("course");
					
					Element courseID = course.addElement("course_id"); // Add element name 
					courseID.setText(r[0]);
					Element courseName = course.addElement("course_name");
					courseName.setText(r[1]);
					Element imgUrl = course.addElement("img_url");
					imgUrl.setText(r[2].trim());
					Element courseIntro = course.addElement("course_intro");
					courseIntro.setText(r[3]);
					Element url = course.addElement("url");
					url.setText(r[4].trim());
					Element status = course.addElement("status");
					status.setText(r[5]);
					Element creater = course.addElement("creater");
					creater.setText(r[6]);
					Element cTime = course.addElement("create_time");
					cTime.setText(r[7]);
					Element courLoc = course.addElement("course_location");
					courLoc.setText(r[8]);
					Element time = course.addElement("start_time");
					time.setText(r[9]);
					Element duration = course.addElement("duration");
					duration.setText(r[10]);
					Element capacity = course.addElement("capacity");
					capacity.setText(r[11]);
					Element majorId = course.addElement("major_id");
					majorId.setText(r[12]);
					Element courLen = course.addElement("course_length");
					courLen.setText(r[13]);
					Element price = course.addElement("price");
					price.setText(r[14]);
					Element rating = course.addElement("rating");
					rating.setText(r[15].trim());
				}
			}
			else if (level.equals("s")) {
				for (int i = 1; i <= amount; i++) {
					String ret  = ArticleDBConn.obtainInfos(uic, level, i, s).replaceAll("null", " ");
					String[] r = ret.split("@!#");
					
					Element errno = root.addElement("errno");
					errno.setText("0");
					Element msg = root.addElement("msg");
					msg.setText("成功");
					
					Element article = root.addElement("article"); // article node 
					Element course = article.addElement("course");
					Element studId = course.addElement("student_id");
					studId.setText(r[0]);
					Element creater = course.addElement("creater");
					creater.setText(r[1]);
					Element courId = course.addElement("course_id");
					courId.setText(r[2]);
					Element status = course.addElement("status");
					status.setText(r[3]);
					Element rating = course.addElement("rating");
					rating.setText(r[4].trim());
					Element selectTime = course.addElement("select_time");
					selectTime.setText(r[5]);
				}
			}
		}
		else if (uic.length() != 32 || !level.equals("t") || !level.equals("s") || amount == 0)	
			doc = ArtErrXML.getErrCode(3);
		
		return doc;
	}
}