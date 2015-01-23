package deletefriend;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import addfriend.AddDBConn;
import addfriend.AddErrXML;

public class DeleteXML {
	public static Document buildXML(String uic, String who) {
		Document doc = DocumentHelper.createDocument();
		
		if (uic.length() == 32 && who.length() == 32) {
			AddDBConn dbc = new AddDBConn();
			int ret = dbc.result(uic, who);
			
			if (ret == 1) {
				Element root = doc.addElement("result");
				Element errno = root.addElement("errno");
				errno.setText("0");
				Element msg = root.addElement("msg");
				msg.setText("成功");
			}
			else if (ret != 1)
				doc = AddErrXML.getErrCode(9);		
		}
		else if (uic.length() != 32 || who.length() != 32)
			doc = AddErrXML.getErrCode(3);
		return doc;
	}
}
