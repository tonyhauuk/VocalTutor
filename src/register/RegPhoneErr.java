package register;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class RegPhoneErr {
	public static Document getErrCode(int err, String phone) {
		Document doc = buildXML(err, phone);
		return doc;
	}

	private static Document buildXML(int err, String phone) {
		Document doc = DocumentHelper.createDocument();
		if (err == 1) {
			Element root = doc.addElement("error");
			Element errno = root.addElement("errno");
			errno.setText("1");
			Element msg = root.addElement("msg");
			msg.setText("密码错误");
		} else if (err == 2) {
			Element root = doc.addElement("error");
			Element errno = root.addElement("errno");
			errno.setText("2");
			Element msg = root.addElement("msg");
			msg.setText("参数不完整");
		} else if (err == 3) {
			Element root = doc.addElement("error");
			Element errno = root.addElement("errno");
			errno.setText("3");
			Element msg = root.addElement("msg");
			msg.setText("参数错误");
		} else if (err == 4) {
			Element root = doc.addElement("error");
			Element errno = root.addElement("errno");
			errno.setText("4");
			Element pNum = root.addElement("phone");
			pNum.setText(phone);
			Element msg = root.addElement("msg");
			msg.setText("该手机号码已被注册");
		}
		return doc;
	}
}

