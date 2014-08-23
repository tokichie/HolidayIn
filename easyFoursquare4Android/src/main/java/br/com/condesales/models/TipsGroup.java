package br.com.condesales.models;

import java.util.ArrayList;


public class TipsGroup {
	
	private int count;
	
	private String type;
	
	private ArrayList<TipItem> items;
	
	private String name;

	public int getCount() {
		return count;
	}

	public String getType() {
		return type;
	}

	public ArrayList<TipItem> getItems() {
		return items;
	}

	public String getName() {
		return name;
	}

}
