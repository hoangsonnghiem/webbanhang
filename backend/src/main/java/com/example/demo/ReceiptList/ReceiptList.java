package com.example.demo.ReceiptList;

public class ReceiptList {

	private Long pid;
	private int rid;
	private int num;
	private int total;
	
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	 public Object[] toArrayForUpdate()
	    {
	        return new Object[]{this.pid, this.rid, this.num, this.total};
	    }
	
	
	
}
