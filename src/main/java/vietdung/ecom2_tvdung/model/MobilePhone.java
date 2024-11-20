package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
@DiscriminatorValue("MobilePhone")
public class MobilePhone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int ram;
    private int storage;
    private double screenSize;
    private String operatingSystem;

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
}
