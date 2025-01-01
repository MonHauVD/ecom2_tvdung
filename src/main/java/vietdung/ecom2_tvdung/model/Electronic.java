package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("Electronic")
public class Electronic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String typeOfMachine;
    private Double weight;
    private String dimensions;
    
    
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Electronic()
    {
    }

    public Electronic(Long id, String model, String typeOfMachine, Double weight, String dimensions, Item item)
    {
        this.id = id;
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
        this.item = item;
    }

    public Electronic(String model, String typeOfMachine, Double weight, String dimensions, Item item)
    {
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
        this.item = item;
    }

    public Electronic(String model, String typeOfMachine, Double weight, String dimensions)
    {
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getTypeOfMachine()
    {
        return typeOfMachine;
    }

    public void setTypeOfMachine(String typeOfMachine)
    {
        this.typeOfMachine = typeOfMachine;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public String getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(String dimensions)
    {
        this.dimensions = dimensions;
    }

   
}
