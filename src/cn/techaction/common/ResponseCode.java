package cn.techaction.common;

public enum ResponseCode {
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"ERROR"),
	LOGOUT(3,"LOGOUT"),
	UNLOGIN(2,"UNLOGIN");
	
	private final int code;//ÓÃ»§µÇÂ¼×´Ì¬´úÂë
	private final String desc;///ÃèÊö
	
	private ResponseCode(int code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}

}
