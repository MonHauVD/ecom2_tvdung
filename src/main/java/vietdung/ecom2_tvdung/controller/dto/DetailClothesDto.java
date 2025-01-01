/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import vietdung.ecom2_tvdung.model.Clothes;
import vietdung.ecom2_tvdung.model.Catalog;
import vietdung.ecom2_tvdung.model.Item;

/**
 *
 * @author TranVietDung
 */
public class DetailClothesDto
{
    private Long clothesId;
    private String name;
    private Double price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    private String size;
    private String color;
    private String material;
    private String gender;

    public DetailClothesDto()
    {
    }

    public DetailClothesDto(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String size, String color, String material, String gender)
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
        this.material = material;
        this.gender = gender;
    }

    public DetailClothesDto(String name, Double price, Integer quantity, String producer, String image, String description, String size, String color, String material, String gender)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.size = size;
        this.color = color;
        this.material = material;
        this.gender = gender;
    }

    

    public DetailClothesDto(Clothes clothes, Item item)
    {
        this.clothesId = clothes.getId();
        this.name = item.getName();
        this.price = item.getPrice();
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

    public DetailClothesDto(Long clothesId, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String size, String color, String material, String gender)
    {
        this.clothesId = clothesId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.size = size;
        this.color = color;
        this.material = material;
        this.gender = gender;
    }

    
   

    public Long getClothesId()
    {
        return clothesId;
    }

    public void setClothesId(Long clothesId)
    {
        this.clothesId = clothesId;
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

    

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
    
    
    
}