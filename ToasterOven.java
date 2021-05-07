public class ToasterOven extends KitchenAppliance{

    //Instance Variables
    private int width;
    private boolean convection;

    //Constructor
    public ToasterOven(double price, int quantity, int wattage, String color, String brand, int width, boolean convection){
        super(price,quantity,wattage,color,brand);
        this.width = width;
        this.convection = convection;
    }

    //Formatting output using toString
    public String toString(){
        if (convection){
            return width + " inch " + getBrand() +  " Toaster with convection" + " (" + getColor() + ", " + getWattage() + " watts)";
        }
        else{
            return width + " inch " + getBrand() +  " Toaster" + " (" + getColor() + ", " + getWattage() + " watts)";
        }

    }
}
