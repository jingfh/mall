package cn.techaction.pojo;


import java.util.Date;


public class Address {
	private Integer  id;
	private Integer user_id;
	private String name;
	private String phone;//固定电话
	private String mobile;//移动电话
	private String province;//省
	private String city;//城市
	private String district;//区
	private String addr;//详细地址
	private String zip;//邮编
	private Integer default_addr;//默认地址？1是0非
	private Date created;
	private Date updated;
	private Integer del_state;//删除？1删0否
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getDefault_addr() {
		return default_addr;
	}
	public void setDefault_addr(Integer default_addr) {
		this.default_addr = default_addr;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getDel_state() {
		return del_state;
	}
	public void setDel_state(Integer del_state) {
		this.del_state = del_state;
	}
	
}
