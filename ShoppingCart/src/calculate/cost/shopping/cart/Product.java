package calculate.cost.shopping.cart;

import java.util.Vector;

public class Product {
	
	private String name;
	private Vector<ShoppingCartData> variants = new Vector<ShoppingCartData>();
	
	
	public Product(String name, Vector<ShoppingCartData> variants) {
		
		this.name = name;
		this.variants = variants;
	}

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector<ShoppingCartData> getVariants() {
		return variants;
	}
	
	public void setVariants(Vector<ShoppingCartData> variants) {
		this.variants = variants;
	}
}
