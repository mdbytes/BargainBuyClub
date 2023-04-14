package com.mdbytes.app.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductScraper {

    private final String productUrl;
    private final String priceQuery;

    private final String productNameQuery;

    public ProductScraper(String productUrl, String priceQuery, String productNameQuery) {
        this.productUrl = productUrl;
        this.priceQuery = priceQuery;
        this.productNameQuery = productNameQuery;
    }


    /**
     * Method uses the JSOUP API to retrieve the product price from the product's
     * URL address.
     *
     * @param productURL the URL address where the product is located
     * @return product price from store website, a double
     * @throws IOException when I/O exception occurs
     */
    public double getProductPrice(String productURL) throws IOException {
        double price = 0.0;
        Document doc = Jsoup.connect(productURL).get();
        Elements htmlElements = doc.select(this.priceQuery);
        String stringPrice = "";
        Pattern howToFindPrice = Pattern.compile("(\\d+.\\d+)");
        Matcher findingPrice = howToFindPrice.matcher(htmlElements.get(0).toString());
        while (findingPrice.find()) {
            stringPrice = findingPrice.group(1);
        }

        try {
            price = Double.parseDouble(stringPrice.replace(",", ""));
        } catch (NumberFormatException e) {
            System.err.println("Error in price extraction.");
            price = 0.0;
        }

        return price;

    }

    /**
     * Method uses the JSOUP API to retrieve the product name from the product's
     * URL address.
     *
     * @param productURL the URL address where the product is located
     * @return product name, a String
     * @throws IOException when I/O exceptions occur
     */
    public String getProductName(String productURL) throws IOException {

        Document doc = Jsoup.connect(productURL).get();
        Element htmlElement = doc.select(this.productNameQuery).first();
        String productName = htmlElement.text();

        return productName;
    }


}
