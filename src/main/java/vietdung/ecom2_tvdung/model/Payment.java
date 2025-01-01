package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PaymentMethod method;
    private PaymentStatus status;
    private Double pretax, tax, total;

    public Payment()
    {
        this.status = PaymentStatus.unpaid;
    }
    
    public Payment(PaymentMethod method, Double pretax)
    {
        this.status = PaymentStatus.unpaid;
        this.method = method;
        this.pretax = pretax;
        this.tax = pretax*0.1;
        this.total = pretax + tax;
        
    }
    
    public void calculatePayment()
    {
        if(pretax != null)
        {
            this.tax = pretax*0.1;
            this.total = pretax + tax;
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

    public PaymentMethod getMethod()
    {
        return method;
    }

    public void setMethod(PaymentMethod method)
    {
        this.method = method;
    }

    public PaymentStatus getStatus()
    {
        return status;
    }

    public void setStatus(PaymentStatus status)
    {
        this.status = status;
    }

    public Double getPretax()
    {
        return pretax;
    }

    public void setPretax(Double pretax)
    {
        this.pretax = pretax;
    }

    public Double getTax()
    {
        return tax;
    }

    public void setTax(Double tax)
    {
        this.tax = tax;
    }

    public Double getTotal()
    {
        return total;
    }

    public void setTotal(Double total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "Payment{" + "id=" + id + ", method=" + method + ", status=" + status + ", pretax=" + pretax + ", tax=" + tax + ", total=" + total + '}';
    }

    
}