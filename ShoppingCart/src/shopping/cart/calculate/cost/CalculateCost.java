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
	private static HashMap<String, Double> taxValues = new HashMap<String, Double>();
	
	private static HashMap<String, Product> products;
	private static HashMap<String, TaxRate> taxRates;
	private static String product_file_name = "products.json";
	private static String taxt_file_name = "taxRate.json";
	private static String input_file_name = "input.json";
	private static Vector<ReceiptProfileData> productData = new Vector<ReceiptProfileData>();
	private static Vector<ReceiptTaxData> taxData = new Vector<ReceiptTaxData>();

	public static void main(String[] args) {
		
		JsonParserShoppingData json_data = new JsonParserShoppingData(product_file_name, taxt_file_name);
		
		products = json_data.getProducts();
		taxRates = json_data.getTaxRates();
		
		Vector<ShoppingCart> input_data = json_data.parseShoppingCart(input_file_name);
		
		buildProductData(input_data);
		
		printOutResult();
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
			
			ReceiptTaxData tax_receipt_data = new ReceiptTaxData(cur_tax_description, cur_tax_code, (cur_tax_rate.getRate()*100 + "%"), cur_tax_rate.getRate());
			if (!taxData.contains(tax_receipt_data))
				taxData.add(tax_receipt_data);
			
			if (taxValues.containsKey(cur_tax_code))
			{
				double curValue = taxValues.get(cur_tax_code);
				curValue = curValue + (cur_price*cur_quantity);
				taxValues.put(cur_tax_code, curValue);
			}
			else
				taxValues.put(cur_tax_code, (cur_price*cur_quantity));
		}
	}
	
	
	private static void printOutResult() 
	{
		System.out.println("==================================================================");
		System.out.println("                      Coding Challenge Store                      ");
		System.out.println("==================================================================");
		
		
		System.out.println("Quantity\tDescription\t\tTax Code\tAmount");
		
		double subtotal = 0;
		for (ReceiptProfileData p_data : productData) {
			
			subtotal += p_data.getAmount();
			System.out.println(p_data.getQuantity()+ "\t\t" + p_data.getDescription() + "\t\t" + p_data.getTax_code() + "\t" + p_data.getAmount());
		}
		
		System.out.println("\t\tSUBTOTAL:\t\t\t" + subtotal);
		System.out.println("");
		
		for (ReceiptTaxData t_data : taxData) {
			
			System.out.println("\t\t" + t_data.getDescription() + "\t" + t_data.getCode_des() + "\t" + (taxValues.get(t_data.getCode_orig())*taxRates.get(t_data.getCode_orig()).getRate()));
			subtotal += (taxValues.get(t_data.getCode_orig())*taxRates.get(t_data.getCode_orig()).getRate());
		}
		
		System.out.println("");
		System.out.println("\t\tTOTAL:\t\t\t" + subtotal);
	}

}
