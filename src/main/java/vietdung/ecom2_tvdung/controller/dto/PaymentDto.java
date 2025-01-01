package vietdung.ecom2_tvdung.controller.dto;


import java.text.NumberFormat;
import java.util.Locale;
import vietdung.ecom2_tvdung.model.PaymentMethod;
import vietdung.ecom2_tvdung.model.PaymentStatus;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TranVietDung
 */
public class PaymentDto
{
    private PaymentMethod selectedPaymentMethod;
    private Double preTax, tax, total;
    private String formatedPreTax;
    private String formatedTax;
    private String formatedTotal;
    
    public PaymentDto()
    {
    }

    public PaymentDto(PaymentMethod selectedPaymentMethod, Double preTax, Double tax, Double total)
    {
        this.selectedPaymentMethod = selectedPaymentMethod;
        this.preTax = preTax;
        this.tax = tax;
        this.total = total;
        this.formatedPreTax = formatValue(preTax);
        this.formatedTax = formatValue(tax);
        this.formatedTotal = formatValue(total);
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
    
    public PaymentMethod getSelectedPaymentMethod()
    {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(PaymentMethod selectedPaymentMethod)
    {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    public Double getPretax()
    {
        return preTax;
    }

    public void setPretax(Double preTax)
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

    public Double getPreTax()
    {
        return preTax;
    }

    public void setPreTax(Double preTax)
    {
        this.preTax = preTax;
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

    
    
    @Override
    public String toString()
    {
        return "PaymentDto{" + "selectedPaymentMethod=" + selectedPaymentMethod + ", formatedPreTax=" + formatedPreTax + ", formatedTax=" + formatedTax + ", formatedTotal=" + formatedTotal + '}';
    }

   
    
    
    
}
