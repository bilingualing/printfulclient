package com.printful.api;

import com.printful.api.ProductCatalog.Product;
import com.printful.api.RequestObjects.ProductsRequest;
import com.printful.api.Tools.Tools;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.Result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductsRequestTest
{

    private ProductsRequest mockedProductRequestObject = mock(ProductsRequest.class);
    private CloseableHttpResponse mockedResponse = mock(CloseableHttpResponse.class);
    private List<Product> productList = mock(List.class);
    private HttpEntity entity = mock(HttpEntity.class);
    private String allProductsList =
            "{\"code\": 200,\"result\": [{\"id\": 1,\"type\": \"POSTER\",\"brand\": null,\"model\": \"Poster\",\"image\"" +
            ": \"https://www.theprintful.com/storage/products/poster_18x24.jpg\",\"variant_count\": 6,\"files\": [{\"id\"" +
            ": \"default\",\"title\": \"Print file\",\"additional_price\": null},{\"id\": \"preview\",\"title\": \"Mocku" +
            "p\",\"additional_price\": null}],\"options\": []},{\"id\": 2,\"type\": \"FRAMED-POSTER\",\"brand\": null,\"" +
            "model\": \"Framed Poster\",\"image\": \"https://www.theprintful.com/storage/products/framed_poster_18x24.jp" +
            "g\",\"variant_count\":3,\"files\":[{\"id\":\"default\",\"title\":\"Print file\",\"additional_price\": null}" +
            ",{\"id\":\"preview\",\"title\": \"Mockup\",\"additional_price\": null}],\"options\": []}]}";

    @Before
    public void setUp() throws IOException
    {
        //entity.getContent()
        when(EntityUtils.toString(entity)).thenReturn(allProductsList);
        mockedResponse.setStatusCode(200);
        mockedResponse.setEntity(entity);
        when(mockedProductRequestObject.getAllProductList(mockedResponse)).thenReturn(productList);
    }

    @Test
    public void testGetAllProductsList()
    {
        List<Product> products = mockedProductRequestObject.getAllProductList(mockedResponse);
        assertEquals(2,products.size());
    }

}
