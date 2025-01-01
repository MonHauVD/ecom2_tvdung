package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("Laptop")
public class Laptop{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String processor;
    private int ram;
    private int storage;
    private double screenSize;
    private String operatingSystem;
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Laptop()
    {
    }

    public Laptop(Long id, String processor, int ram, int storage, double screenSize, String operatingSystem, Item item)
    {
        this.id = id;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.item = item;
    }

    public Laptop(String processor, int ram, int storage, double screenSize, String operatingSystem, Item item)
    {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        this.item = item;
    }

    public Laptop(String processor, int ram, int storage, double screenSize, String operatingSystem)
    {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }

    
    
    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
    
    
    
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
}