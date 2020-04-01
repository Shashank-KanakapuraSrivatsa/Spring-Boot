package de.upb.cs.textclassifier;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class InputJsonObject {

	/*
	 * { "category":"POLITICS",
	 * "headline":"Ireland Votes To Repeal Abortion Amendment In Landslide Referendum"
	 * , "authors":"Laura Bassett", "link":
	 * "https://www.huffingtonpost.com/entry/results-for-irelands-historic-abortion-referendum-show-yes-vote-leads_us_5b086497e4b0802d69cb2cff",
	 * "short_description":"Irish women will no longer have to travel to the United Kingdom to end their pregnancies."
	 * , "date":"2018-05-26" }
	 */
	private String category;
	private String headline;
	private String authors;
	private URL link;
	private String short_description;
	private String date;
	private ArrayList<String> query = new ArrayList<String>();

	
	public InputJsonObject() {
		super();
	}

	public InputJsonObject(String category, String headline, String authors, URL link, String short_description,
			String date, ArrayList<String> query) {
		super();
		this.category = category;
		this.headline = headline;
		this.authors = authors;
		this.link = link;
		this.short_description = short_description;
		this.date = date;
		this.query = query;
	}

	public InputJsonObject(String category, String headline) {
		super();
		this.category = category;
		this.headline = headline;
	}
	
	public InputJsonObject(ArrayList<String> query) {
		super();
		this.query = query;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public URL getLink() {
		return link;
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public String getshort_description() {
		return short_description;
	}

	public void setshort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setQuery(ArrayList<String> query) {
		this.query = query;
	}
	
	public ArrayList<String> getQuery() {
		return query;
	}

	@Override
	public String toString() {
		return "InputJsonObject [category=" + category + ", headline=" + headline + ", authors="
				+ authors + ", link=" + link + ", short_description=" + short_description + ", date=" + date + "]";
	}

}
