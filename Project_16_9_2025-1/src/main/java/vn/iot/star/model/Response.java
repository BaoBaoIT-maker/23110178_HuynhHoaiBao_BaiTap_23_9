package vn.iot.star.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
private Boolean status;
private String message;
private Object data;
public Boolean getStatus() {
	return status;
}
public Response() {}

// Constructor có tham số
public Response(boolean success, String message, Object data) {
    this.success = success;
    this.message = message;
    this.data = data;
}
public void setStatus(Boolean status) {
	this.status = status;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
private boolean success;  // trạng thái true/false
}