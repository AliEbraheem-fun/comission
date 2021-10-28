package com.ali.test.commission.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Payment {
	@NotNull
	@NotEmpty
	@Pattern(regexp="^[+][7][0-9]{10}")
	private String id;
	
	@Min(value=0)
	@Max(value=1000000)
	private double amount;
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
    
	private String comment;
	public Payment() {
	}
	public Payment(String id, double amount, LocalDateTime dateTime, String comment) {
		this.id = id;
		this.amount = amount;
		this.dateTime = dateTime;
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", dateTime=" + dateTime + ", comment=" + comment + "]";
	}
	
}
