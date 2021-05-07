import java.util.Scanner;
public class ElectronicStore {

    //Instance variables
    final int MAX_PRODUCTS = 10;
    private String name; //Name of electronic store
    private double revenue; //Revenue
    private int productCount; //A variable to maintain a counter for the number of products in the product array
    private int popularCount; //Number of popular products
    private int numSales; //Number of sales made
    private double avSale; //Dollars per sale or an average based on revenue and number of sales
    private double totalAmount; //Total amount in dollars of the items in the cart
    private int cartCount; //A variable to maintain a counter for number of products in the cart
    private Product[] stock;//Array to hold all the electronic store products
    private Product[] cartProducts; //Array of cart products
    private Product[] mostPopular; //Array of most popular products

    //Constructor
    public ElectronicStore(String name) {
        productCount = 0;
        cartCount = 0;
        revenue = 0.00;
        numSales = 0;
        avSale = 0.00;
        totalAmount = 0.00;
        popularCount = 3;
        this.name = name;
        stock = new Product[MAX_PRODUCTS];
        cartProducts = new Product[MAX_PRODUCTS];
        mostPopular = new Product[MAX_PRODUCTS];
    }

    //Create store constructor
    public static ElectronicStore createStore(){
        ElectronicStore store1 = new ElectronicStore("Electronic Store Application - Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }


    //Basic get and set methods
    public int getProductCount() { return productCount; }

    public int getCartCount() { return cartCount; }

    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getNumSales() { return numSales; }

    public double getTotalAmount() { return totalAmount; }

    public double getAvSale() {
        if(numSales > 0){
            return revenue/numSales;
        }
        else{
            return -1;
        }
    }

    public int getPopularCount() { return popularCount; }

    //Getting the products array but removing any null values before doing so
    public Product[] getStock(){

        int countProducts = 0;
        for(int i=0;i<MAX_PRODUCTS;i++){
            if (stock[i] != null && stock[i].getStockQuantity() > 0){
                countProducts++;
            }
        }
        Product[] result = new Product[countProducts];
        int currentIndex = 0;

        for (int i=0;i<MAX_PRODUCTS;i++){
            if(stock[i] != null && (stock[i].getStockQuantity() > 0)){
                result[currentIndex] = stock[i];
                currentIndex++;
            }
        }
        return result;
    }

    //Getting the most popular products array with non-null values
    public Product[] getMostPopular(){

        for(int i=0;i<MAX_PRODUCTS;i++){
            mostPopular[i] = null;
        }
        for(int i=0;i<MAX_PRODUCTS;i++){
            if(stock[i] != null){
                mostPopular[i] = stock[i];
            }
        }
        sortPopular();
        Product[] result = new Product[popularCount];
        int currentIndex = 0;
        for (int i=0;i<popularCount;i++){
            if(mostPopular[i] != null){
                result[currentIndex] = mostPopular[i];
                currentIndex++;
            }
        }

        return result;
    }

    //Getting the cart products array without null values
    public Product[] getCartProducts(){
        int countProducts = 0;
        for(int i=0;i<MAX_PRODUCTS;i++){
            if (cartProducts[i] != null && cartProducts[i].getCartQuantity() > 0){
                countProducts++;
            }
        }

        Product[] result = new Product[countProducts];
        int currentIndex = 0;

        // update result array with card quantity more than 0
        for (int i=0;i<MAX_PRODUCTS;i++){
            if(cartProducts[i] != null && cartProducts[i].getCartQuantity() > 0){
                result[currentIndex] = cartProducts[i];
                currentIndex++;
            }
        }
        return result;
    }

    //Method to send the quantity of products in cart to the view
    public String[] CartString(){
        String[] inCart = new String[cartCount];
        Product[] cart = getCartProducts();
        for(int i=0; i<cartCount; i++){
            if (cart[i].getCartQuantity() > 0){
                inCart[i] = cart[i].cartToString() + cart[i].toString();
            }
        }
        return inCart;
    }

    //Method to add new products to the product array if there is is space
    public boolean addProduct(Product p) {
        if ((productCount < MAX_PRODUCTS)) {
            stock[productCount++] = p;
            return true;
        }
        else {
            return false;
        }
    }

    //Method to sort the popular products in stock using bubble sort which is called whenever an item is sold
    public void sortPopular(){
        Product temp;
        for(int i=0;i<MAX_PRODUCTS;i++){
            for(int j=1;j<MAX_PRODUCTS;j++){
                if((mostPopular[j-1] != null) && (mostPopular[j] != null) && (mostPopular[j-1].getSoldQuantity() < mostPopular[j].getSoldQuantity())){
                    temp = mostPopular[j-1];
                    mostPopular[j-1] = mostPopular[j];
                    mostPopular[j] = temp;
                }
            }
        }
    }

    //Method to add one unit to cart and remove one unit from the stock
    public void addCart(int stockSelection){
        int cartIndex = 0;
        int curStockQuant = stock[stockSelection].getStockQuantity();

        if (curStockQuant > 0){
            stock[stockSelection].removeStock();
            if (stock[stockSelection].getStockQuantity() == 0) {
                productCount--;
            }
            addCartProducts(stockSelection);

            //Find the cart index that corresponds to stockSelection
            for (int i=0; i<MAX_PRODUCTS; i++)
            {
                if ((cartProducts[i] != null) && (stock[stockSelection] == cartProducts[i])) {
                    cartIndex = i;
                }
            }
            cartProducts[cartIndex].addCart();
            totalAmount = totalAmount + cartProducts[cartIndex].getPrice();

        }
    }

    //Method to add one unit of a product to a cart
    public void addCartProducts(int stockSelection){
        boolean isDupe = duplicateProducts(stockSelection); //Calling method to determine if should add to cart or not
        if ((cartCount < MAX_PRODUCTS) && isDupe == false) {
            cartProducts[cartCount++] = stock[stockSelection];
        }
    }

    //Method that checks if items in cart has duplicates
    public boolean duplicateProducts(int stockSelection){
        for(int i=0; i<cartCount; i++){
            if((cartProducts[i] == stock[stockSelection])){
                return true;
            }
        }
        return false;
    }

    //Method to remove one unit from the cart, and add one unit to the stock
    public void removeCart(int cartSelection){
        int stockIndex = 0;

        //Find stockIndex that corresponds to selectionCart
        for (int i=0; i<MAX_PRODUCTS; i++)
        {
            if ((stock[i] != null) && stock[i] == cartProducts[cartSelection]) {
                stockIndex = i;
                break;
            }
        }

        if (stock[stockIndex].getStockQuantity() == 0) {
            productCount++;
        }
        stock[stockIndex].addStock();

        if (cartProducts[cartSelection].getCartQuantity() > 0) {
            cartProducts[cartSelection].removeCart();
        }
        if (cartProducts[cartSelection].getCartQuantity() == 0) {
            cartCount--;
        }

        if (totalAmount > 0){
            totalAmount = totalAmount - cartProducts[cartSelection].getPrice();
        }
    }

    //Method to run when user completes shopping and a sale is made
    public void completeSale(){
        for(int i=0; i<productCount; i++){
            stock[i].soldProducts();
        }
        for(int i=0; i<cartCount; i++){
            cartProducts[i].resetCartQuant();
        }
        numSales += 1;
        revenue += totalAmount;
        cartCount = 0;
        totalAmount = 0.00;
    }

}

