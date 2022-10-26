package com.example.demo.Feedback;

import java.sql.Date;

import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
public class Feedback {
	@Id
	private int fid;
	private String email;
	private int pid;
	private float rating;
	private String feedback;
	private String medialink;
	private Date fdate;

	public Feedback() {
		super();
	}

	public Feedback(int fid, String email, int pid, float rating, String feedback, String medialink, Date fdate) {
		super();
		this.fid = fid;
		this.email = email;
		this.pid = pid;
		this.rating = rating;
		this.feedback = feedback;
		this.medialink = medialink;
		this.fdate = fdate;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getMedialink() {
		return medialink;
	}

	public void setMedialink(String medialink) {
		this.medialink = medialink;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Object[] toArray() {
		return new Object[] { this.fid, this.email, this.pid, this.rating, this.feedback, this.medialink, this.fdate };
	}

	public Object[] toArrayForUpdate() {
		return new Object[] { this.email, this.pid, this.rating, this.feedback, this.medialink, this.fdate, this.fid };
	}

	public Object[] toArrayForDelete() {
		return new Object[] { this.email, this.pid, this.rating, this.feedback, this.medialink, this.fdate, this.fid };
	}

}
