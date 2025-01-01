/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

/**
 *
 * @author TranVietDung
 */
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vietdung.ecom2_tvdung.controller.dto.DetailLaptopDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
@Slf4j
public class LaptopDAOImpl implements LaptopDAO{

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private ItemRepository itemRepository;

    
    public Laptop addNewLaptop(DetailLaptopDto detailLaptopDto) {
        String Image = detailLaptopDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/laptop.png";
        }
        Item item = new Item(detailLaptopDto.getName(), detailLaptopDto.getPrice(), detailLaptopDto.getQuantity(), detailLaptopDto.getProducer(), Catalog.laptop, Image, detailLaptopDto.getDescription());
        item = itemRepository.save(item); 
        
        Laptop laptop = new Laptop(detailLaptopDto.getProcessor(), detailLaptopDto.getRam(), detailLaptopDto.getStorage(), detailLaptopDto.getScreenSize(), detailLaptopDto.getOperatingSystem());
        laptop.setItem(item);

        return laptopRepository.save(laptop);
    }
    
    
    public Laptop getLaptopByLaptopId(Long LaptopId)
    {
//        Long item_id = itemRepository.getItemIdByLaptopID(LaptopId);
//        Item item = itemRepository.findItemById(item_id);
        
        Laptop laptop = laptopRepository.getById(LaptopId);
        return laptop;
    }
    
    
    public DetailLaptopDto getDetailLaptopDtoByLaptopId(Long LaptopId)
    {
        Long item_id = itemRepository.getItemIdByLaptopID(LaptopId);
        Item item = itemRepository.getById(item_id);
        Laptop laptop = laptopRepository.getById(LaptopId);
        return new DetailLaptopDto(laptop, item);
    }
    
    
    public List<Laptop> getAllLaptops()
    {
        return laptopRepository.findAll();
    }
    
    
    public List<DetailLaptopDto> getAllDetailLaptopDto()
    {
        List<DetailLaptopDto> ls = new ArrayList<>();
        List <Laptop> lsLaptop = laptopRepository.findAll();
        for(Laptop thisLaptop : lsLaptop)
        {
            Long item_id = itemRepository.getItemIdByLaptopID(thisLaptop.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailLaptopDto(thisLaptop, thisItem));
        }
        
        return ls;
    }

    
    public void updateLaptop(DetailLaptopDto detailLaptopDto)
    {
        Long item_id = itemRepository.getItemIdByLaptopID(detailLaptopDto.getLaptopId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailLaptopDto.getName(), detailLaptopDto.getPrice(), detailLaptopDto.getQuantity(), detailLaptopDto.getProducer(), Catalog.laptop, detailLaptopDto.getImage(), detailLaptopDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.laptop);
        if(detailLaptopDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        Laptop laptop = new Laptop(detailLaptopDto.getLaptopId(), detailLaptopDto.getProcessor(), detailLaptopDto.getRam(), detailLaptopDto.getStorage(), detailLaptopDto.getScreenSize(), detailLaptopDto.getOperatingSystem(), item);
        
        laptopRepository.save(laptop);
    }

    
    public void deleteLaptop(Long LaptopId)
    {
        Long item_id = itemRepository.getItemIdByLaptopID(LaptopId);
        Laptop laptop = laptopRepository.getById(LaptopId);
        Item item = itemRepository.getById(item_id);
        laptopRepository.delete(laptop);
        itemRepository.delete(item);
    }
    
    
    
}