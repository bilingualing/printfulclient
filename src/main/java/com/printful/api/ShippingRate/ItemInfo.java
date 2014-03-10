package com.printful.api.ShippingRate;

public class ItemInfo
{
    private String variant_id;
    private String quantity;

    public ItemInfo()
    {
        super();
    }

    public String getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
