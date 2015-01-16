package create;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CreateXML {

	public static Document buildXML(String cn, String ci, String status,
			String creater, String ts, String loc, String startTS, String dur, String capa,
			String major, String len, String price) {
		Document doc = null;
		doc = DocumentHelper.createDocument();
		Long cts = Long.parseLong(ts);
		Long sts = Long.parseLong(startTS);
		
		if (creater.length() == 32 && sts > cts && !dur.equals("0") && !capa.equals("0") && !major.equals("0")
				&& !len.equals("0") && !price.equals("0")) {
			
			int ret = CreateDBConn.result(cn, ci, status, creater, cts, loc, sts, dur, capa, major, len, price);
			
			if (ret == 1) {
				Element root = doc.addElement("create");
				Element errno = root.addElement("errno");
				errno.setText("0");
				
				Element msg = root.addElement("msg");
				msg.setText("成功");
			}
			else if (ret == 7) {
				doc = CreateErrXML.getErrCode(7);
			}
		}
		else if (creater.length() != 32 || sts <= cts || dur.equals("0") || capa.equals("0") || major.equals("0") || 
				len.equals("0") || price.equals("0")) 
			doc = CreateErrXML.getErrCode(3);
		
		
		return doc;
	}

}
