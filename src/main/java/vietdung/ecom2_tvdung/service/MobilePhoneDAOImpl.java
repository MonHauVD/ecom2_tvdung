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
import vietdung.ecom2_tvdung.controller.dto.DetailMobilePhoneDto;
import vietdung.ecom2_tvdung.model.*;
import vietdung.ecom2_tvdung.repository.*;

@Service
@Slf4j
public class MobilePhoneDAOImpl implements MobilePhoneDAO{

    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;

    @Autowired
    private ItemRepository itemRepository;

    
    @Override
    public MobilePhone addNewMobilePhone(DetailMobilePhoneDto detailMobilePhoneDto) {
        String Image = detailMobilePhoneDto.getImage();
        if(Image.length() == 0)
        {
            Image = "../images/origin/mobilePhone.png";
        }
        Item item = new Item(detailMobilePhoneDto.getName(), detailMobilePhoneDto.getPrice(), detailMobilePhoneDto.getQuantity(), detailMobilePhoneDto.getProducer(), Catalog.mobilephone, Image, detailMobilePhoneDto.getDescription());
        item = itemRepository.save(item); 
        
        MobilePhone mobilePhone = new MobilePhone(detailMobilePhoneDto.getModel(), detailMobilePhoneDto.getRam(), detailMobilePhoneDto.getStorage(), detailMobilePhoneDto.getBattery(), detailMobilePhoneDto.getScreenSize(), detailMobilePhoneDto.getOperatingSystem());
        mobilePhone.setItem(item);

        return mobilePhoneRepository.save(mobilePhone);
    }
    
    
    @Override
    public MobilePhone getMobilePhoneByMobilePhoneId(Long MobilePhoneId)
    {
//        Long item_id = itemRepository.getItemIdByMobilePhoneID(MobilePhoneId);
//        Item item = itemRepository.findItemById(item_id);
        
        MobilePhone mobilePhone = mobilePhoneRepository.getById(MobilePhoneId);
        return mobilePhone;
    }
    
    
    @Override
    public DetailMobilePhoneDto getDetailMobilePhoneDtoByMobilePhoneId(Long MobilePhoneId)
    {
        Long item_id = itemRepository.getItemIdByMobilePhoneID(MobilePhoneId);
        Item item = itemRepository.getById(item_id);
        MobilePhone mobilePhone = mobilePhoneRepository.getById(MobilePhoneId);
        return new DetailMobilePhoneDto(mobilePhone, item);
    }
    
    
    @Override
    public List<MobilePhone> getAllMobilePhones()
    {
        return mobilePhoneRepository.findAll();
    }
    
    
    @Override
    public List<DetailMobilePhoneDto> getAllDetailMobilePhoneDto()
    {
        List<DetailMobilePhoneDto> ls = new ArrayList<>();
        List <MobilePhone> lsMobilePhone = mobilePhoneRepository.findAll();
        for(MobilePhone thisMobilePhone : lsMobilePhone)
        {
            Long item_id = itemRepository.getItemIdByMobilePhoneID(thisMobilePhone.getId());
            Item thisItem = itemRepository.getById(item_id);
            ls.add(new DetailMobilePhoneDto(thisMobilePhone, thisItem));
        }
        
        return ls;
    }

    
    @Override
    public void updateMobilePhone(DetailMobilePhoneDto detailMobilePhoneDto)
    {
        Long item_id = itemRepository.getItemIdByMobilePhoneID(detailMobilePhoneDto.getMobilePhoneId());
        Item oldItem = itemRepository.getById(item_id);
        Item item = new Item(detailMobilePhoneDto.getName(), detailMobilePhoneDto.getPrice(), detailMobilePhoneDto.getQuantity(), detailMobilePhoneDto.getProducer(), Catalog.mobilephone, detailMobilePhoneDto.getImage(), detailMobilePhoneDto.getDescription());
        item.setId(item_id);
        item.setCatalog(Catalog.mobilephone);
        if(detailMobilePhoneDto.getImage().length() == 0)
        {
            item.setImage(oldItem.getImage());
        }
        
        itemRepository.save(item);
        
        MobilePhone mobilePhone = new MobilePhone(detailMobilePhoneDto.getMobilePhoneId(), detailMobilePhoneDto.getModel(), detailMobilePhoneDto.getRam(), detailMobilePhoneDto.getStorage(), detailMobilePhoneDto.getBattery(), detailMobilePhoneDto.getScreenSize(), detailMobilePhoneDto.getOperatingSystem(), item);
        
        mobilePhoneRepository.save(mobilePhone);
    }

    
    @Override
    public void deleteMobilePhone(Long MobilePhoneId)
    {
        Long item_id = itemRepository.getItemIdByMobilePhoneID(MobilePhoneId);
        MobilePhone mobilePhone = mobilePhoneRepository.getById(MobilePhoneId);
        Item item = itemRepository.getById(item_id);
        mobilePhoneRepository.delete(mobilePhone);
        itemRepository.delete(item);
    }
    
    
    
}