package com.printful.api.Interfaces;

import com.printful.api.ProductCatalog.Product;
import com.printful.api.ProductCatalog.ProductInfo;
import com.printful.api.ProductCatalog.VariantInfo;

import java.util.List;

public interface ProductsRequest {

    public List<Product> getProductsList();

    public VariantInfo getProductVariantInfo();

    public List<ProductInfo> getProductVariantList();
}
