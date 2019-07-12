package cn.techaction.common;
//�û���¼״̬����Ϣ
//�����û�����Ϣ
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
//		���صǳ���Ϣ
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
//		�û���¼�ɹ�
//		System.out.println("1111111111");
//		System.out.println(ResponseCode.SUCCESS.getCode());
		return new SverResponse<>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> SverResponse<T> createRespBySuccess(T data) {
//		�û���¼�������û���Ϣ
		//System.out.println("222222222222");
		//System.out.println(data);
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> SverResponse<T> createRespBySuccess(String msg, T data) {
//		�û���¼�ɹ���������Ϣ���û���Ϣ
//System.out.println("333333333333333333");
//System.out.println(msg);
//System.out.println(new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg, data));
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> SverResponse<T> createRespBySuccessMessage(String msg) {
//		�û���¼�ɹ���������Ϣ
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
//		�û���¼ʧ�ܷ���ʧ����Ϣ
		return new SverResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}

	public static <T> SverResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new SverResponse<T>(errorCode, errorMessage);
	}
	public static int createByLogout(){
		return ResponseCode.LOGOUT.getCode();
	}

}
