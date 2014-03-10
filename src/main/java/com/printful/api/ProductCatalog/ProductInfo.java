package com.printful.api.ProductCatalog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 2/6/14
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductInfo {

    private Product product;
    private List<Variant> variants;

    public ProductInfo()
    {
        super();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
