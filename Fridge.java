public class Fridge extends KitchenAppliance{

    //Instance variables
    private double cubicFeet;
    private boolean hasFreezer;

    //Constructor
    public Fridge(double price, int quantity, int wattage, String color, String brand, double cubicFeet, boolean freezer){
        super(price,quantity,wattage,color,brand);
        this.cubicFeet = cubicFeet;
        this.hasFreezer = freezer;
    }

    //Formatting output using toString
    public String toString(){
        if (hasFreezer){
            return cubicFeet + " cu. ft. " + getBrand() + " Fridge with Freezer" + " (" + getColor() + ", " + getWattage() + " watts)";
        }
        else{
            return cubicFeet + " cu. ft. " + getBrand() + " Fridge" + " (" + getColor() + ", " + getWattage() + " watts)";
        }
    }
}

