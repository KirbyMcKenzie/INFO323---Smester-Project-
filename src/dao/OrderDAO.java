/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Order;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kirby McKenzie
 */
public class OrderDAO {

    private static Map<String, Order> orders = null;
    //private static Map<String, Order> consignments = null;
    private static Multimap<String, Order> consignments = ArrayListMultimap.create();

    // create some dummy orders for testing purposes
    public OrderDAO() {
        if (orders == null) {
            orders = new HashMap<>();
       
            //dummy order data
            orders.put("O123456", new Order("O123456", "PD1", "C666999333", "Big Mac", "unfilled", 4));
            orders.put("O345673", new Order("O345673", "PD2", "C948578346", "Cheese Burger", "unfilled", 10));
            orders.put("O576234", new Order("O458736", "PD3", "C323546356", "Chicken Nuggets", "unfilled", 900));

        }

        //dummy cosignment data
        consignments.put("C123456", new Order("C123456", "O123456", "P123456", 8, "unfilled"));
    }


   
    /**
     * Returns all order objects in the orders collection.
     * 
     * @return - returns all order objects in the orders collection.
     */
    public Collection<Order> getAll() {
        return orders.values();
    }
    /**
     * Returns an order object from the orders collection.
     * 
     * @param id - The ID of the order object to be checked
     * @return - returns single order object from the collection
     */
    public Order getByID(String id) {
        return orders.get(id);
    }
    
    /**
     * Checks if order exists in orders collection.
     * 
     * @param id - ID of the order object to be checked
     * @return  - returns true if order object exists in collection
     */
    public Boolean exists(String id) {
        return orders.containsKey(id);
    }

    /**
     * Creates an order object & stores it in orders collection.
     * 
     * @param order - The order object to be created
     */
    public void create(Order order) {
        orders.put(order.getOrderID(), order);
    }

    /**
     * Deletes the order from the orders collection.
     * 
     * @param order - Order object to be deleted
     */
    public void delete(Order order) {
        orders.remove(order.getOrderID());
    }

    /**
     * Updates the order from the orders collection.
     * 
     * @param id - ID of order to be updated
     * @param order - Order Object to be updated
     */
    public void updateOrder(String id, Order order) {
        orders.put(id, order);
    }

    /**
     * Returns order objects where they're status is 'unfilled'.
     * 
     * @return - Collection of Orders
     */
    public Collection<Order> getUnfilledOrders() {

        Collection<Order> unfilled = new ArrayList<>();

        for (Order order : orders.values()) {
            if (order.getStatus().equals("unfilled")) {
                unfilled.add(order);
            }

        }
        return unfilled;
    }

    /**
     * Changes order status from shipped to received.
     * 
     * @param id - ID of order 
     */
    public void recieveOrder(String id) {
        for (Order order : orders.values()) {
            if (order.getStatus().equals("shipped")) {
                order.setStatus("received");
            }

        }
    }

 
    /**
     * Returns the orders in the consignment by the consignment
     * ID.
     * 
     * @param id - ID of consignment
     * @return Orders in Consignment Multimap
     */   
    public Collection<Order> getOrdersInConsignments(String id) {
        return consignments.get(id);
    }

    /**
     * Returns orders by their consignment ID.
     * 
     * @param id - consignment ID
     * @return   orders by their consignment ID
     */
    public Collection<Order> getByConsignmentID(String id) {
        return consignments.get(id);
    }
    
    /**
     * Adds orders to the consignment Multimap.
     * 
     * @param id - ID of consignment
     * @param order - The order object
     */
    public void addOrderToConsignment(String id, Order order) {
        consignments.put(id, order);
        order.setConsignmentID(id);
        order.setStatus("consigned");

    }

    /**
     * Changes orders status from 'consigned' to 'shipped' when they are
     * processed.
     * 
     * @param id  - The consignment ID.
     */
    public void shipConsignment(String id) {
        for (Order order : consignments.get(id)) {
            if (order.getStatus().equals("consigned")) {
                order.setStatus("shipped");
            }

        }
    }

} // END ORDERDAO CLASS
