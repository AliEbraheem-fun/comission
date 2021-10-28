package com.ali.test.commission.entity;

public class Commission {
	private double commission;
	private int year;
	private int month;
	public Commission(double commission) {
		this.commission = commission;
	}

	public Commission(double commission, int year, int month) {
		this.commission = commission;
		this.year = year;
		this.month = month;
	}

	public Commission() {
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Commission [commission=" + commission + ", year=" + year + ", month=" + month + "]";
	}
	
}
