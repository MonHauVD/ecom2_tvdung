/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.text.NumberFormat;
import java.util.Locale;
import vietdung.ecom2_tvdung.model.CartItem;
import vietdung.ecom2_tvdung.model.Catalog;
import vietdung.ecom2_tvdung.model.Item;

/**
 *
 * @author TranVietDung
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemDto
{
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Integer maxQuantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    private String totalPrice;
    private boolean isReviewed;

    public ItemDto()
    {
    }

    public ItemDto(Long id, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description)
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
    
    public ItemDto(CartItem cartItem)
    {
        Item item = cartItem.getItem();
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.quantity = cartItem.getQuantity();
        this.maxQuantity = item.getQuantity();
        if(cartItem.getQuantity() != null)
        {
            this.totalPrice = formatValue(cartItem.getQuantity() * item.getPrice());
        }
    }
    
    // for order detail page
    public ItemDto(CartItem cartItem, boolean isReviewed)
    {
        Item item = cartItem.getItem();
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.quantity = cartItem.getQuantity();
        this.maxQuantity = item.getQuantity();
        if(cartItem.getQuantity() != null)
        {
            this.totalPrice = formatValue(cartItem.getQuantity() * item.getPrice());
        }
        this.isReviewed = isReviewed;
    }
    
    public Item getItem()
    {
        return new Item(id, name, price, quantity, producer, catalog, image, description);
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getMaxQuantity()
    {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity)
    {
        this.maxQuantity = maxQuantity;
    }

    public String getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public boolean isIsReviewed()
    {
        return isReviewed;
    }

    public void setIsReviewed(boolean isReviewed)
    {
        this.isReviewed = isReviewed;
    }

    @Override
    public String toString()
    {
        return "ItemDto{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", maxQuantity=" + maxQuantity + ", producer=" + producer + ", catalog=" + catalog + ", image=" + image + ", description=" + description + ", totalPrice=" + totalPrice + ", isReviewed=" + isReviewed + '}';
    }

    
    
    
    
//    @Override
//    public String toString()
//    {
//        return "ItemDto{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", producer=" + producer + ", catalog=" + catalog + ", image=" + image + ", description=" + description + '}';
//    }
//    
    public String formatValue (Double value)
    {
        if (value == null)
        {
            return null;
        }
        Locale locale = new Locale("vi", "VN"); // Vietnam locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formatted = currencyFormatter.format(value).replace("₫", "").trim() + " đ";
        return formatted;
    }
    
}
