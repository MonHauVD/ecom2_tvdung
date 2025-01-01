/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vietdung.ecom2_tvdung.controller.dto;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import vietdung.ecom2_tvdung.model.Cart;
import vietdung.ecom2_tvdung.model.CartItem;
import vietdung.ecom2_tvdung.model.Order;
import vietdung.ecom2_tvdung.model.OrderState;

/**
 *
 * @author TranVietDung
 */
public class OrderListDto
{
    private Long id;
    private String orderDate;
    private String listItem;
    private String totalPrice;
    private OrderState state;

    public OrderListDto()
    {
    }

    public OrderListDto(Long id, String orderDate, String listItem, double totalPrice, OrderState state)
    {
        this.id = id;
        this.orderDate = orderDate;
        this.listItem = listItem;
        this.totalPrice = formatValue(totalPrice);
        this.state = state;
    }
    
    public OrderListDto(Order order)
    {
        this.id = order.getId();
        try
        {
            this.orderDate = order.getOrderDate().getTime().toString();
        } catch (Exception e)
        {
            this.orderDate = "Not has order date!";
        }
        Cart cart = order.getCart();
        if(cart == null)
        {
            this.listItem = "Empty!";
        }
        else
        {
            List<CartItem> lsCI = cart.getCartItems();
            StringBuilder lsItemStr = new StringBuilder();
            for(CartItem ci : lsCI)
            {
                lsItemStr.append(ci.getItem().getName() + ": " + ci.getQuantity() + ";\n");
            }
            this.listItem = lsItemStr.toString();
        }
        try
        {
            this.totalPrice = formatValue(order.getPayment().getTotal());
            this.state = order.getState();
        } catch (Exception e)
        {
        }
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getListItem()
    {
        return listItem;
    }

    public void setListItem(String listItem)
    {
        this.listItem = listItem;
    }

    public String getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice)
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

    @Override
    public String toString()
    {
        return "OrderListDto{" + "id=" + id + ", orderDate=" + orderDate + ", listItem=" + listItem + ", totalPrice=" + totalPrice + ", state=" + state + '}';
    }
    
    public String formatValue (Double value)
    {
        if (value == null)
        {
            return null;
        }
        Locale locale = new Locale("vi", "VN"); // Vietnam locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formatted = currencyFormatter.format(value).replace("₫", "").trim() + " đ";
        return formatted;
    }
    
}
