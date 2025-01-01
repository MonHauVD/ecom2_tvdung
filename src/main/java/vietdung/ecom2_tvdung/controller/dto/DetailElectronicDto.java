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
public class DetailElectronicDto
{
    private Long electronicId;
    private String name;
    private Double price;
    private Integer quantity;
    private String producer;
    private Catalog catalog;
    private String image;
    private String description;
    
    //Thuoc tinh rieng
    private String model;
    private String typeOfMachine;
    private Double weight;
    private String dimensions;

    public DetailElectronicDto()
    {
    }

   //Thieu id
    public DetailElectronicDto(String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String model, String typeOfMachine, Double weight, String dimensions)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
    }
    
    //Thieu id va catalog

    public DetailElectronicDto(String name, Double price, Integer quantity, String producer, String image, String description, String model, String typeOfMachine, Double weight, String dimensions)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.image = image;
        this.description = description;
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    //all

    public DetailElectronicDto(Long electronicId, String name, Double price, Integer quantity, String producer, Catalog catalog, String image, String description, String model, String typeOfMachine, Double weight, String dimensions)
    {
        this.electronicId = electronicId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
        this.catalog = catalog;
        this.image = image;
        this.description = description;
        this.model = model;
        this.typeOfMachine = typeOfMachine;
        this.weight = weight;
        this.dimensions = dimensions;
    }
    
   
    // loai nay + item

    public DetailElectronicDto(Electronic electronic, Item item)
    {
        this.electronicId = electronic.getId();
        this.name = item.getName();
        this.price = item.getPrice();
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

    public Long getElectronicId()
    {
        return electronicId;
    }

    public void setElectronicId(Long electronicId)
    {
        this.electronicId = electronicId;
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
    
    
    
}