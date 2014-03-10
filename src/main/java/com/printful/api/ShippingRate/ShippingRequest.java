package com.printful.api.ShippingRate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 2/5/14
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShippingRequest {
    private AddressInfo recipient;
    private List<ItemInfo> items;

    public ShippingRequest()
    {
        super();
    }

    public List<ItemInfo> getItems() {
        return items;
    }

    public void setItems(List<ItemInfo> items) {
        this.items = items;
    }

    public AddressInfo getRecipient() {
        return recipient;
    }

    public void setRecipient(AddressInfo recipient) {
        this.recipient = recipient;
    }
}
