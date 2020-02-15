package kr.ac.smu.cs.comnet.vo;

import java.sql.Date;

public class BoardVO {
	int rowNum;
	int bid;
	int uid;
	String title;
	String content;
	Date deadline; 
	int partner_limit;
	String contact;
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getPartner_limit() {
		return partner_limit;
	}
	public void setPartner_limit(int partner_limit) {
		this.partner_limit = partner_limit;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
