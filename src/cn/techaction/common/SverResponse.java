package cn.techaction.common;
//用户登录状态和消息
//返回用户的信息
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SverResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;
	private T data;

	public int getStatus() {
		return this.status;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}
	
	@JsonIgnore
	public boolean isSuccess() {
		return this.status==ResponseCode.SUCCESS.getCode();
	}
	public boolean isLogout() {
		return this.status==ResponseCode.LOGOUT.getCode();
//		返回登出信息
	}
	private SverResponse(int status) {
		this.status = status;
	}

	private SverResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private SverResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private SverResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static <T> SverResponse<T> createRespBySuccess() {
//		用户登录成功
//		System.out.println("1111111111");
//		System.out.println(ResponseCode.SUCCESS.getCode());
		return new SverResponse<>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> SverResponse<T> createRespBySuccess(T data) {
//		用户登录并返回用户信息
		//System.out.println("222222222222");
		//System.out.println(data);
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> SverResponse<T> createRespBySuccess(String msg, T data) {
//		用户登录成功并返回消息和用户信息
//System.out.println("333333333333333333");
//System.out.println(msg);
//System.out.println(new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg, data));
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> SverResponse<T> createRespBySuccessMessage(String msg) {
//		用户登录成功并返回消息
//		System.out.println("444444444444");
//		System.out.println(msg);
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg);
	}

	
	
	public static <T> SverResponse<T> createRespByError() {
		return new SverResponse<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
	}
	public static <T> SverResponse<T> createRespByError(T data) {
		return new SverResponse<>(ResponseCode.ERROR.getCode(), data);
	}

	public static <T> SverResponse<T> createRespByError(String msg, T data) {
		return new SverResponse<>(ResponseCode.ERROR.getCode(), msg, data);
	}
	public static <T> SverResponse<T> createByErrorMessage(String errorMessage) {
//		用户登录失败返回失败消息
		return new SverResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> SverResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new SverResponse<T>(errorCode, errorMessage);
	}
	public static int createByLogout(){
		return ResponseCode.LOGOUT.getCode();
	}

}
