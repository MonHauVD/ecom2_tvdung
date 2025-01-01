/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import java.text.NumberFormat;
import java.util.Locale;
import vietdung.ecom2_tvdung.model.*;
/**
 *
 * @author TranVietDung
 */
public class OrderDto
{
    private Long orderId;
    private ShipmentMethod selectedShipmentMethod;
    private PaymentMethod selectedPaymentMethod;
    private Double totalPrice;
    private Double shipmentFee;
    private Double preTax;
    private Double tax;
    private Double total;

    
    private String formatedTotalPrice;
    private String formatedShipmentFee;
    private String formatedPreTax;
    private String formatedTax;
    private String formatedTotal;
    
    private OrderState state;
    private String orderDate;
    private String receiveDate;
//    private String shipmentMethod;
    private String shipmentAddress;
//    private String paymentMethod;
    
    public OrderDto()
    {
    }

    public OrderDto(ShipmentMethod selectedShipmentMethod, PaymentMethod selectedPaymentMethod, Double shipmentFee, Double preTax, Double tax, Double total, Double totalPrice)
    {
        this.selectedShipmentMethod = selectedShipmentMethod;
        this.selectedPaymentMethod = selectedPaymentMethod;
        this.totalPrice = totalPrice;
        this.shipmentFee = shipmentFee;
        this.preTax = preTax;
        this.tax = tax;
        this.total = total;
        this.formatedTotalPrice = formatValue(totalPrice);
        this.formatedShipmentFee = formatValue(shipmentFee);
        this.formatedPreTax = formatValue(preTax);
        this.formatedTax = formatValue(tax);
        this.formatedTotal = formatValue(total);
    }

    //for order detail page
    public OrderDto(Order order)
    {
        try
        {
            this.orderId = order.getId();
            Shipment shipment = order.getShipment();
            this.selectedShipmentMethod = shipment.getMethod();
            this.shipmentAddress = shipment.getCustomerAddress();

            Payment payment = order.getPayment();
            this.selectedPaymentMethod = payment.getMethod();
            this.formatedTotalPrice = formatValue(payment.getTotal());
            this.state = order.getState();
           
        } catch (Exception e)
        {
        }
        
        try
        {
            this.orderDate = order.getOrderDate().getTime().toString();
        } catch (Exception e)
        {
            this.orderDate = "Not has order date!";
        }
        try
        {
             this.receiveDate = order.getReceiveDate().getTime().toString();
        } catch (Exception e)
        {
            this.receiveDate = "Has not received!";
        }
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public ShipmentMethod getSelectedShipmentMethod()
    {
        return selectedShipmentMethod;
    }

    public void setSelectedShipmentMethod(ShipmentMethod selectedShipmentMethod)
    {
        this.selectedShipmentMethod = selectedShipmentMethod;
    }

    public PaymentMethod getSelectedPaymentMethod()
    {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(PaymentMethod selectedPaymentMethod)
    {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    

    public Double getShipmentFee()
    {
        return shipmentFee;
    }

    public void setShipmentFee(Double shipmentFee)
    {
        this.shipmentFee = shipmentFee;
        this.formatedShipmentFee = formatValue(shipmentFee);
    }

    public Double getPreTax()
    {
        return preTax;
    }

    public void setPreTax(Double preTax)
    {
        this.preTax = preTax;
        this.formatedPreTax = formatValue(preTax);
    }

    public Double getTax()
    {
        return tax;
    }

    public void setTax(Double tax)
    {
        this.tax = tax;
        this.formatedTax = formatValue(tax);
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
        this.formatedTotal = formatValue(total);
    }

    public String getFormatedShipmentFee()
    {
        return formatedShipmentFee;
    }

    public void setFormatedShipmentFee(String formatedShipmentFee)
    {
        this.formatedShipmentFee = formatedShipmentFee;
    }

    public String getFormatedPreTax()
    {
        return formatedPreTax;
    }

    public void setFormatedPreTax(String formatedPreTax)
    {
        this.formatedPreTax = formatedPreTax;
    }

    public String getFormatedTax()
    {
        return formatedTax;
    }

    public void setFormatedTax(String formatedTax)
    {
        this.formatedTax = formatedTax;
    }

    public String getFormatedTotal()
    {
        return formatedTotal;
    }

    public void setFormatedTotal(String formatedTotal)
    {
        this.formatedTotal = formatedTotal;
    }

    public String getFormatedTotalPrice()
    {
        return formatedTotalPrice;
    }

    public void setFormatedTotalPrice(String formatedTotalPrice)
    {
        this.formatedTotalPrice = formatedTotalPrice;
        this.formatedTotalPrice = formatValue(totalPrice);
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public OrderState getState()
    {
        return state;
    }

    public void setState(OrderState state)
    {
        this.state = state;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getReceiveDate()
    {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate)
    {
        this.receiveDate = receiveDate;
    }

    public String getShipmentAddress()
    {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress)
    {
        this.shipmentAddress = shipmentAddress;
    }
    
    
    
    public String formatValue (Double value)
    {
        if (value == null)
        {
            return new String();
        }
        else
        {
            Locale locale = new Locale("vi", "VN"); // Vietnam locale
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            String formatted = currencyFormatter.format(value).replace("₫", "").trim() + " đ";
            return formatted;
        }
        
    }
    
    
}
