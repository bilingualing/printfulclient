package com.printful.api.ShippingRate;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 2/5/14
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShippingInfo {
    private String id;
    private String name;
    private String rate;

    public ShippingInfo()
    {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
