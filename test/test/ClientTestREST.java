/*
 *  Created by: Kirby McKenzie | ID:6601021
 *  INFO323 Course Project 2016, Phase 1
 */
package test;

import domain.Order;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kirby McKenzie
 */
public class ClientTestREST {

    // Data Fields
    private Order order;
    private Order consignment;
    private WebTarget orderTarget;
    private WebTarget consignmentTarget;

    /**
     * Default Constructor
     */
    public ClientTestREST() {
    }

    /**
     * SetUp() method initializes Client Configuration and WebTarget URIs.
     */
    @Before
    public void setUp() {

        // Create a client configuration.
        ClientConfig config = new ClientConfig();

        // Create client endpoint.
        Client client = ClientBuilder.newClient(config);

        // Creates a web target for root of the orders service.
        orderTarget = client.target("http://localhost:8081/orders/");

        // Creates a web target for root of the consignment service.
        consignmentTarget = client.target("http://localhost:8081/consignment/");

        // Create new order object for testing purposes & POST it in JSON.
        order = new Order("O123456", "PD1", "C666999333", "Big Mac", "unfilled", 4);
        orderTarget.request().post(Entity.entity(order, "application/json"));

        // Create new order object with consignment details  & POST it in JSON
        consignment = new Order("C123456", "O123456", "P123456", 8, "unfilled");
        consignmentTarget.request().post(Entity.entity(consignment, "application/json"));

    } //end setUp() method.

    /**
     * Cleans-up after tests
     */
    @After
    public void tearDown() {

        // Remove dummy data after tests run.
        orderTarget.path("O123456").request().delete();
        consignmentTarget.path("C123456").request().delete();

    } // end tearDown() method.

  
    /**
     * Tests if order can be retrieved using GET.
     */
    @Test
    public void testOrderGET() {
        // GET the RAW json for the order Ids
        String rawIds = orderTarget.request("text/json").get(String.class);

        assertTrue(rawIds.contains("O123456") && rawIds.contains("PD1"));
    }

    /**
     * Tests if Order can be deleted.
     */
    @Test(expected = javax.ws.rs.NotFoundException.class)
    public void testDeleteOrder() {

        orderTarget.path("O123456").request().delete();

        Order testOrder = orderTarget.path("O123456")
                .request("application/json").get(Order.class);

    } // end testDeleteOrder() method.

    /**
     * Gets the orders in consignment
     */
    @Test

    public void getOrderInConsignment() {

        Collection<Order> allOrders = consignmentTarget.path("CON233")
                .request().get(new GenericType<List<Order>>() {
                });

        for (Order aOrder : allOrders) {

            if (!aOrder.getConsignmentID().equals("CON233")) {

                fail();

            }
        }

    } // end getOrderInConsignment

    /**
     * Tests if order can be PUT in consignment
     */
    @Test
    public void AddOrderInConsignmentPUT() {
        Collection<Order> OrdersInConsignment = consignmentTarget.path("unfilledProds")
                .request().get(new GenericType<List<Order>>() {
                });
        for (Order aOrder : OrdersInConsignment) {
            if (aOrder.getConsignmentID().equals("consigned")) {
                aOrder.setStatus("shipped");

                Order aOrderInCon = consignmentTarget.path("OR233")
                        .request("application/json").get(Order.class);

                if (!aOrderInCon.equals("shipped")) {

                    fail();

                }
            }

        }

    } // end AddOrderInConsignmentPUT

     /**
      * tests if order can be updated
      */
     @Test
     public void testOrderUpdatePUT(){
     Order testOrder = orderTarget.path("O123456")
     .request("application/json").get(Order.class);
         
     orderTarget.path("O999999").request().put(Entity.entity(testOrder, "text/xml"));  
      
     assertEquals("must be equal",testOrder, order);
    
     } // end testOrderPUT
    
     
} // end clientTest
