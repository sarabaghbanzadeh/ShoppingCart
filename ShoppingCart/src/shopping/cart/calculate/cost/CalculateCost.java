package shopping.cart.calculate.cost;

import java.util.HashMap;
import java.util.Vector;

import calculate.cost.shopping.cart.JsonParserShoppingData;
import calculate.cost.shopping.cart.Product;
import calculate.cost.shopping.cart.ShoppingCart;
import calculate.cost.shopping.cart.ShoppingCartData;
import calculate.cost.shopping.cart.TaxRate;

public class CalculateCost {
	
	// taxValues<taxRate, price1+price2+..>
	private HashMap<Double, Double> taxValues = new HashMap<Double, Double>();
	
	private static HashMap<String, Product> products;
	private static HashMap<String, TaxRate> taxRates;
	private static String product_file_name = "products.txt";
	private static String taxt_file_name = "taxRate.txt";
	private static String input_file_name = "input.txt";
	private static Vector<ReceiptProfileData> productData = new Vector<ReceiptProfileData>();
	private static Vector<ReceiptTaxData> taxData = new Vector<ReceiptTaxData>();

	public static void main(String[] args) {
		
		JsonParserShoppingData json_data = new JsonParserShoppingData(product_file_name, taxt_file_name);
		
		products = json_data.getProducts();
		taxRates = json_data.getTaxRates();
		
		Vector<ShoppingCart> input_data = json_data.parseShoppingCart(input_file_name);
		
		buildProductData(input_data);
		
		
	}

	private static void buildProductData(Vector<ShoppingCart> input_data) {
				
		for (ShoppingCart object : input_data) {
			
			String cur_product = object.getProduct();
			int cur_variant = object.getVariant();
			int cur_quantity = object.getQuantity();
			
			Product cur_product_details = products.get(cur_product);
			ShoppingCartData cur_variants_details = cur_product_details.getVariants().get(cur_variant);
			
			String cur_size = cur_variants_details.getSize();
			double cur_price = cur_variants_details.getPrice();
			String cur_tax_code = cur_variants_details.getTax_code();
			
			String cur_description = cur_product_details.getName() + " - " + cur_size;
			
			ReceiptProfileData product_receipt_data = new ReceiptProfileData(cur_quantity, cur_description, cur_tax_code, (cur_quantity*cur_price));
			productData.add(product_receipt_data);
			
			TaxRate cur_tax_rate = taxRates.get(cur_tax_code);
			String cur_tax_description = cur_tax_code + "-" + cur_tax_rate.getName();
			
			ReceiptTaxData tax_receipt_data = new ReceiptTaxData(cur_tax_description, cur_tax_code, (cur_tax_rate.getRate()*100 + "%"));
			taxData.add(tax_receipt_data);
		}
		
	}

}
