package com.example.demo;

public class CustomResponse {
	private int code;
	private String status;
	private String message;
	private Object data;
	
	public CustomResponse() {
		super();
	}
	public CustomResponse(int code, String status, String message, Object data) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public CustomResponse notFoundException()
	{
		CustomResponse response = new CustomResponse(404, "NOT_FOUND", "error: found nothing", null);
		
		return response;
	}
	
	public CustomResponse serverErrorException()
	{
		CustomResponse response = new CustomResponse(500, "INTERNAL_SERVER_ERROR", "error: server error", null);
		
		return response;
	}
	
	public CustomResponse requestCompleted(Object data)
	{
		CustomResponse response = new CustomResponse(200, "OK", "request completed", data);
		
		return response;
	}
	public CustomResponse customNumberFormatException() {
		// TODO Auto-generated method stub
		return null;
	}
}
