package com.test.ab;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ResponseJsonParse 
{

	public void parseResonsebody(IndHttpResponse res) throws ParseException
	{
		Object obj = new JSONParser().parse(res.body);
		JSONObject responsebody = (JSONObject)obj;
		String token = 
		
		
	}
}
