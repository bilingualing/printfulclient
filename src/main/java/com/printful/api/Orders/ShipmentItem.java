package com.printful.api.Orders;

public class ShipmentItem
{
    private int item_id;
    private int quantity;

    public ShipmentItem()
    {
        super();
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
