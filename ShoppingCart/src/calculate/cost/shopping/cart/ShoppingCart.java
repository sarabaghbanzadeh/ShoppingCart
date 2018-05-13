package calculate.cost.shopping.cart;

public class ShoppingCart {

	
	private String product;
	private int variant;
	private int quantity;
	
	
	public ShoppingCart(String product, int variant, int quantity) {
		
		this.product = product;
		this.variant = variant;
		this.quantity = quantity;
	}


	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getVariant() {
		return variant;
	}

	public void setVariant(int variant) {
		this.variant = variant;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
