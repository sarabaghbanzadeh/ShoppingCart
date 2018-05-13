package shopping.cart.calculate.cost;

public class ReceiptProfileData {
	
	private int quantity;
	private String description;
	private String tax_code;
	private double amount;
	
	
	public ReceiptProfileData(int quantity, String description, String tax_code, double amount) {
		
		this.quantity = quantity;
		this.description = description;
		this.tax_code = tax_code;
		this.amount = amount;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
