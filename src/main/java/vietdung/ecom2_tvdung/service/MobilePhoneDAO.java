/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailMobilePhoneDto;
import vietdung.ecom2_tvdung.model.MobilePhone;

/**
 *
 * @author TranVietDung
 */
public interface MobilePhoneDAO
{
    MobilePhone addNewMobilePhone(DetailMobilePhoneDto detailMobilePhoneDto);
    MobilePhone getMobilePhoneByMobilePhoneId(Long MobilePhoneId);
    DetailMobilePhoneDto getDetailMobilePhoneDtoByMobilePhoneId(Long MobilePhoneId);
    List<MobilePhone> getAllMobilePhones();
    List<DetailMobilePhoneDto> getAllDetailMobilePhoneDto();
    void updateMobilePhone(DetailMobilePhoneDto detailMobilePhoneDto);
    void deleteMobilePhone(Long MobilePhoneId);
    
}
