package com.test.ab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class JsonRead {

	public static void main(String[] args) {

		JSONParser jsonParser = new JSONParser();

		try {
			FileReader reader = new FileReader("sample.json");
			// Read JSON file
			JSONObject Obj = (JSONObject) jsonParser.parse(reader);

			JSONArray jsonarray = (JSONArray) Obj.get("list");
			int l = jsonarray.size();
			ArrayList<Long> seqIds = new ArrayList<Long>();
            
			for (int i=0;i<l;i++)
            {
			JSONObject jObj = (JSONObject) jsonarray.get(i);
		    Long seqid = (Long) jObj.get("seq_id");
		    seqIds.add(seqid);
            }
			System.out.println(seqIds.get(0));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
