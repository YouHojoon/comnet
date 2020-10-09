package kr.ac.smu.cs.comnet.vo;

public class MessageVO {
	
	public static final String APPLY_USER = "프로젝트에  지원자가 있습니다.";
	public static final String APPROVAL_USER = "프로젝트에 승인 되었습니다.";
	public static final String REJECT_USER = "프로젝트에 거부 되었습니다.";
	public static final String ELIMINATE_USER = "프로젝트에 추방 되었습니다.";
	
	private int mid;
	private int uid;
	private String msg;
	
	public MessageVO(int uid, String msg) {
		this.uid = uid;
		this.msg = msg;
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
