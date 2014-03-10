package com.printful.api;

import com.printful.api.ProductCatalog.Product;
import com.printful.api.RequestObjects.ProductsRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.junit.runner.Result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class ProductsRequestTest
{
    @Test
    public void testGetAllProducts()
    {
        ProductsRequest request = new ProductsRequest("aewco3iz-8ta7-4ipp:qijx-u83bmxs9jie8");
        List<Product> products = request.getAllProductList("https://api.theprintful.com/products");
        assertNotNull(products);
    }
}
