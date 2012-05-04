package com.numrex.gwaas.storeNforward;
import org.mule.transformer.AbstractMessageAwareTransformer;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;






@SuppressWarnings("deprecation")
public class DestinationMsgTransformer extends AbstractMessageAwareTransformer {


	private static final Map<String, String> MsgtypeToTransportMap;
	static {
		
		MsgtypeToTransportMap = new HashMap<String, String> ();
		MsgtypeToTransportMap.put("message", "jms");
		MsgtypeToTransportMap.put("http", "http");
		MsgtypeToTransportMap.put("event", "mongodb");
		
		
	}
	

	@Override
	public Object transform(MuleMessage arg0, String arg1)
			throws TransformerException {
		// TODO Auto-generated method stub
		
		System.out.println("Dest Msg Transformer - " + arg0);
		
		Map<String, Object> invocationProperty = (Map<String, Object>) arg0.getInvocationProperty("properties");
		Map<String, Object> map = invocationProperty;
		String device_data_type=(String) map.get("device_data_type");
		String dest = (String) map.get("delivery_url");
		String protocl = this.MsgtypeToTransportMap.get(device_data_type.toLowerCase());
	
		
		
		if(protocl != null && dest != null)
		{
			arg0.setInvocationProperty("protocol", protocl);
			arg0.setInvocationProperty("destination", dest);
		}
	   
		
		
		
		return arg0;
	}

}
