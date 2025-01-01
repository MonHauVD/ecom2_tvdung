package vietdung.ecom2_tvdung.model;
//import jakarta.persistence.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import vietdung.ecom2_tvdung.controller.dto.ItemDto;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 300, message = "Product name size should be between 3-30")
    private String name;
    
    @NotNull
    @DecimalMin(value = "0.00")
    private Double price;
    
    @NotNull
    @Min(value = 0)
    private Integer quantity;
    
    
    private String producer;
    
    private Catalog catalog;
    
    private String image;
    
    private String description;

    public Item()
    {
        
    }

    public Item(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
    }

    public Item(Long id, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
    }

    
    public ItemDto getItemDto()
    {
        return new ItemDto(id, name, price, quantity, producer, catalog, image, description);
    }
    

    
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getProducer()
    {
        return producer;
    }

    public void setProducer(String producer)
    {
        this.producer = producer;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    @Override
    public String toString()
    {
        return "Item{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", producer=" + producer + ", catalog=" + catalog + ", image=" + image + ", description=" + description + '}';
    }

    
    
    
    
}