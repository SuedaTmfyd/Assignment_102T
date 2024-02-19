package Assignment02;

import java.util.ArrayList;
import java.util.Random;

//@Mehtap Sueda Turkoglu @14.04.2023

public class Assignment02_20220808008 {

		public static void main(String[] args) {
//			Store s = new Store("Migros", "www.migros.com.tr");
//
//	        Customer c = new Customer("CSE 102");
//
//	        ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
////	        
////	        s.addCustomer(c);
//	        s.addCustomer(cc);
//
//	        Product p = new Product(123456L, "Computer", 20, 1000.00);
//	        FoodProduct fp = new FoodProduct(456798L, "snickers", 100, 2, 250, true, true, true, false);
//	        CleaningProduct cp = new CleaningProduct(31654L, "Mop", 28, 99, false, "Multi-room");
//
//	        s.addProduct(p);
//	        s.addProduct(fp);
//	        s.addProduct(cp);
//
//	        System.out.println(s.getInventorySize());
////	        
////	        System.out.println(s.getProduct("shoes"));
//
//	        System.out.println(cp.purchase(2));
//	        s.getProduct("Computer").addToInventory(3);
////	        
////	        System.out.println(fp.purchase(200));
//
//	        c.addToCart(p, 2);
//	        c.addToCart(s.getProduct("snickers"),-2);
//	        c.addToCart(s.getProduct("snickers"), 1);
//	        System.out.println("Total due - " + c.getTotalDue());
//	        System.out.println("\n\nReceipt:\n" + c.receipt());
//	        
////			
////	        System.out.println("After paying: "+c.pay(2000));
//
//	        System.out.println("After paying: " + c.pay(2020));
//
//	        System.out.println("Total due - " + c.getTotalDue());
//	        System.out.println("\n\nReceipt 1:\n" + c.receipt());
//	        
////			
////	        Customer c2 = s.getCustomer("05551234568");
//	        cc.addToCart(s.getProduct("snickers"), 2);
//	        cc.addToCart(s.getProduct("snickers"), 1);
//	        System.out.println("\n\nReceipt 2:\n" + cc.receipt());
//
//	        Customer c3 = s.getCustomer("05551234567");
//	        c3.addToCart(s.getProduct("snickers"), 10);
//	        System.out.println("\n\nReceipt 3:\n" + cc.receipt());
//
//	        System.out.println(((ClubCustomer) c3).pay(26, false));
//
//	        c3.addToCart(s.getProduct(31654L),3);
//	        System.out.println(c3.getTotalDue());
//	        System.out.println(c3.receipt());
//	        System.out.println(cc.pay(3 * 99, false));
//
//	        c3.addToCart(s.getProduct(31654L), 3);
//	        System.out.println(c3.getTotalDue());
//	        System.out.println(c3.receipt());
//	        System.out.println(cc.pay(3 * 99, true));
	        
	}
}

	class Product{
		private long Id;
		private String Name;
		private int Quantity;
		private double Price;
		
		public Product(long Id, String Name, int Quantity, double Price){
			this.Id = Id;
			this.Name = Name;
			if(Quantity<0)
				throw new InvalidAmountException(Quantity);
			this.Quantity = Quantity;
			if(Price<0)
				throw new InvalidPriceException(Price);
			this.Price = Price;
		}
		
		public long getId() {
			return this.Id;
		}
		
		public void setId(long id) {
			Id = id;
		}
		
		public String getName() {
			return this.Name;
		}
		
		public void setName(String name) {
			Name = name;
		}
		
		public double getPrice() {
			return this.Price;
		}
		
		public void setPrice(double price){
			if(price<0)
				throw new InvalidPriceException(Price);
			Price = price;
		}
		
		public int remaining() {
			return this.Quantity;
		}
		
		public int addToInventory(int amount) {
			if(amount<0)
				throw new InvalidAmountException(amount);
			
			Quantity += amount;
			return Quantity;
		}
		
		public double purchase(int amount) {
			if(amount<0)
				throw new InvalidAmountException(amount); 
				
			if(Quantity<amount)
				throw new InvalidAmountException(amount, Quantity); 
			
			Quantity -= amount;
			return (amount*Price);
		}
		
		public String toString() {
			return "Product " + Name + " has " + Quantity + " remaining";
		}
		
		public boolean equals(Object o) {
			if((((Product)o).Price - this.Price) > 0)
				return (o instanceof Product) && (0.001>(((Product)o).Price - this.Price));
				
			return (o instanceof Product) && (0.001>(this.Price - ((Product)o).Price));
		}
		
	}

	class FoodProduct extends Product{
		private int Calories;
		private boolean Dairy;
		private boolean Eggs;
		private boolean Peanuts;
		private boolean Gluten;
		
		public FoodProduct(Long Id, String Name, int Quantity, double Price, int Calories, boolean Dairy, boolean Peanuts, boolean Eggs, boolean Gluten) {
			super(Id, Name, Quantity, Price);
			if(Calories<0)
				throw new InvalidAmountException(Calories);
			this.Calories = Calories;
			this.Dairy = Dairy;
			this.Eggs = Eggs;
			this.Peanuts = Peanuts;
			this.Gluten = Gluten;
		}
		
		public int getCalories() {
			return this.Calories;
		}
		
		public void setCalories(int calories) {
			if(calories<0)
				throw new InvalidAmountException(calories);
			Calories = calories;
		}
		
		public boolean containsDairy() {
			return Dairy;
		}
		
		public boolean containsEggs() {
			return Eggs;
		}
		
		public boolean containsPeanuts() {
			return Peanuts;
		}
		
		public boolean containsGluten() {
			return Gluten;
		}
		
	}

	class CleaningProduct extends Product{
		private boolean Liquid;
		private String WhereToUse;

		public CleaningProduct(Long Id, String Name, int Quantity, double Price, boolean Liquid, String WhereToUse) {
			super(Id, Name, Quantity, Price);
			this.Liquid = Liquid;
			this.WhereToUse = WhereToUse;
		}
		
		public String getWhereToUse() {
			return this.WhereToUse;
		}
		
		public void setWhereToUse(String size) {
			WhereToUse = size;
		}
		
		public boolean isLiquid() {
			return Liquid;
		}
	}

	class Customer{
		private String name;
		protected ArrayList<Product> productList = new ArrayList<Product>();
		protected ArrayList<Integer> countList = new ArrayList<Integer>();
		
		protected double totalDue = 0;
		
		public Customer(String name){
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
		public void addToCart(Product product, int count) {
			try {
				product.purchase(count);
			}catch(InvalidAmountException e) {
				System.out.println("ERROR: " + e);
				return;
			}
			productList.add(product);
			countList.add(count);
			totalDue += product.getPrice()*count;
		}
		
		private String capitalize(String text) {
			text = text.toUpperCase().charAt(0) + text.substring(1 , text.length()).toLowerCase();
			return text;
		}
		
		private String a;
		
		public String receipt() {
			a = "";
			for(int i = 0; i < productList.size(); i++) {
				a += capitalize(productList.get(i).getName()) + " - " + productList.get(i).getPrice() 
						+ " X " + countList.get(i) + " = " + productList.get(i).getPrice()*countList.get(i) 
						+ "\n";
			}
			a += "\n---------------------------------------" + "\n\nTotal Due = " + totalDue;
			return a;
		}
		
		public double getTotalDue() {
			return totalDue;
		}
		
		public double pay(double amount) {
			if(amount>=totalDue) {
				System.out.println("Thank you for shopping with us.");
				productList.clear();
				countList.clear();
				amount -= totalDue;
				totalDue = 0;
				return amount;
			}
			else
				throw new InsufficientFundsException(totalDue, amount);
		}
	}

	class ClubCustomer extends Customer{
		private String Phone;
		private int Points;
		
		public ClubCustomer(String name, String Phone){
			super(name);
			this.Points = 0;
			this.Phone = Phone;
		}
		
		public String getPhone() {
			return Phone;
		}
		
		public void setPhone(String phone) {
			Phone = phone;
		}
		
		public int getPoints() {
			return Points;
		}
		
		public void addPoints(int point) {
			if(point<0)
				return;
			Points += point;
		}
		
		public String toString() {
			return super.getName() + " has " + Points + " points";
		}
		
		public double pay(double amount, boolean usePoints) {
			if(amount>=totalDue) {
				System.out.println("Thank you for shopping with us.");
				productList.clear();
				countList.clear();
				
				if(usePoints == true && getPoints() != 0) {
					totalDue -= getPoints() * 0.01;
					
					if(usePoints == true && totalDue == 0) {
						Points = (int) (Points - totalDue * 100);
					}	
					else if(usePoints == true)
						this.Points = 0;
				}
				
				this.Points += (int)totalDue;
				amount -= totalDue;
				totalDue = 0;
				return amount;
			}
			else
				throw new InsufficientFundsException(totalDue, amount);
		}
	}

	class Store{
		private String Name;
		private String Website;
		private ArrayList<ClubCustomer> cCustomer = new ArrayList<ClubCustomer>();
		private ArrayList<Product> Prod = new ArrayList<Product>();
		private ArrayList<Object> Inventory = new ArrayList<Object>();
		
		public Store(String Name, String Website){
			this.Name = Name;
			this.Website = Website;
		}
		
		public String getName() {
			return this.Name;
		}
		
		public void setName(String name) {
			Name = name;
		}
		
		public String getWebsite() {
			return this.Website;
		}
		
		public void setWebsite(String website) {
			Website = website;
		}
		
		public int getInventorySize() {
			return (Inventory.size());
		}
		
		public void addProduct(Product product) {
			Inventory.add(product);
			Prod.add(product);
		}
		
		public Product getProduct(Long ID) {
			Long[] idList = new Long[Prod.size()];
			
			for(int i = 0; i < Prod.size(); i++)
				idList[i] = Prod.get(i).getId();
			
			int index = 0;

			for(int j = 0; j < Prod.size(); j++) {
				if(idList[j].longValue() == ID.longValue()) {
					break;
				}
				index++;
			}
			
			if(index >= idList.length)
				throw new ProductNotFoundException(ID);
			
			return Prod.get(index);
		}
		
		public Product getProduct(String name) {
			String[] nameList = new String[Prod.size()]; 
			
			for(int i = 0; i < Prod.size(); i++)
				nameList[i] = Prod.get(i).getName();
			
			int index = 0;
			
			for(int j = 0; j < Prod.size(); j++) {
				if(nameList[j].equalsIgnoreCase(name)) {
					break;
				}
				index++;
			}
			
			if(index >= nameList.length)
				throw new ProductNotFoundException(name);	
			
			return Prod.get(index);
		}
		
		public void addCustomer(ClubCustomer customer) {
			cCustomer.add(customer);
		}
		
		public ClubCustomer getCustomer(String phone) {
			String[] phoneList = new String[cCustomer.size()]; 
			
			for(int i = 0; i < cCustomer.size(); i++)
				phoneList[i] = cCustomer.get(i).getPhone();
			
			int index = 0;
			
			for(int j = 0; j < cCustomer.size(); j++) {
				if(phoneList[j].equals(phone)) {
					break;
				}
				index++;
			}
			
			if(index >= phoneList.length)
				throw new CustomerNotFoundException(phone);
			
			return cCustomer.get(index);
		}
		
		public void removeProduct(Long ID) {
			Long[] idList = new Long[Prod.size()]; 
			
			for(int i = 0; i < Prod.size(); i++)
				idList[i] = Prod.get(i).getId();
			
			int index = 0;
			
			for(int j = 0; j < Prod.size(); j++) {
				if(idList[j].longValue() == ID.longValue()) {
					break;
				}
				index++;
			}
			
			if(index >= idList.length)
				throw new ProductNotFoundException(ID);
			
			Prod.remove(index);
		}
		
		public void removeProduct(String name) {
			String[] nameList = new String[Prod.size()]; 
			
			for(int i = 0; i < Prod.size(); i++)
				nameList[i] = Prod.get(i).getName();
			
			int index = 0;
			
			for(int j = 0; j < Prod.size(); j++) {
				if(nameList[j].equalsIgnoreCase(name)) {
					break;
				}
				index++;
			}
			
			if(index >= nameList.length)
				throw new ProductNotFoundException(name);
			
			Prod.remove(index);
		}
		
		public void removeCustomer(String Phone) {
			String[] phoneList = new String[cCustomer.size()]; 
			
			for(int i = 0; i < cCustomer.size(); i++)
				phoneList[i] = cCustomer.get(i).getPhone();
			
			int index = 0;
			
			for(int j = 0; j < Prod.size(); j++) {
				if(phoneList[j].equals(Phone)) {
					break;
				}
				index++;
			}
			
			if(index >= phoneList.length)
				throw new CustomerNotFoundException(Phone);
			
			cCustomer.remove(index);
		}
		
	}
	
	
	
	
	class CustomerNotFoundException extends IllegalArgumentException{
		private String phone;
		
		public CustomerNotFoundException(String phone){
			this.phone = phone;
		}
		
		public String toString() {
			return "CustomerNotFoundException: " + phone;
		}
	}
	
	class InsufficientFundsException extends RuntimeException{
		private double total, payment;
		
		public InsufficientFundsException(double total, double payment){
			this.total = total;
			this.payment = payment;
		}
		
		public String toString() {
			return "InsufficientFundsException: " + total + " due, but only " + payment + " given";
		}
	}
	
	class InvalidAmountException extends RuntimeException{
		private int amount, quantity;
		
		public InvalidAmountException(int amount){
			this.amount = amount;
		}
		
		public InvalidAmountException(int amount, int quantity){
			this.amount = amount;
			this.quantity = quantity;
		}
		
		public String toString() {
			if(quantity == 0)
				return "InvalidAmountException: " + amount;
			else
				return "InvalidAmountException: " + amount + " was requested, but only " + quantity + " remaining";
		}
	}
	
	class InvalidPriceException extends RuntimeException{
		private double price;
		
		public InvalidPriceException(double price){
			this.price = price;
		}
		
		public String toString() {
			return "InvalidPriceException: " + price;
		}
	}
	
	class ProductNotFoundException extends IllegalArgumentException{
		private long ID;
		private String name;
		
		public ProductNotFoundException(long ID){
			this.ID = ID;
			name = null;
		}
		
		Random r = new Random();
		public ProductNotFoundException(String name){
			this.name = name;
			ID = r.nextLong();
		}
		
		public String toString() {
			if(name == null)
				return "ProductNotFoundException: ID - " + ID;
			return "ProductNotFoundException: Name - " + name;
		}
	}