package org.practice.jsoup;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	System.out.println("Enter the product for crawler on flipkart :");
    	
    	Scanner sc = new Scanner(System.in);
    	String product = sc.nextLine();
    	Document doc = Jsoup.connect("http://www.flipkart.com/search?q="+product+"&as=off&as-show=off&otracker=start").get();
    	Elements elements = doc.select("div");
    	ArrayList<Product> productList = new ArrayList<Product>() ;
    	ArrayList<String> titleNodes = new ArrayList<String>();
    	ArrayList<Node> rateNodes = new ArrayList<Node>();
    	for(Element e : elements) {
    			if(e.hasAttr("class")) {
    				//crawling products list
    				if(e.attr("class").contains("product-unit")) {
    						Elements products = e.select("div");
    						for(Element productDetails : products) {
    							
    							if(productDetails.attr("class").contains("pu-details")) {
    								Product pro = new Product();
    								Elements productTitle = productDetails.select("a");
    								for(Element title : productTitle) {
    									if(title.attr("data-tracking-id").contains("prd_title")) {
    										pro.setName(title.attr("title"));
    										pro.setLink(title.attr("href"));
    									}
    								}
    								Elements productRate = productDetails.select("span");
    								for(Element rate : productRate) {
    									if(rate.attr("class").contains("fk-font-17")) {
    										
    										pro.setRate(rate.childNodes().get(0));
    									}
    								}
    								productList.add(pro);
    							}
    						}	
    				}
    			}
    	}
    	for(Product productTrav : productList) {
    		productTrav.printProductDetais();
    	}
    	System.out.println("Wanna Try Again : ");
    	if(sc.nextLine().equals("y")) {
    		main(null);
    	}
    	
    }
    
}
