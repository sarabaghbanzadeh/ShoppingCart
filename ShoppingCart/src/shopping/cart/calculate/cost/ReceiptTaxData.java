package shopping.cart.calculate.cost;

public class ReceiptTaxData {

	private String description;
	private String code_orig;
	private String code_des;
	private double tax_rate;
	
	
	public ReceiptTaxData(String description, String code_orig, String code_des, double tax_rate) {
		super();
		this.description = description;
		this.code_orig = code_orig;
		this.code_des = code_des;
		this.tax_rate = tax_rate;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode_orig() {
		return code_orig;
	}


	public void setCode_orig(String code_orig) {
		this.code_orig = code_orig;
	}

	public String getCode_des() {
		return code_des;
	}

	public void setCode_des(String code_des) {
		this.code_des = code_des;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}
}
