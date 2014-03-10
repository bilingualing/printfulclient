package com.printful.api.Orders;

import java.security.Timestamp;
import java.util.List;

public class Order
{
    private int id;
    private String external_id;
    private String status;
    private String shipping;
    private Timestamp created;//I need to check and see if this is the correct timestamp
    private Timestamp updated;
    private Address recipient;
    private Item items;
    private Costs costs;
    private Costs retail_costs;
    private List<Shipment> shipments;
    private String notes;
}
