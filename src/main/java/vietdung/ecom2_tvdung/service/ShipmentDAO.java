/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import vietdung.ecom2_tvdung.model.Shipment;

/**
 *
 * @author TranVietDung
 */
public interface ShipmentDAO
{
    public Shipment getShipmentById(Long id);
}
