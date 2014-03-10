package com.printful.api.Orders;

public class ItemOption
{
    private int id;
    private Object value;

    public ItemOption()
    {
        super();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
