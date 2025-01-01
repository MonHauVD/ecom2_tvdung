/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.text.NumberFormat;
import java.util.Locale;
import vietdung.ecom2_tvdung.model.*;

/**
 *
 * @author TranVietDung
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemDetailDto
{
    private Long id;
    private String name;
    private String price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    //for book
    private String author;
    private String isbn;
    private Integer numberPage;
    //for clothes
    private String size;
    private String color;
    private String material;
    private String gender;
    //for Electronic
    private String model;
    private String typeOfMachine;
    private Double weight;
    private String dimensions;
    //for Laptop
    private String processor;
    private int ram;
    private int storage;
    private double screenSize;
    private String operatingSystem;
    //for MobilePhone
//    private String model;
//    private int ram;
//    private int storage;
    private int battery;
//    private double screenSize;
//    private String operatingSystem;
    //for Shoes
//    private String size;
//    private String color;
    private String type;
//    private String gender;

    public ItemDetailDto()
    {
    }
    
    public ItemDetailDto(Item item)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
    }

    public ItemDetailDto(Item item, Book book)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.numberPage = book.getNumberPage();
    }

    public ItemDetailDto(Item item, Clothes clothes)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.size = clothes.getSize();
        this.color = clothes.getColor();
        this.material = clothes.getMaterial();
        this.gender = clothes.getGender();
    }
    
    public ItemDetailDto(Item item, Electronic electronic)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.model = electronic.getModel();
        this.typeOfMachine = electronic.getTypeOfMachine();
        this.weight = electronic.getWeight();
        this.dimensions = electronic.getDimensions();
    }
    
    public ItemDetailDto(Item item, Laptop laptop)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.processor = laptop.getProcessor();
        this.ram = laptop.getRam();
        this.storage = laptop.getStorage();
        this.screenSize = laptop.getScreenSize();
    }
    
    public ItemDetailDto(Item item, MobilePhone mobilePhone)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.model = mobilePhone.getModel();
        this.ram = mobilePhone.getRam();
        this.storage = mobilePhone.getStorage();
        this.battery = mobilePhone.getBattery();
        this.screenSize = mobilePhone.getScreenSize();
        this.operatingSystem = mobilePhone.getOperatingSystem();
    }

    public ItemDetailDto(Item item, Shoes shoes)
    {
        this.id = item.getId();
        this.name = item.getName();
        this.price = formatValue(item.getPrice());
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        
        this.size = shoes.getSize();
        this.color = shoes.getColor();
        this.type = shoes.getType();
        this.gender = shoes.getGender();
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

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
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

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public Integer getNumberPage()
    {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage)
    {
        this.numberPage = numberPage;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getMaterial()
    {
        return material;
    }

    public void setMaterial(String material)
    {
        this.material = material;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
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

    public String getProcessor()
    {
        return processor;
    }

    public void setProcessor(String processor)
    {
        this.processor = processor;
    }

    public int getRam()
    {
        return ram;
    }

    public void setRam(int ram)
    {
        this.ram = ram;
    }

    public int getStorage()
    {
        return storage;
    }

    public void setStorage(int storage)
    {
        this.storage = storage;
    }

    public double getScreenSize()
    {
        return screenSize;
    }

    public void setScreenSize(double screenSize)
    {
        this.screenSize = screenSize;
    }

    public String getOperatingSystem()
    {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem)
    {
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    
    
    
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
