/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import java.util.List;
import vietdung.ecom2_tvdung.controller.dto.DetailElectronicDto;
import vietdung.ecom2_tvdung.model.Electronic;

/**
 *
 * @author TranVietDung
 */
public interface ElectronicDAO
{
    Electronic addNewElectronic(DetailElectronicDto detailElectronicDto);
    Electronic getElectronicByElectronicId(Long ElectronicId);
    DetailElectronicDto getDetailElectronicDtoByElectronicId(Long ElectronicId);
    List<Electronic> getAllElectronics();
    List<DetailElectronicDto> getAllDetailElectronicDto();
    void updateElectronic(DetailElectronicDto detailElectronicDto);
    void deleteElectronic(Long ElectronicId);
    
}
