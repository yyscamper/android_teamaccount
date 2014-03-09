package com.yyscamper.teamaccount;

import java.util.*;

public class PayManager {
	private static Hashtable<String, PayPerson> _allPersons;
	private static ArrayList<PayEntry> _allPayEntries;
	private static double _totalConsumedMoney;
	
	public static String[] GetAllPersonNames() {
		return new String[] {"Felix", "Andy", "Alfred", "Simon", "Leo", "Wayne", "Wenrey", "Tao", "Peter", "Lyne"};
	}
	
	public static Boolean AddPerson(PayPerson p) {
		if (_allPersons.containsKey(p.Name))
			return false;
		_allPersons.put(p.Name,  p);
		return true;
	}
	
	public static Boolean DeletePerson(String name) {
		if (!_allPersons.containsKey(name))
			return false;
		_allPersons.remove(name);
		return true;
	}
	
	public static PayPerson FindPerson(String name) {
		if (_allPersons.containsKey(name))
			return _allPersons.get(name);
		else
			return null;
	}
	
	public static int GetPersonCount() {
		return _allPersons.size();
	}
	
	public static int GetPayCount() {
		return _allPayEntries.size();
	}
	
	public static PayEntry GetPay(int index) {
		if (index >= _allPayEntries.size())
			return null;
		else
			return _allPayEntries.get(index);
	}
	
	public static void DeletePay(int index) {
		if (index >= _allPayEntries.size())
			return;
		_allPayEntries.remove(index);
	}
	
	public static void LoadPersons() {
		_allPersons = new Hashtable<String, PayPerson>();

		AddPerson(new PayPerson("Andy"));
		AddPerson(new PayPerson("Felix"));
		AddPerson(new PayPerson("Simon"));
		AddPerson(new PayPerson("Alfred"));
		AddPerson(new PayPerson("Leo"));
		AddPerson(new PayPerson("Lyne"));
		AddPerson(new PayPerson("Wayne"));
		AddPerson(new PayPerson("Peter"));
		AddPerson(new PayPerson("Tao"));
		AddPerson(new PayPerson("Wenrey"));
		AddPerson(new PayPerson("Phoebe"));
	}
	
	public static void LoadPayEntries() {
		_allPayEntries = new ArrayList<PayEntry>();
		_allPayEntries.add(new PayEntry(100, "Felix", new String[] {"Felix", "Andy", "Alfred", "Simon"}, 
				new Date(), "Buger King", "Here we are"));
		_allPayEntries.add(new PayEntry(60, "Andy", new String[] {"Felix", "Andy", "Leo"},
				new Date(), "À¼ÖÝÀ­Ãæ", "Not so bad"));
	}
	
	public static void Calculate()
	{
		for (int i = 0; i < _allPayEntries.size(); i++) {
			PayEntry e = _allPayEntries.get(i);
			_totalConsumedMoney += e.Money;
			double avg = e.Money / (double)e.AttendPersons.size();
			e.Payer.Debet += e.Money;
			for (int j = 0; j < e.AttendPersons.size(); j++)
				e.AttendPersons.get(j).Debet -= avg;
		}
	}

	public static double get_totalConsumedMoney() {
		return _totalConsumedMoney;
	}
}
