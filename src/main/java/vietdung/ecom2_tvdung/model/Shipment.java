package vietdung.ecom2_tvdung.model;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ShipmentMethod method;
    private String customerAddress;
    private Double distance;
    
    private final Double pricePerKm = 1000.0;
    private final String addressOfThisShop = "Km10, Nguyễn Trãi, Hà Đông, Hà Nội";

    
    public Shipment()
    {
    }

    public Shipment(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    
    
    public Shipment(ShipmentMethod method, String customerAddress)
    {
        this.method = method;
        this.customerAddress = customerAddress;
        this.distance = calculateDistance();
    }
    
    public Double calculateDistance()
    {
        double randomValue = 1 + (Math.random() * (50 - 1));
        return randomValue;
    }
    
    public Double calculateFeeShip()
    {
        if (this.distance == null)
        {
            this.distance = calculateDistance();
        }
        Double base = this.distance * this.pricePerKm;
        if (method == null)
        {
            base = null;
        }
        else if (method.equals(ShipmentMethod.Motorcycle))
        {
            base *= 1;
        }
        else if (method.equals(ShipmentMethod.Car))
        {
            base *= 1.5;
        }
        else if (method.equals(ShipmentMethod.Drone))
        {
            base *= 2;
        }
        return base;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public ShipmentMethod getMethod()
    {
        return method;
    }

    public void setMethod(ShipmentMethod method)
    {
        this.method = method;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public Double getDistance()
    {
        return distance;
    }

    public void setDistance(Double distance)
    {
        this.distance = distance;
    }

    @Override
    public String toString()
    {
        return "Shipment{" + "id=" + id + ", method=" + method + ", customerAddress=" + customerAddress + ", distance=" + distance + ", pricePerKm=" + pricePerKm + ", addressOfThisShop=" + addressOfThisShop + '}';
    }

    
}