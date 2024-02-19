package Assignment01;

import java.util.ArrayList;

//@Mehtap Sueda Turkoglu @16.03.2023
public class Assignment01_20220808008 {

	public static void main(String[] args) {
		
//		Store s = new Store("Migros", "www.migros.com.tr"); 
//		
//		Customer c = new Customer("CSE 102"); 
//		System.out.println(c); 
//		
//		ClubCustomer cc = new ClubCustomer("Club CSE 102", "05551234567");
//		cc.addPoints(20);
//		cc.addPoints(30);
//		System.out.println(cc.getPhone());
//		System.out.println(cc);
//		
//		Product p = new Product("1234", "Computer", 20, 1000.00); 
//		FoodProduct fp = new FoodProduct("3456", "Snickers", 100, 2, 250, true, true, true, false); 
//		CleaningProduct cp = new CleaningProduct("5678", "Mop", 28, 99, false, "Multi-room");
//		
//		s.addProduct(p);
//		s.addProduct(fp); 
//		
//		for(int i = 0; i < s.getInventorySize(); i++) 
//			System.out.println(s.getProduct(i)); 
//		s.addProduct(cp); 
//			
//		s.addProduct(new Product("4321", "iPhone", 50, 99.00)); 
//		
//		System.out.println(s.getProductIndex(new FoodProduct("8888", "Apples", 500, 1, 50, false, false, false, false)));
//		
//		System.out.println(cp.purchase(2)); 
//		if(fp.containsGluten()) 
//			System.out.println("My wife cannot eat or drink " + fp.getName()); 
//		else 
//			System.out.println("My wife can eat or drink " + fp.getName()); 
//		
//		if(fp.containsPeanuts()) 
//			System.out.println("My friend cannot eat or drink " + fp.getName());
//		else 
//			System.out.println("My friend can eat or drink " + fp.getName()); 
//		
//		s.getProduct(0).addToInventory(3); 
//		
//		for(int i = 0; i < s.getInventorySize(); i++) {
//			Product cur = s.getProduct(i); 
//			System.out.println(cur); 
//			for(int j = i + 1; j < s.getInventorySize(); j++) 
//				if(cur.equals(s.getProduct(j))) 
//					System.out.println(cur.getName() + " is the same price as " + s.getProduct(j).getName()); 
//		}
	}
}


class Product{
	private String Id;
	private String Name;
	private int Quantity;
	private double Price;
	
	public Product(String Id, String Name, int Quantity, double Price){
		this.Id = Id;
		this.Name = Name;
		this.Quantity = Quantity;
		this.Price = Price;
	}
	
	public String getId() {
		return this.Id;
	}
	
	public void setId(String id) {
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
	
	public void setPrice(double price) {
		Price = price;
	}
	
	public int remaining() {
		return this.Quantity;
	}
	
	public int addToInventory(int amount) {
		if(amount<0)
			return Quantity;
		
		Quantity += amount;
		return Quantity;
	}
	
	public double purchase(int amount) {
		if(amount<0 || Quantity<amount)
			return 0;
		
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
	
	public FoodProduct(String Id, String Name, int Quantity, double Price, int Calories, boolean Dairy, boolean Peanuts, boolean Eggs, boolean Gluten) {
		super(Id, Name, Quantity, Price);	
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

	public CleaningProduct(String Id, String Name, int Quantity, double Price, boolean Liquid, String WhereToUse) {
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
	protected String name;
	
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
		return name + " has " + Points + " points";
	}
	
}

class Store{
	private String Name;
	private String Website;
	
	private ArrayList<Object> Inventory;
	public Store(String Name, String Website){
		Inventory = new ArrayList<Object>();
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
	
	public void addProduct(Product product, int index) {
		if(index<0 || Inventory.size()< index)
			Inventory.add((Inventory.size()), product);
		Inventory.add(index, product);
	}
	
	public void addProduct(Product product) {
		Inventory.add((Inventory.size()), product);
	}
	
	public Product getProduct(int index) {
		if(index<0 || index>Inventory.size())
			return null;
		return (Product) Inventory.get(index);
	}
	
	public int getProductIndex(Product p) {
		if(!(Inventory.contains(p)))
			return -1;
		return Inventory.indexOf(p);
	}
	
}