package com.test.ab;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONCreate {

	public static void main(String[] args) {
		String message;
		JSONObject json = new JSONObject();
		

		JSONArray array = new JSONArray();
		array.add("com.docsapp.patients");
		array.add("com.xwhfh.abc");

		json.put("package", array);
		System.out.println(json);

		message = json.toString();
		System.out.println("JSON body : \n"+message);

	}

}
