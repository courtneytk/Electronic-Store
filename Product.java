public class Product {

    //Instance variables
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;

    //Constructor
    public Product(double price, int stockQuantity, int soldQuantity, int cartQuantity){
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = soldQuantity;
        this.cartQuantity = cartQuantity;
    }

    //Get and set methods
    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public int getCartQuantity() { return cartQuantity; }

    //Methods to add to the current stock of a product
    public void addStock(){
        stockQuantity = stockQuantity + 1;
    }

    //Methods to remove from the current stock of a product
    public void removeStock(){
        if (stockQuantity >= 1) {
            stockQuantity = stockQuantity - 1;
        }
    }

    //Method to add to the cart amount of a certain product
    public void addCart(){
        cartQuantity = cartQuantity + 1;
    }

    //Method to remove from the cart amount of a certain product
    public void removeCart(){
        if (cartQuantity >= 1) {
            cartQuantity = cartQuantity - 1;
        }
    }

    //Resetting the cart quantity for each item before all the items in the cart is sold
    public void resetCartQuant(){
        cartQuantity = 0;
    }

    //Method to increase the number of sold items for a certain product
    public void soldProducts(){
        soldQuantity = soldQuantity + cartQuantity;
    }

    //Method to indicate the string value of the number of items of a specific product added to the cart (eg. 2x Grey Laptops)
    public String cartToString(){
        return cartQuantity + " x ";
    }
}
