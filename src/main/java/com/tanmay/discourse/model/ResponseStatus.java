package com.tanmay.discourse.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseStatus {
	
	private String status;
	private List<String> messages;
	
	public ResponseStatus() {
		super();
		this.messages = new ArrayList<>();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
