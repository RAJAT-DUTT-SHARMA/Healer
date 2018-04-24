package services;

import java.util.HashMap;

public class HelperfunctionForDate
{
	static HashMap <String, HashMap<String,String>> PersonToDetails;
	
	public static void initializeMap()
	{
		PersonToDetails =new HashMap<>(); 
	}
	
	public static void addDateForPerson(String person, String address, String disease, String date, String rate, String doct)
	{
		HashMap<String, String> tmp = new HashMap<String,String>();
		tmp.put("address", address);
		tmp.put("disease", disease);
		tmp.put("date", date);
		tmp.put("rate", rate);
		tmp.put("doct", doct);
		PersonToDetails.put(person, tmp);
	}

	public static HashMap<String,HashMap<String, String>> getPersonToDate(){
		System.out.println(PersonToDetails.toString());
		return PersonToDetails;
	}







}