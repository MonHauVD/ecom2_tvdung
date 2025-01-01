package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("Shoes")
public class Shoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String color;
    private String type;
    private String gender;
    
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Shoes()
    {
    }

    public Shoes(Long id, String size, String color, String type, String gender, Item item)
    {
        this.id = id;
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
        this.item = item;
    }

    public Shoes(String size, String color, String type, String gender, Item item)
    {
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
        this.item = item;
    }

    public Shoes(String size, String color, String type, String gender)
    {
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
    }

    
    
    
    
    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    
    
    
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
