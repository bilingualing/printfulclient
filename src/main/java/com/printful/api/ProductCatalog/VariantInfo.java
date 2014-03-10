package com.printful.api.ProductCatalog;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 2/6/14
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class VariantInfo
{
    private Variant variant;
    private Product product;

    public VariantInfo()
    {
        super();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
