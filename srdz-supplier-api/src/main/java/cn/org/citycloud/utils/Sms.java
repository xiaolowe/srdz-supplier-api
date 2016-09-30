package cn.org.citycloud.utils;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Sms {
	
	/**
	 * 发送短信
	 * @param phone
	 * @param code
	 * @return
	 */
	public static String sendSms(String phone,String code,String message){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-48ff368ba05b496e962046cbf7917a71"));
        WebResource webResource = client.resource(
            "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", phone);
        formData.add("message", message);
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
        post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }
	
	
	/**
	 * 短信状态
	 * @return
	 */
	private  String smsStatus(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-48ff368ba05b496e962046cbf7917a71"));
        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        ClientResponse response =  webResource.get( ClientResponse.class );
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(status);
        //System.out.print(textEntity);
        return textEntity;
    }
	
	/** 
	  * 创建指定数量的随机字符串 
	  * @param numberFlag 是否是数字 
	  * @param length 
	  * @return 
	  */  
	 public String createRandom(boolean numberFlag, int length){  
	  String retStr = "";  
	  String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";  
	  int len = strTable.length();  
	  boolean bDone = true;  
	  do {  
	   retStr = "";  
	   int count = 0;  
	   for (int i = 0; i < length; i++) {  
	    double dblR = Math.random() * len;  
	    int intR = (int) Math.floor(dblR);  
	    char c = strTable.charAt(intR);  
	    if (('0' <= c) && (c <= '9')) {  
	     count++;  
	    }  
	    retStr += strTable.charAt(intR);  
	   }  
	   if (count >= 2) {  
	    bDone = false;  
	   }  
	  } while (bDone);  
	  
	  return retStr;  
	 }  

}
