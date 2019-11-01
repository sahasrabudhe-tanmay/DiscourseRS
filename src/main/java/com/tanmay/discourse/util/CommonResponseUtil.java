package com.tanmay.discourse.util;

import java.util.List;

import com.tanmay.discourse.model.ResponseStatus;

public class CommonResponseUtil {
	
	public static ResponseStatus buildSuccessResponseStatus() {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("SUCCESS");
		return responseStatus;
	}
	
	public static ResponseStatus buildFailureResponseStatus(List<String> messages) {
		ResponseStatus responseStatus = new ResponseStatus();
		
		responseStatus.setStatus("FAILURE");
		responseStatus.setMessages(messages);
		
		return responseStatus;
	}
	
}
