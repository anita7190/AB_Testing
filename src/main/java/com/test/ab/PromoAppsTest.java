package com.test.ab;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PromoAppsTest
{
	
	public static boolean testStarterKit()
	{
	String serverIp=TestConstants.IP;
	String paramsAndQuery="/apps/promo?start=0&limit=50&language=en&packages=true";
	String url=serverIp+paramsAndQuery;
	
	HashMap<String, String> headers = new HashMap<String, String>();
	 headers.put("Accept-version", "2.0.0");
	 headers.put("Authorization", "ABAPI-Custom token="+TestConstants.globalToken);
	 headers.put("Android-Version", "26");
	//headers.put("Accept-Encoding", "gzip");
	 headers.put("cache-control", "no-cache");
	 
	 IndHttpResponse response = IndHttp.httpsGet(url, headers);
     if(response.code==200) {
    	 String skJsonString = response.body;
		
    	 
    	 JSONParser parser = new JSONParser();
    	 try {
			JSONObject Obj = (JSONObject) parser.parse(skJsonString);
			
			
			JSONArray jsonarray = (JSONArray) Obj.get("list");
			
				JSONObject jObj = (JSONObject) jsonarray.get(0);
				String packname = (String) jObj.get("package");
				
				
				
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     else 
     {
    	 System.out.println("Error in fetching API");
     }
	
	 
	 
	 
	 
	 return false;
	
	}
	
	 
}


