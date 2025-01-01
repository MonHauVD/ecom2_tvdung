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
import vietdung.ecom2_tvdung.controller.dto.DetailElectronicDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
@Slf4j
public class ElectronicDAOImpl implements ElectronicDAO{

    @Autowired
    private ElectronicRepository electronicRepository;

    @Autowired
    private ItemRepository itemRepository;

    
    @Override
    public Electronic addNewElectronic(DetailElectronicDto detailElectronicDto) {
        String Image = detailElectronicDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/electronic.png";
        }
        Item item = new Item(detailElectronicDto.getName(), detailElectronicDto.getPrice(), detailElectronicDto.getQuantity(), detailElectronicDto.getProducer(), Catalog.electronic, Image, detailElectronicDto.getDescription());
        item = itemRepository.save(item); 
        
        Electronic electronic = new Electronic(detailElectronicDto.getModel(), detailElectronicDto.getTypeOfMachine(), detailElectronicDto.getWeight(), detailElectronicDto.getDimensions());
        electronic.setItem(item);

        return electronicRepository.save(electronic);
    }
    
    
    @Override
    public Electronic getElectronicByElectronicId(Long ElectronicId)
    {
//        Long item_id = itemRepository.getItemIdByElectronicID(ElectronicId);
//        Item item = itemRepository.findItemById(item_id);
        
        Electronic electronic = electronicRepository.getById(ElectronicId);
        return electronic;
    }
    
    
    @Override
    public DetailElectronicDto getDetailElectronicDtoByElectronicId(Long ElectronicId)
    {
        Long item_id = itemRepository.getItemIdByElectronicID(ElectronicId);
        Item item = itemRepository.getById(item_id);
        Electronic electronic = electronicRepository.getById(ElectronicId);
        return new DetailElectronicDto(electronic, item);
    }
    
    
    @Override
    public List<Electronic> getAllElectronics()
    {
        return electronicRepository.findAll();
    }
    
    
    @Override
    public List<DetailElectronicDto> getAllDetailElectronicDto()
    {
        List<DetailElectronicDto> ls = new ArrayList<>();
        List <Electronic> lsElectronic = electronicRepository.findAll();
        for(Electronic thisElectronic : lsElectronic)
        {
            Long item_id = itemRepository.getItemIdByElectronicID(thisElectronic.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailElectronicDto(thisElectronic, thisItem));
        }
        
        return ls;
    }

    
    @Override
    public void updateElectronic(DetailElectronicDto detailElectronicDto)
    {
        Long item_id = itemRepository.getItemIdByElectronicID(detailElectronicDto.getElectronicId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailElectronicDto.getName(), detailElectronicDto.getPrice(), detailElectronicDto.getQuantity(), detailElectronicDto.getProducer(), Catalog.electronic, detailElectronicDto.getImage(), detailElectronicDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.electronic);
        if(detailElectronicDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        Electronic electronic = new Electronic(detailElectronicDto.getElectronicId(), detailElectronicDto.getModel(), detailElectronicDto.getTypeOfMachine(), detailElectronicDto.getWeight(), detailElectronicDto.getDimensions(), item);
        
        electronicRepository.save(electronic);
    }

    
    @Override
    public void deleteElectronic(Long ElectronicId)
    {
        Long item_id = itemRepository.getItemIdByElectronicID(ElectronicId);
        Electronic electronic = electronicRepository.getById(ElectronicId);
        Item item = itemRepository.getById(item_id);
        electronicRepository.delete(electronic);
        itemRepository.delete(item);
    }
    
    
    
}