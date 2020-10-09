package kr.ac.smu.cs.comnet.vo;

public class MessageVO {
	
	public static final String APPLY_USER = "������Ʈ��  �����ڰ� �ֽ��ϴ�.";
	public static final String APPROVAL_USER = "������Ʈ�� ���� �Ǿ����ϴ�.";
	public static final String REJECT_USER = "������Ʈ�� �ź� �Ǿ����ϴ�.";
	public static final String ELIMINATE_USER = "������Ʈ�� �߹� �Ǿ����ϴ�.";
	
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
