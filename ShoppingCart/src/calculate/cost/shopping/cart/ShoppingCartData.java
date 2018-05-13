package calculate.cost.shopping.cart;

public class ShoppingCartData {
	
	private String size;
	private double price;
	private int tax_code;
	
	
	public ShoppingCartData(String size, double price, int tax_code) {
		
		this.size = size;
		this.price = price;
		this.tax_code = tax_code;
	}

	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getTax_code() {
		return tax_code;
	}
	
	public void setTax_code(int tax_code) {
		this.tax_code = tax_code;
	}
}
