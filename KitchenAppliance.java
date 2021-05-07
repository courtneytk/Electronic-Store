public abstract class KitchenAppliance extends Product{

    //Instance variables
    private int wattage;
    private String color;
    private String brand;

    //Constructor
    public KitchenAppliance(double price, int quantity,  int wattage, String color, String brand) {
        super(price,quantity,0,0);
        this.wattage = wattage;
        this.color = color;
        this.brand = brand;
    }

    //Get and set methods
    public int getWattage() {
        return wattage;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() { return brand; }
}
