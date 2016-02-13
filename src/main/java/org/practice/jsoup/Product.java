package org.practice.jsoup;

import org.jsoup.nodes.Node;

public class Product {
	private final String baseUrl = "http://www.flipkart.com";
	private String name;
	private String link;
	private Node rate;

	// getters n setters..
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = baseUrl + link;
	}

	public Node getRate() {
		return rate;
	}

	public void setRate(Node rate) {
		this.rate = rate;
	}

	public void printProductDetais() {
		System.out.println("Name : "+name);
		System.out.println("link : ["+link+"]");
		System.out.println("Name : "+rate);
		System.out.println();
	}

}