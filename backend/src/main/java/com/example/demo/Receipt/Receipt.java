package com.example.demo.Receipt;

import java.sql.Date;

import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Table(name = "receipt")
public class Receipt {

	@Id
	private Long rid;
	private String email;
	private int stid;
	private String payment;
	private String address;
	private String note;
	private Date rdate;
	private String receiptstate ;
	
	
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getReceiptstate() {
		return receiptstate;
	}
	public void setReceiptstate(String receiptstate) {
		this.receiptstate = receiptstate;
	}
	
	 public Object[] toArrayForUpdate()
	    {
	        return new Object[]{this.email, this.stid, this.payment, this.address, this.note, this.rdate, this.receiptstate, this.rid};
	    }
}
