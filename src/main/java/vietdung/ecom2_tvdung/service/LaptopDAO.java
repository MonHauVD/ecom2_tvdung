/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailLaptopDto;
import vietdung.ecom2_tvdung.model.Laptop;

/**
 *
 * @author TranVietDung
 */
public interface LaptopDAO
{
    Laptop addNewLaptop(DetailLaptopDto detailLaptopDto);
    Laptop getLaptopByLaptopId(Long LaptopId);
    DetailLaptopDto getDetailLaptopDtoByLaptopId(Long LaptopId);
    List<Laptop> getAllLaptops();
    List<DetailLaptopDto> getAllDetailLaptopDto();
    void updateLaptop(DetailLaptopDto detailLaptopDto);
    void deleteLaptop(Long LaptopId);
    
}
