package org.practice.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FlipkartSearchProductClrawler {
	public static void main(String[] args){
		System.out.println("Enter the product for crawler on flipkart :");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String product = sc.nextLine();

		execute(product);
		// code to recursively re-execute the Application.
		System.out.println("Wanna Try Again : ");
		if (sc.nextLine().equals("y")) {
			main(null);
		}
	}
	
	private static void execute(String product) {
		try{
			Document doc = Jsoup.connect(
				"http://www.flipkart.com/search?q=" + product
						+ "&as=off&as-show=off&otracker=start").get();
		Elements elements = doc.select("div");
		ArrayList<Product> productList = new ArrayList<Product>();
		for (Element e : elements) {
			if (e.hasAttr("class")) {
				// crawling products list
				if (e.attr("class").contains("product-unit")) {
					// flipkart uses a div tag for each enclosed product
					// specifications
					Elements products = e.select("div");
					for (Element productDetails : products) {
						// crawling at the details section for each product.
						if (productDetails.attr("class").contains("pu-details")) {
							Product pro = new Product();
							// flipkart uses an anchor tag as a layout for title
							// of products mentioned
							Elements productTitle = productDetails.select("a");
							for (Element title : productTitle) {
								// looking for title and link
								if (title.attr("data-tracking-id").contains(
										"prd_title")) {
									pro.setName(title.attr("title"));
									pro.setLink(title.attr("href"));
								}
							}
							// flipkart uses a span tag for displaying rate of
							// the corresponding product.
							Elements productRate = productDetails
									.select("span");
							for (Element rate : productRate) {
								// looking for rate in span value.
								if (rate.attr("class").contains("fk-font")) {

									pro.setRate(rate.childNodes().get(0));
								}
							}
							productList.add(pro);
						}
					}
				}
			}
		}

		// display full details for all gathered products.
		for (Product productTrav : productList) {
			productTrav.printProductDetais();
		}
		System.out.println("Entry count : "+productList.size());
		}
		catch(IOException e) {
			e.printStackTrace();
			//re-execute in case of error occurence.
			execute(product);
		}
	}
			
		
	

}
