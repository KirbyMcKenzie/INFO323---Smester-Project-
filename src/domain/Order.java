/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package domain;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kirby McKenzie
 */
@XmlRootElement
public class Order {

    private String orderID;
    private String productID;
    private String consignmentID;
    private String productName;
    private String status;
    private Integer reorderAmount;

    public Order() {
    }

    public Order(String orderID, String productID, String consignmentID, String productName, String status, Integer reorderAmount) {
        this.orderID = orderID;
        this.productID = productID;
        this.consignmentID = consignmentID;
        this.productName = productName;
        this.status = status;
        this.reorderAmount = reorderAmount;
    }

    public Order(String consignmentID, String orderID, String productID, Integer reorderAmount, String status) {
        this.orderID = orderID;
        this.productID = productID;
        this.consignmentID = consignmentID;
        this.status = status;
        this.reorderAmount = reorderAmount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReorderAmount() {
        return reorderAmount;
    }

    public void setReorderAmount(Integer reorderAmount) {
        this.reorderAmount = reorderAmount;
    }

    public String getConsignmentID() {
        return consignmentID;
    }

    public void setConsignmentID(String consignmentID) {
        this.consignmentID = consignmentID;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", productID=" + productID + ", consignmentID=" + consignmentID + ", productName=" + productName + ", status=" + status + ", reorderAmount=" + reorderAmount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.orderID);
        hash = 53 * hash + Objects.hashCode(this.productID);
        hash = 53 * hash + Objects.hashCode(this.consignmentID);
        hash = 53 * hash + Objects.hashCode(this.productName);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.reorderAmount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderID, other.orderID)) {
            return false;
        }
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        if (!Objects.equals(this.consignmentID, other.consignmentID)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.reorderAmount, other.reorderAmount)) {
            return false;
        }
        return true;
    }

   
    

}
