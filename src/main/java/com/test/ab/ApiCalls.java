package com.test.ab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;


import com.test.ab.IndHttp;
import com.test.ab.IndHttpResponse;

public class ApiCalls {
	public static String globalToken = null;
	String serverIp = "https://sp-api.appbazaar.com";
	String hardwreID = "AM%2FbgGavRaJsQSy%2BzdPw1CIRFoR0CWO9bf9woqV%2FG8E%3D%0A";
	
	String token = null;

	public boolean getAuth() {
		String paramsAndQuery = "/auth/access?resolutiony=1396&resolutionx=720&api=27&version=660100110&hardware_id="
				+ hardwreID + "&iuid=";
		String url = serverIp + paramsAndQuery;
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-version", "2.0.0");
		headers.put("Authorization", "ABAPI-Custom token=");
		headers.put("Android-Version", "26");
		// headers.put("Accept-Encoding", "gzip");
		headers.put("cache-control", "no-cache");

		IndHttpResponse response = IndHttp.httpsGet(url, headers);
		if (response.code == 200) {
			//System.out.println(response.body);
			 String authJsonString = response.body;
				
	    	 
	    	 JSONParser parser = new JSONParser();
			try {
				JSONObject responseJson = (JSONObject)parser.parse(authJsonString);
				//System.out.println("Response Body : " + responseJson);
				
               JSONObject obj= (JSONObject) responseJson.get("token");
                token= (String) obj.get("token");
                System.out.println("\n"+token);
				if (token != null) {

					System.out.println("Auth Api is working fine and retrieved token is " + token + " \n\n");
					globalToken = token;
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("Error fetching API");
		}
		return false;
	}

	public boolean getbanners() {

		String paramsAndQuery = "/apps/banner";
		String url = serverIp + paramsAndQuery;

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-version", "2.0.0");
		headers.put("Authorization", "ABAPI-Custom token=" + globalToken);
		headers.put("Android-Version", "26");
		// headers.put("Accept-Encoding", "gzip");
		headers.put("cache-control", "no-cache");

		IndHttpResponse response = IndHttp.httpsGet(url, headers);
		//System.out.println(response.body);

		if (response.code == 200) {
			System.out.println("Banners Api is working fine.\n  Response received: " + response.body + "\n\n");
			//System.out.println(response.body);
			// Parse json and check collections list
			return true;
		} else {
			System.out.println("Error fetching API");
		}
		return false;

	}

	public boolean testStarterKit() {

		String paramsAndQuery = "/apps/promo?start=0&limit=50&language=en&packages=true";
		String url = serverIp + paramsAndQuery;

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-version", "2.0.0");
		headers.put("Authorization", "ABAPI-Custom token=" + globalToken);
		headers.put("Android-Version", "26");
		// headers.put("Accept-Encoding", "gzip");
		headers.put("cache-control", "no-cache");

		IndHttpResponse response = IndHttp.httpsGet(url, headers);
		if (response.code == 200) {
			String skJsonString = response.body;
		

			JSONParser parser = new JSONParser();
			try {
				JSONObject Obj = (JSONObject) parser.parse(skJsonString);
				JSONArray jsonarray = (JSONArray) Obj.get("list");
				ArrayList<String> pkg = new ArrayList<String>();
                
				for (int i=0;i<jsonarray.size();i++ )
				{
				JSONObject jObj = (JSONObject) jsonarray.get(i);
				String packagename = (String) jObj.get("package");
				
				pkg.add(packagename);
				}
				System.out.println("Number of apps in Starter Kit : "+ pkg.size());
				System.out.println(pkg);
				
				getAppDetails();
	            
				
                
				return true;
			}

			catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Error fetching API");
		}
		return false;

	}

    public void getAppDetails() 
    {
    	String paramsAndQuery = "/apps/details?language=en&country=IN";
		String url = serverIp + paramsAndQuery;

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-version", "2.0.0");
		headers.put("Authorization", "ABAPI-Custom token=" + globalToken);
		headers.put("Android-Version", "26");
		// headers.put("Accept-Encoding", "gzip");
		headers.put("cache-control", "no-cache");
    
		
        String body ="{\n\t\"packages\":[\n\t\t\"com.jetstartgames.chess\","
        		+ "\n\t\t\"com.zhiliaoapp.musically\"\n\t]\n}";
		IndHttpResponse response = IndHttp.httpsPost(url,headers,body);
		System.out.println("App Details :" + response.body);
		String  Appdetails = response.body;

		JSONParser parser = new JSONParser();
		try {
			JSONObject responseJson = (JSONObject)parser.parse(Appdetails);
			JSONArray jsonarray = (JSONArray)responseJson.get("list");
			int l = jsonarray.size();
			ArrayList<Long> seqIds = new ArrayList<Long>();
            
			for (int i=0;i<l;i++)
            {
			JSONObject jObj = (JSONObject) jsonarray.get(i);
		    Long seqid = (Long) jObj.get("seq_id");
		    seqIds.add(seqid);
            }
			System.out.println(seqIds);
			
				getDownloadLink(seqIds);
					
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
    	
    	
    }

	private void getDownloadLink(ArrayList<Long> seqIds) {
		for(int i =0;i<seqIds.size();i++) {
		Long s = seqIds.get(i);
		String paramsAndQuery = "/device/apps/download/"+s;
		String url = serverIp + paramsAndQuery;
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-version", "2.0.0");
		headers.put("Authorization", "ABAPI-Custom token=" + globalToken);
		headers.put("Android-Version", "26");
		// headers.put("Accept-Encoding", "gzip");
		headers.put("cache-control", "no-cache");
		String body ="{\"source\":\"indus\"}";
		IndHttpResponse response = IndHttp.httpsPost(url,headers,body);
		//System.out.println("Download Details :" + response.body);
		String  DownloadDetails = response.body;
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject responseJson = (JSONObject)parser.parse(DownloadDetails);
			String downloadLink = (String) responseJson.get("download_link");
			System.out.println("Link " + (i+1)+ downloadLink);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	}


}
