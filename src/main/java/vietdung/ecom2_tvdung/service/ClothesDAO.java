/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailClothesDto;
import vietdung.ecom2_tvdung.model.Clothes;

/**
 *
 * @author TranVietDung
 */
public interface ClothesDAO
{
    Clothes addNewClothes(DetailClothesDto detailClothesDto);
    Clothes getClothesByClothesId(Long ClothesId);
    DetailClothesDto getDetailClothesDtoByClothesId(Long ClothesId);
    List<Clothes> getAllClothess();
    List<DetailClothesDto> getAllDetailClothesDto();
    void updateClothes(DetailClothesDto detailClothesDto);
    void deleteClothes(Long ClothesId);    
    
}
