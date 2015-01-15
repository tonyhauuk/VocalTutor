package delete;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DelXML {
	public static Document buildXML(String uic, String courID) throws Exception  {
		Document doc = null;
		doc = DocumentHelper.createDocument();
		int cid = Integer.parseInt(courID);
		
		if (uic.length() == 32 && cid >= 1) {
			String ret = DelDBConn.retStr(uic, courID);
			
			if (ret == null || !ret.equals("1") || ret.equals("null"))  // Delete fail
				doc = DelErrXML.getErrCode(6);	
			else if (ret.equals("1")) {
				Element root = doc.addElement("delete");
				Element errno = root.addElement("errno");
				errno.setText("0");
				Element msg = root.addElement("msg");
				msg.setText("成功");
			}
		}
		else if (uic.length() != 32 || cid < 1)
			doc = DelErrXML.getErrCode(3);
		
		return doc;
	}

}
