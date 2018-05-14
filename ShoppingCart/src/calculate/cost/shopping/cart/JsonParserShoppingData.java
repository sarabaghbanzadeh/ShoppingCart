package calculate.cost.shopping.cart;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonParserShoppingData {
	
	
	private HashMap<String, Product> products;
	private HashMap<String, TaxRate> taxRates;
	
	
	public JsonParserShoppingData() {
		
		this.products = new HashMap<String, Product>();
		this.taxRates = new HashMap<String, TaxRate>();
	}
	
	public JsonParserShoppingData(String fileName_product, String fileName_tax) {
		
		// initiate product's maps
		this();
		
		// read and parse the JSON file
		try {
			parseProduct(fileName_product);
			parseTaxData(fileName_tax);
			
		} catch (FileNotFoundException e) {
			System.out.println("at least one of the files, " + fileName_tax + " or " + fileName_product + ", not found");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Error while parsing JSON data");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseProduct(String fileName_product) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		JSONArray JsonArray = (JSONArray) parser.parse(new FileReader(fileName_product));
		
		for (Object obj : JsonArray) 
		{
			JSONObject product = (JSONObject) obj;
			String id = String.valueOf(product.get("id"));
			String name = (String) product.get("name");
			
			JSONArray p_variants = (JSONArray) product.get("variants");
			Vector<ShoppingCartData> cur_variants = new Vector<ShoppingCartData>();
			
			for (Object obj_var : p_variants) {
				
				JSONObject variant = (JSONObject) obj_var;
				String size = String.valueOf(variant.get("size"));
				double price = (Double) variant.get("price");
				String tax_code = String.valueOf(variant.get("tax_code"));
				ShoppingCartData cur_variant = new ShoppingCartData(size, price, tax_code);
				
				cur_variants.add(cur_variant);
			}
			
			Product cur_product = new Product(name, cur_variants);
			this.products.put(id, cur_product);
		}
	}
	
	private void parseTaxData(String fileName_tax) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		JSONArray JsonArray = (JSONArray) parser.parse(new FileReader(fileName_tax));
		
		for (Object obj : JsonArray) 
		{
			JSONObject tax_data = (JSONObject) obj;
			String code = String.valueOf(tax_data.get("code"));
			String name = (String) tax_data.get("name");
			double rate = Double.valueOf(String.valueOf(tax_data.get("rate")));
			
			TaxRate cur_taxData = new TaxRate(name, rate);
			this.taxRates.put(code, cur_taxData);
		}
		
	}
	

	public HashMap<String, Product> getProducts() {
		return products;
	}
	
	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}
	
	public HashMap<String, TaxRate> getTaxRates() {
		return taxRates;
	}
	
	public void setTaxRates(HashMap<String, TaxRate> taxRates) {
		this.taxRates = taxRates;
	}
	
	
	public Vector<ShoppingCart> parseShoppingCart(String input_fileName) {
		
		Vector<ShoppingCart> toReturn = new Vector<ShoppingCart>();
		JSONParser parser = new JSONParser();
		JSONArray JsonArray;
		
		try {
			JsonArray = (JSONArray) parser.parse(new FileReader(input_fileName));
		
			for (Object obj : JsonArray) 
			{
				JSONObject data = (JSONObject) obj;
				String product = String.valueOf(data.get("product"));
				int variant = Integer.parseInt(String.valueOf(data.get("variant")));
				int quantity = Integer.parseInt(String.valueOf(data.get("quantity")));
				
				ShoppingCart cur_data = new ShoppingCart(product, variant, quantity);
				toReturn.add(cur_data);
			}
			
			return toReturn;
			
		} catch (Exception e) {
			System.out.println("Error while parsing the input data");
			e.printStackTrace();
			
			return toReturn;
		}
	}

}
