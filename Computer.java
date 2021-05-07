public abstract class Computer extends Product{

    //Instance variable
    private double cpuSpeed;
    private int ram;
    private boolean ssd;
    private int storage;

    //Constructor
    public Computer(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage){
        super(price, quantity, 0,0);
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.ssd = ssd;
        this.storage = storage;
    }

    //Get and set methods
    public double getCpuSpeed() { return cpuSpeed; }

    public int getRam() {
        return ram;
    }

    public boolean getSsd() { return ssd; }

    public int getStorage() {
        return storage;
    }

}
