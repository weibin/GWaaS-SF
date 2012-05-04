package com.numrex.gwaas.storeNforward;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mule.transformer.AbstractMessageAwareTransformer;

import java.util.HashMap;
import java.util.Map;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;



public class JsonMsgTransformer extends AbstractMessageAwareTransformer {

	

	@Override
	public Object transform(MuleMessage arg0, String arg1)
			throws TransformerException {
		// TODO Auto-generated method stub
		
		System.out.println("Json Msg Transformer - " + arg0);
		
		String a="";
		try {
			a = arg0.getPayloadAsString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JSONObject req = JSONObject.fromObject(a);

		//JSONObject  locs     = req.getJSONObject("readings");

	
		JSONObject readings = req.getJSONObject("readings");
		JSONObject reading = readings.getJSONObject("reading");
		System.out.println("JSONObject reading = " + reading);
		JSONArray deviceinfo = reading.getJSONArray("device");
		JSONArray elements = reading.getJSONArray("element");
		
		
		HashMap<String, String> keymap = new HashMap<String,String>();
		for(int i=0; i<deviceinfo.size(); i++)
		{
			String name = deviceinfo.getJSONObject(i).getString("name");
			String value = deviceinfo.getJSONObject(i).getString("value");
            keymap.put(name, value);
		}	
			
		for(int i=0; i<elements.size(); i++)	
		{
			
			String name = elements.getJSONObject(i).getString("name");
			String value = elements.getJSONObject(i).getString("value");
            keymap.put(name, value);
		}
		
		System.out.println("keymap = " + keymap);
		
		
		try {
			System.out.println("JSon Msg Transformer Msg =  " + arg0.getPayload());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return keymap;

		}
	}
