package com.yyscamper.teamaccount;

import java.util.*;

public class PayEntry {
	public double Money;
	public Date PayTime;
	public PayPerson Payer;
	public ArrayList<PayPerson> AttendPersons;
	public String Place;
	public String Description;
	
	public PayEntry() {
		Money = 0.0;
		PayTime = new Date();
		Payer = null;
		AttendPersons = new ArrayList<PayPerson>();
		Place = "";
		Description = "";
	}
	
	public PayEntry(double money, String payName, String[] attPersons, Date t, String place, String desc) {
		this.Money = money;
		this.PayTime = t;
		this.Payer = PayManager.FindPerson(payName);
		this.AttendPersons = new ArrayList<PayPerson>();
		this.Place = place;
		this.Description = desc;
		for (int i = 0; i < attPersons.length; i++) {
			PayPerson p = PayManager.FindPerson(attPersons[i]);
			if (p != null) {
				this.AttendPersons.add(p);
			}
			else {
				PayPerson pnew = new PayPerson(attPersons[i]);
				if (PayManager.AddPerson(pnew))
					this.AttendPersons.add(pnew);
			}
			
		}
	}
}
