package JavaBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class HotelOperateBean {
	
	private List<HotelBean> getHotelByCondition(String url, String location, String range1_, String range2_) {
		OMElement result = null;  
		try {  

            Options options = new Options();   
            EndpointReference targetEPR = new EndpointReference(url);  
            options.setTo(targetEPR);  
  
            ServiceClient sender = new ServiceClient();  
            sender.setOptions(options);  
  
            OMFactory fac = OMAbstractFactory.getOMFactory();  
            String tns = "http://dao.com";  
 
            OMNamespace omNs = fac.createOMNamespace(tns, "");

            OMElement method = fac.createOMElement("queryHotelByAll", omNs);
            
            OMElement symbol1 = fac.createOMElement("location", omNs);
            symbol1.addChild(fac.createOMText(symbol1, location));
            method.addChild(symbol1);
            
            OMElement symbol2 = fac.createOMElement("range1_", omNs);
            symbol2.addChild(fac.createOMText(symbol2, range1_));
            method.addChild(symbol2);
            
            OMElement symbol3 = fac.createOMElement("range2_", omNs);
            symbol3.addChild(fac.createOMText(symbol3, range2_));
            method.addChild(symbol3);
            
            method.build();
  
            result = sender.sendReceive(method);
            List<HotelBean> hotellist = new ArrayList<HotelBean>();
            
            Iterator iterator =  result.getChildElements();
            while (iterator.hasNext()) {
            	OMElement omNode = (OMElement) iterator.next();
            	Iterator iterator2 = omNode.getChildElements();
            	HotelBean tmpHotelBean = new HotelBean();
            	while(iterator2.hasNext()) {
            		OMElement omNode2 = (OMElement) iterator2.next();
            		switch (omNode2.getLocalName()) {
            		case "id":
            			tmpHotelBean.setId(omNode2.getText());
            			break;
					case "address":
						tmpHotelBean.setAddress(omNode2.getText());
						break;
					case "location":
						tmpHotelBean.setLocation(omNode2.getText());
						break;
					case "type":
						tmpHotelBean.setRoomtype(omNode2.getText());
						break;
					case "name":
						tmpHotelBean.setName(omNode2.getText());
						break;
					case "star":
						tmpHotelBean.setStar(omNode2.getText());
						break;
					case "price":
						tmpHotelBean.setPrice(omNode2.getText());
						break;
					default:
						break;
					}
            	}
            	hotellist.add(tmpHotelBean);
            }
            return hotellist;    
        } catch (AxisFault axisFault) {  
            axisFault.printStackTrace();  
            return new ArrayList<HotelBean>();
        }  
	}
	
	private List<HotelBean> getHotelByCompany(String url) {
		OMElement result = null;  
		try {  

            Options options = new Options();   
            EndpointReference targetEPR = new EndpointReference(url);  
            options.setTo(targetEPR);  
  
            ServiceClient sender = new ServiceClient();  
            sender.setOptions(options);  
  
            OMFactory fac = OMAbstractFactory.getOMFactory();  
            String tns = "http://dao.com";  
 
            OMNamespace omNs = fac.createOMNamespace(tns, "");

            OMElement method = fac.createOMElement("queryHotel", omNs);
            method.build();
  
            result = sender.sendReceive(method);
            List<HotelBean> hotellist = new ArrayList<HotelBean>();
            
            Iterator iterator =  result.getChildElements();
            while (iterator.hasNext()) {
            	OMElement omNode = (OMElement) iterator.next();
            	Iterator iterator2 = omNode.getChildElements();
            	HotelBean tmpHotelBean = new HotelBean();
            	while(iterator2.hasNext()) {
            		OMElement omNode2 = (OMElement) iterator2.next();
            		switch (omNode2.getLocalName()) {
            		case "id":
            			tmpHotelBean.setId(omNode2.getText());
            			break;
					case "address":
						tmpHotelBean.setAddress(omNode2.getText());
						break;
					case "location":
						tmpHotelBean.setLocation(omNode2.getText());
						break;
					case "type":
						tmpHotelBean.setRoomtype(omNode2.getText());
						break;
					case "name":
						tmpHotelBean.setName(omNode2.getText());
						break;
					case "star":
						tmpHotelBean.setStar(omNode2.getText());
						break;
					case "price":
						tmpHotelBean.setPrice(omNode2.getText());
						break;
					default:
						break;
					}
            	}
            	hotellist.add(tmpHotelBean);
            }
            return hotellist;    
        } catch (AxisFault axisFault) {  
            axisFault.printStackTrace();  
            return null;
        }  
	}
	
	public List<HotelBean> getAllHotels() {
		String url = "http://10.79.10.65:8080/axis2/services/HepingHotelServices?wsdl";
		String url2 = "http://10.79.10.65:8080/axis2/services/RujiaHotelServices?wsdl";
		String url3 = "http://10.79.10.65:8080/axis2/services/ShouduHotelServices?wsdl";
		String url4 = "http://10.79.10.65:8080/axis2/services/XierdunHotelServices?wsdl";
		
		List<HotelBean> hotellist = getHotelByCompany(url);
		List<HotelBean> hotellist2 = getHotelByCompany(url2);
		List<HotelBean> hotellist3 = getHotelByCompany(url3);
		List<HotelBean> hotellist4 = getHotelByCompany(url4);
		
		hotellist.addAll(hotellist2);
		hotellist.addAll(hotellist3);
		hotellist.addAll(hotellist4);
		
		return hotellist;
		
	}
	
	public List<HotelBean> getAllHotelsByCondition(String location, String range1_, String range2_) {
		String url = "http://10.79.10.65:8080/axis2/services/HepingHotelServices?wsdl";
		String url2 = "http://10.79.10.65:8080/axis2/services/RujiaHotelServices?wsdl";
		String url3 = "http://10.79.10.65:8080/axis2/services/ShouduHotelServices?wsdl";
		String url4 = "http://10.79.10.65:8080/axis2/services/XierdunHotelServices?wsdl";
		
		List<HotelBean> hotellist = getHotelByCondition(url, location, range1_, range2_);
		List<HotelBean> hotellist2 = getHotelByCondition(url2, location, range1_, range2_);
		List<HotelBean> hotellist3 = getHotelByCondition(url3, location, range1_, range2_);
		List<HotelBean> hotellist4 = getHotelByCondition(url4, location, range1_, range2_);
		
		hotellist.addAll(hotellist2);
		hotellist.addAll(hotellist3);
		hotellist.addAll(hotellist4);
		
		return hotellist;
		
	}
}
