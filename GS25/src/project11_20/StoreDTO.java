package project11_20;

public class StoreDTO {
	
	String no,name,company,location;
	int price, amount, money;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "StoreDTO [no=" + no + ", name=" + name + ", company=" + company + ", location=" + location + ", price="
				+ price + ", amount=" + amount + ", money=" + money + "]";
	}
	public StoreDTO(String no, String name, String company, String location, int price, int amount) {
		this.no = no;
		this.name = name;
		this.company = company;
		this.location = location;
		this.price = price;
		this.amount = amount;
		money=price*amount;
		
		}
	public StoreDTO() {
	}
	
	
	
	
	
}
