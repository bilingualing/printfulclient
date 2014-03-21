package com.printful.api.TestingInterfaces;

import com.printful.api.ProductCatalog.Product;
import com.printful.api.ProductCatalog.ProductInfo;
import com.printful.api.ProductCatalog.VariantInfo;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 3/21/14
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IProductsRequest
{
    public List<Product> getAllProductList(CloseableHttpResponse response);

    public VariantInfo getVariantInfo(CloseableHttpResponse response);

    public ProductInfo getProductsVariantList(CloseableHttpResponse response);

}
