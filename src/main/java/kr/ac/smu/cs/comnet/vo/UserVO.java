package kr.ac.smu.cs.comnet.vo;

public class UserVO {
	private int uid;
	private String email;
	private String name;
	private String password;
	private String memo;
	private String phone;
	
	public UserVO(int uid, String email, String name,String memo, String phone) {
		this.uid = uid;
		this.email = email;
		this.name = name;
		this.memo = memo;
		this.phone = phone;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
