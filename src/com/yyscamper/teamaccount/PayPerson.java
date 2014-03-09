package com.yyscamper.teamaccount;

public class PayPerson {
	public String Name;
	public long PayCount;
	public long AttendCount;
	public double Debet;
	
	public PayPerson(String name) {
		Name = name;
		PayCount = 0;
		AttendCount = 0;
		Debet = 0.0;
	}
}
