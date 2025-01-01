/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailShoesDto;
import vietdung.ecom2_tvdung.model.Shoes;

/**
 *
 * @author TranVietDung
 */
public interface ShoesDAO
{
    Shoes addNewShoes(DetailShoesDto detailShoesDto);
    Shoes getShoesByShoesId(Long ShoesId);
    DetailShoesDto getDetailShoesDtoByShoesId(Long ShoesId);
    List<Shoes> getAllShoess();
    List<DetailShoesDto> getAllDetailShoesDto();
    void updateShoes(DetailShoesDto detailShoesDto);
    void deleteShoes(Long ShoesId);
    
}
