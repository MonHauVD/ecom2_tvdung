/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import vietdung.ecom2_tvdung.model.*;

/**
 *
 * @author TranVietDung
 */
public class DetailLaptopDto
{
    private Long laptopId;
    private String name;
    private Double price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    //Thuoc tinh rieng
    private String processor;
    private int ram;
    private int storage;
    private double screenSize;
    private String operatingSystem;
    public DetailLaptopDto()
    {
    }

   //Thieu id
    public DetailLaptopDto(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String processor, int ram, int storage, double screenSize, String operatingSystem)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }
    
    //Thieu id va catalog

    public DetailLaptopDto(String name, Double price, Integer quantity, String producer, String image, String description, String processor, int ram, int storage, double screenSize, String operatingSystem)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }
    
    
    //all

    public DetailLaptopDto(Long laptopId, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String processor, int ram, int storage, double screenSize, String operatingSystem)
    {
        this.laptopId = laptopId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }

    // loai nay + item
    public DetailLaptopDto(Laptop laptop, Item item)
    {
        this.laptopId = laptop.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.producer = item.getProducer();
        this.catalog = item.getCatalog();
        this.image = item.getImage();
        this.description = item.getDescription();
        this.processor = laptop.getProcessor();
        this.ram = laptop.getRam();
        this.storage = laptop.getStorage();
        this.screenSize = laptop.getScreenSize();
        this.operatingSystem = laptop.getOperatingSystem();
    }
    
    public Long getLaptopId()
    {
        return laptopId;
    }

    public void setLaptopId(Long laptopId)
    {
        this.laptopId = laptopId;
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

    
    
    
}