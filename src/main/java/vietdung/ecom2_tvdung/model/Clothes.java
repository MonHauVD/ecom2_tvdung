package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
//@DiscriminatorValue("Clothes")
public class Clothes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private String color;
    private String material;
    private String gender;
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Clothes()
    {
    }

    public Clothes(String size, String color, String material, String gender)
    {
        this.size = size;
        this.color = color;
        this.material = material;
        this.gender = gender;
    }

    public Clothes(String size, String color, String material, String gender, Item item)
    {
        this.size = size;
        this.color = color;
        this.material = material;
        this.gender = gender;
        this.item = item;
    }

    public Clothes(Long id, String size, String color, String material, String gender, Item item)
    {
        this.id = id;
        this.size = size;
        this.color = color;
        this.material = material;
        this.gender = gender;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    @Override
    public String toString()
    {
        return "Clothes{" + "id=" + id + ", size=" + size + ", color=" + color + ", material=" + material + ", gender=" + gender + '}';
    }
    
}
