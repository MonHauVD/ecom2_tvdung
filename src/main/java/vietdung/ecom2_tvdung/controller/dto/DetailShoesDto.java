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
public class DetailShoesDto
{
    private Long shoesId;
    private String name;
    private Double price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    //Thuoc tinh rieng
    private String size;
    private String color;
    private String type;
    private String gender;

    public DetailShoesDto()
    {
    }

   //Thieu id

    public DetailShoesDto(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String size, String color, String type, String gender)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
    }
    
    
    //Thieu id va catalog

    public DetailShoesDto(String name, Double price, Integer quantity, String producer, String image, String description, String size, String color, String type, String gender)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
    }

    //all
    public DetailShoesDto(Long shoesId, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String size, String color, String type, String gender)    
    {
        this.shoesId = shoesId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.size = size;
        this.color = color;
        this.type = type;
        this.gender = gender;
    }

    // loai nay + item
    public DetailShoesDto(Shoes shoes, Item item)
    {
        this.shoesId = shoes.getId();
        this.name = item.getName();
        this.price = item.getPrice();
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
    
    
    public Long getShoesId()
    {
        return shoesId;
    }

    public void setShoesId(Long shoesId)
    {
        this.shoesId = shoesId;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    
}