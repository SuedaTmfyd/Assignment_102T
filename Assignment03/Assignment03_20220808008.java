package Assignment03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Assignment03_20220808008 {

	public static void main(String[] args) {
		    Store s1 = new Store("Migros","www.migros.com.tr");
	        Store s2 = new Store("Bim","www.bim.com.tr");
	       
	        Customer c = new Customer("CSE 102");
	        
	        Customer cc = new Customer("Club CSE 102");
	        s1.addCustomer(cc);
	        
	        Product p = new Product(123456L,"Computer",1000.00);
	        FoodProduct fp = new FoodProduct(456789L,"Snickers",2,250,true,true,true,false);
	        CleaningProduct cp = new CleaningProduct(31654L,"Mop",99,false,"Multi-room");
	        System.out.println(cp);
	        
	        s1.addToInventory(p,20);
	        s2.addToInventory(p,10);
	        s2.addToInventory(fp,100);
	        s1.addToInventory(cp,28);
	        
	        System.out.println(s1.getName()+" has "+ s1.getCount() +" products");
	        System.out.println(s1.getProductCount(p));
	        
	        System.out.println(s1.purchase(p,2));
	        s1.addToInventory(p,3);
	        System.out.println(s1.getProductCount(p));
	        System.out.println(s2.getProductCount(p));
	        
//	        System.out.println(s1.getProductCount(fp));
//	        System.out.println(s2.purchase(fp, 200));
	        
	        c.addToCart(s1,p,2);
	        c.addToCart(s1,fp,1);
	        c.addToCart(s1,cp,1);
	        System.out.println("Total due - " +c.getTotalDue(s1));
	        System.out.println("\n \nReceipt: " +c.receipt(s1));
	        
//	        System.out.println("\n\nReceipt:\n" + c.receipt(s2));
//	        System.out.println("After paying: " + c.pay(s1, 2000, true));
	        
	        System.out.println("After paying:  "+ c.pay(s1,2100,true));
	        
//	        System.out.println("Total due - " + c.getTotalDue(s1));
//	        System.out.println("\n\nReceipt:\n" + c.receipt(s1));
	        
	        cc.addToCart(s2,fp,2);
	        cc.addToCart(s2,fp,1);
	        System.out.println(cc.receipt(s2));
	        
	        cc.addToCart(s2,fp,10);
	        System.out.println(cc.receipt(s2));
	}
}

class Product{
	private long Id;
	private String Name;
	private double Price;
	
	public Product(long Id, String Name, double Price){
		this.Id = Id;
		this.Name = Name;
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
	
	public String toString() {
		return Id + " - " + Name + " @ " + Price;
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
	
	public FoodProduct(Long Id, String Name, double Price, int Calories, boolean Dairy, boolean Peanuts, boolean Eggs, boolean Gluten) {
		super(Id, Name, Price);
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

	public CleaningProduct(Long Id, String Name, double Price, boolean Liquid, String WhereToUse) {
		super(Id, Name, Price);
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
	private Map<String, ArrayList<Product>> storeList = new HashMap<String, ArrayList<Product>>();
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Integer> countList = new ArrayList<Integer>();
	
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
	
	public void addToCart(Store store, Product product, int count) {
		try {
			store.purchase(product, count);
		}catch(InvalidAmountException e) {
			System.out.println("ERROR: " + e);
			return;
		}catch(ProductNotFoundException a) {
			System.out.println("ERROR: " + a);
			return;
		}
		productList.add(product);
		storeList.put(store.getName(), productList);
		countList.add(count);
	}
	
	private String capitalize(String text) {
		text = text.toUpperCase().charAt(0) + text.substring(1 , text.length()).toLowerCase();
		return text;
	}
	
	public String receipt(Store store) {
		if(!storeList.containsKey(store.getName()))
			throw new StoreNotFoundException(store.getName());
		
		String a;
		a = "Customer receipt for " + store.getName() + "\n";
		for(int i = 0; i < productList.size(); i++) {
			a += productList.get(i).getId() + " - "+ capitalize(productList.get(i).getName()) + " @ " + productList.get(i).getPrice() 
					+ " X " + countList.get(i) + " ... " + productList.get(i).getPrice()*countList.get(i) 
					+ "\n";
		}
		a += "\n------------------------------------------" + "\n\nTotal Due - " + getTotalDue(store) + "\n";
		return a;
	}
	
	public double getTotalDue(Store store) {
		if(!storeList.containsKey(store.getName()))
			throw new StoreNotFoundException(store.getName());
		
		totalDue = 0;
		for(int i = 0; i<storeList.get(store.getName()).size(); i++)
			totalDue+=storeList.get(store.getName()).get(i).getPrice() * countList.get(i);
		return totalDue;
	}
	
	public int getPoints(Store store) {
		if(!store.CustomerList.contains(this))
			throw new StoreNotFoundException(store.getName());
		return store.getCustomerPoints(this);
	}
	
	public double pay(Store store, double amount, boolean usePoints) {
		if(!storeList.containsKey(store.getName()))
			throw new StoreNotFoundException(store.getName());
		
		double due = getTotalDue(store);
		int points;
		if(amount<due)
			throw new InsufficientFundsException(getTotalDue(store), amount);
		
			if(usePoints && store.CustomerList.contains(this)) {
				points = getPoints(store);
				due -= points * 0.01;
				if(due == 0) 
					points = (int) (points - due * 100);
				else
					store.PointsList.add(store.CustomerList.indexOf(this), 0);
				
				store.PointsList.add(store.CustomerList.indexOf(this), points+(int)due);
			}
				
			if(usePoints && (!store.CustomerList.contains(this))) {
				points = 0;
			}
			
			if(due>0)
				amount -= due;
			totalDue = 0;
			System.out.println("Thank you for shopping with us.");
			productList.clear();
			countList.clear();
			storeList.remove(store.getName());
			return amount;
	}
}

class Store{
	private String Name;
	private String Website;
	private Map<String, ArrayList<Product>> storeList = new HashMap<String, ArrayList<Product>>();
	protected ArrayList<Customer> CustomerList = new ArrayList<Customer>();
	protected ArrayList<Integer> PointsList = new ArrayList<Integer>();
	private ArrayList<Product> ProductList = new ArrayList<Product>();
	private ArrayList<Integer> QuantityList = new ArrayList<Integer>();
	
	public Store(String Name, String Website){
		this.Name = Name;
		this.Website = Website;
		storeList.put(Name, ProductList);
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
		return (ProductList.size());
	}
	
	public int remaining(Product product) {
		for(int i = 0; i<ProductList.size(); i++)
			if(ProductList.get(i).equals(product))
				return QuantityList.get(i);
		throw new ProductNotFoundException(product);
	}
	
	public int getCount() {
		return ProductList.size();
	}
	
	public void addCustomer(Customer customer) {
		CustomerList.add(customer);
		PointsList.add(0);
	}
	
	public int getProductCount(Product product) {
		for(int i = 0; i<ProductList.size(); i++)
			if(ProductList.get(i).equals(product))
				return QuantityList.get(i);
		throw new ProductNotFoundException(product);
	}
	
	public int getCustomerPoints(Customer customer) {
		for(int i = 0; i<CustomerList.size(); i++)
			if(CustomerList.get(i).equals(customer))
				return PointsList.get(i);
		throw new CustomerNotFoundException(customer);
	}
	
	public void removeProduct(Product product) {
		for(int i = 0; i<ProductList.size(); i++)
			if(ProductList.get(i).equals(product)) {
				ProductList.remove(i);
				QuantityList.remove(i);
				storeList.put(Name, ProductList);
			}
		throw new ProductNotFoundException(product);
	}
	
	public void addToInventory(Product product, int amount) {
		if(amount<0)
			throw new InvalidAmountException(amount);
		for(int i = 0; i<ProductList.size(); i++)
			if(ProductList.get(i).equals(product)) {
				int a = QuantityList.get(i);
				QuantityList.add(i, a+amount);
				return;
			}
		ProductList.add(product);
		storeList.put(Name, ProductList);
		QuantityList.add(amount);
	}
	
	public double purchase(Product product, int amount) {
		if(!ProductList.contains(product))
			throw new ProductNotFoundException(product); 
		
		if(amount<0 || amount>QuantityList.get(ProductList.indexOf(product)))
			throw new InvalidAmountException(amount); 
		
		int count = QuantityList.get(ProductList.indexOf(product));
		QuantityList.add(ProductList.indexOf(product), count-amount);
		return (amount*product.getPrice());
	}
}


class CustomerNotFoundException extends IllegalArgumentException{
	private Customer customer;
	
	public CustomerNotFoundException(Customer customer){
		this.customer = customer;
	}
	
	public String toString() {
		return "CustomerNotFoundException: Name - " + customer.getName();
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
	private Product product;
	
	public ProductNotFoundException(long ID){
		this.ID = ID;
		name = null;
	}
	
	Random r = new Random();
	public ProductNotFoundException(String name){
		this.name = name;
		ID = r.nextLong();
	}
	
	public ProductNotFoundException(Product product) {
		this.product = product;
	}
	
	public String toString() {
		if(product == null)
			return "ProductNotFoundException: ID - " + ID + " Name - " + name;
		return "ProductNotFoundException: ID - " + product.getId() + " Name - " + product.getName();
	}
}

class StoreNotFoundException extends IllegalArgumentException{
	private String name;
	
	StoreNotFoundException(String name){
		this.name = name;
	}
	
	public String toString() {
		return "StoreNotFoundException: " + name;
	}
}
