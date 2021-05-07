public class Laptop extends Computer{

    //Instance variables
    private double screenSize;

    //Constructor
    public Laptop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, double screenSize){
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.screenSize = screenSize;
    }

    //Formatting output using toString
    public String toString(){
        if (getSsd()){
            return screenSize + " inch Laptop PC with " + getCpuSpeed() + "ghz CPU, " + getRam() + "GB RAM, " + getStorage() + "GB SSD drive.";
        }
        else{
            return screenSize + " inch Laptop PC with " + getCpuSpeed() + "ghz CPU, " + getRam() + "GB RAM, " + getStorage() + "GB SSD drive.";
        }
    }
}
