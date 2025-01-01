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
import vietdung.ecom2_tvdung.controller.dto.DetailShoesDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
@Slf4j
public class ShoesDAOImpl implements ShoesDAO{

    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private ItemRepository itemRepository;

    
    @Override
    public Shoes addNewShoes(DetailShoesDto detailShoesDto) {
        String Image = detailShoesDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/shoes.png";
        }
        Item item = new Item(detailShoesDto.getName(), detailShoesDto.getPrice(), detailShoesDto.getQuantity(), detailShoesDto.getProducer(), Catalog.shoes, Image, detailShoesDto.getDescription());
        item = itemRepository.save(item); 
        
        Shoes shoes = new Shoes(detailShoesDto.getSize(), detailShoesDto.getColor(), detailShoesDto.getType(), detailShoesDto.getGender());
        shoes.setItem(item);

        return shoesRepository.save(shoes);
    }
    
    
    @Override
    public Shoes getShoesByShoesId(Long ShoesId)
    {
//        Long item_id = itemRepository.getItemIdByShoesID(ShoesId);
//        Item item = itemRepository.findItemById(item_id);
        
        Shoes shoes = shoesRepository.getById(ShoesId);
        return shoes;
    }
    
    
    @Override
    public DetailShoesDto getDetailShoesDtoByShoesId(Long ShoesId)
    {
        Long item_id = itemRepository.getItemIdByShoesID(ShoesId);
        Item item = itemRepository.getById(item_id);
        Shoes shoes = shoesRepository.getById(ShoesId);
        return new DetailShoesDto(shoes, item);
    }
    
    
    @Override
    public List<Shoes> getAllShoess()
    {
        return shoesRepository.findAll();
    }
    
    
    @Override
    public List<DetailShoesDto> getAllDetailShoesDto()
    {
        List<DetailShoesDto> ls = new ArrayList<>();
        List <Shoes> lsShoes = shoesRepository.findAll();
        for(Shoes thisShoes : lsShoes)
        {
            Long item_id = itemRepository.getItemIdByShoesID(thisShoes.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailShoesDto(thisShoes, thisItem));
        }
        
        return ls;
    }

    
    @Override
    public void updateShoes(DetailShoesDto detailShoesDto)
    {
        Long item_id = itemRepository.getItemIdByShoesID(detailShoesDto.getShoesId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailShoesDto.getName(), detailShoesDto.getPrice(), detailShoesDto.getQuantity(), detailShoesDto.getProducer(), Catalog.shoes, detailShoesDto.getImage(), detailShoesDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.shoes);
        if(detailShoesDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        Shoes shoes = new Shoes(detailShoesDto.getShoesId(), detailShoesDto.getSize(), detailShoesDto.getColor(), detailShoesDto.getType(), detailShoesDto.getGender(), item);
        
        shoesRepository.save(shoes);
    }

    
    @Override
    public void deleteShoes(Long ShoesId)
    {
        Long item_id = itemRepository.getItemIdByShoesID(ShoesId);
        Shoes shoes = shoesRepository.getById(ShoesId);
        Item item = itemRepository.getById(item_id);
        shoesRepository.delete(shoes);
        itemRepository.delete(item);
    }
    
    
    
}