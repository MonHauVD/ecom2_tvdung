package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("MobilePhone")
public class MobilePhone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int ram;
    private int storage;
    private int battery;
    private double screenSize;
    private String operatingSystem;
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public MobilePhone()
    {
    }

    public MobilePhone(Long id, String model, int ram, int storage, int battery, double screenSize, String operatingSystem, Item item)
    {
        this.id = id;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.battery = battery;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.item = item;
    }

    public MobilePhone(String model, int ram, int storage, int battery, double screenSize, String operatingSystem, Item item)
    {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.battery = battery;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.item = item;
    }

    public MobilePhone(String model, int ram, int storage, int battery, double screenSize, String operatingSystem)
    {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.battery = battery;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public int getRam() {
        return ram;
    }


    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }


    public double getScreenSize() {
        return screenSize;
    }


    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }


    public String getOperatingSystem() {
        return operatingSystem;
    }


    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getBattery()
    {
        return battery;
    }

    public void setBattery(int battery)
    {
        this.battery = battery;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
    
    
}

