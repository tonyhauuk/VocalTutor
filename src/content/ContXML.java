package content;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ContXML {

	public static Document buildXML(String cid, String level) {
		Document doc = DocumentHelper.createDocument();
		if (!cid.equals("0") && level.equals("t")) {
			String ret = ContDBConn.result(level, cid).replaceAll("null", " ");
			String[] r = ret.split("@!#");
			
			Element root = doc.addElement("content");
			Element errno = root.addElement("errno");
			errno.setText("0");
			Element msg = root.addElement("msg");
			msg.setText("成功");
			Element article = root.addElement("article"); // article node
			
			Element courID = article.addElement("course_name");
			courID.setText(r[0]);
			Element imgUrl = article.addElement("img_url");
			imgUrl.setText(r[1].trim());
			Element ci = article.addElement("course_intro");
			ci.setText(r[2]);
			Element url = article.addElement("url");
			url.setText(r[3].trim());
			Element status = article.addElement("status");
			status.setText(r[4]);
			Element creater = article.addElement("creater");
			creater.setText(r[5]);
			Element ctime = article.addElement("create_time");
			ctime.setText(r[6]);
			Element loc = article.addElement("course_location");
			loc.setText(r[7]);
			Element stime = article.addElement("start_time");
			stime.setText(r[8]);
			Element dur = article.addElement("duration");
			dur.setText(r[9]);
			Element capa = article.addElement("capacity");
			capa.setText(r[10]);
			Element major = article.addElement("major_id");
			major.setText(r[11]);
			Element len = article.addElement("course_length");
			len.setText(r[12]);
			Element price = article.addElement("price");
			price.setText(r[13]);
			Element rating = article.addElement("rating");
			rating.setText(r[14].trim());
		}
		else if (!cid.equals("0") &&  level.equals("s")) {
			String ret = ContDBConn.result(level, cid).replaceAll("null", " ");
			String[] r = ret.split("@!#");
			
			Element root = doc.addElement("content");
			Element errno = root.addElement("errno");
			errno.setText("0");
			Element msg = root.addElement("msg");
			msg.setText("成功");
			Element article = root.addElement("article"); // article node
			
			Element sID = article.addElement("student_id");
			sID.setText(r[0]);
			Element creater = article.addElement("creater");
			creater.setText(r[1]);
			Element scid = article.addElement("course_id");
			scid.setText(r[2]);
			Element status = article.addElement("status");
			status.setText(r[3]);
			Element rating = article.addElement("rating");
			rating.setText(r[4].trim());
			Element slctime = article.addElement("select_time");
			slctime.setText(r[5]);
		}
		else if (cid.equals("0") || !level.equals("s") || !level.equals("t"))
			doc = ContErrXML.getErrCode(3);
		return doc;
	}
}
