package de.upb.cs.textclassifier.restservice;

import java.util.HashMap;
import java.util.List;

public class restServiceWrapper {
	
	private String query;
	private String category;
	private List<String> finalList;
	private HashMap<String,String> outputHashMap;
	
	
	public restServiceWrapper(String query, String category, List<String> finalList,
			HashMap<String, String> outputHashMap) {
		super();
		this.query = query;
		this.category = category;
		this.finalList = finalList;
		this.outputHashMap = outputHashMap;
	}
	
	public restServiceWrapper() {
		super();
	}
	
	public List<String> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<String> finalList) {
		this.finalList = finalList;
	}

	public HashMap<String, String> getOutputHashMap() {
		return outputHashMap;
	}

	public void setOutputHashMap(HashMap<String, String> outputHashMap) {
		this.outputHashMap = outputHashMap;
	}

	public restServiceWrapper(String query, String category) {
		super();
		this.query = query;
		this.category = category;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
