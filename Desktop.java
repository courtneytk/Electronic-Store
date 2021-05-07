public class Desktop extends Computer{

    //Instance variable
    private String profile;

    //Constructor
    public Desktop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, String profile){
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.profile = profile;
    }

    //Formatting output using toString
    public String toString(){
        if (getSsd()){
            return profile + " Desktop PC with " + getCpuSpeed() + "ghz CPU, " + getRam() + "GB RAM, " + getStorage() + "GB SSD drive.";
        }
        else{
            return profile + " Desktop PC with " + getCpuSpeed() + "ghz CPU, " + getRam() + "GB RAM, " + getStorage() + "GB HDD drive.";
        }
    }
}
