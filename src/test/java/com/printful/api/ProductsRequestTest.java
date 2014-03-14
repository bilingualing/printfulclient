package com.printful.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.printful.api.ProductCatalog.Product;
import com.printful.api.RequestObjects.ProductsRequest;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.Result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ProductsRequestTest
{
    @Rule
    public final TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void instantiationWithEmptyFileSource() throws IOException {
        Options options = new WireMockConfiguration().fileSource(new SingleRootFileSource(tempDir.getRoot()));

        WireMockServer wireMockServer = null;
        try {
            wireMockServer = new WireMockServer(options);
            wireMockServer.start();
        } finally {
            if (wireMockServer != null) {
                wireMockServer.stop();
            }
        }
    }

    //@Test
    public void testGetAllProducts()
    {

        /*stubFor(get(urlEqualTo("/some/products"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("{" +
                    "    \"code\": 200," +
                    "    \"result\": [" +
                    "        {" +
                    "            \"id\": 1," +
                    "            \"type\": \"POSTER\"," +
                    "            \"brand\": null,\n" +
                    "            \"model\": \"Poster\"," +
                    "            \"image\": \"https://www.theprintful.com/storage/products/poster_18x24.jpg\"," +
                    "            \"variant_count\": 6," +
                    "            \"files\": [" +
                    "                {" +
                    "                    \"id\": \"default\"," +
                    "                    \"title\": \"Print file\"," +
                    "                    \"additional_price\": null" +
                    "                }" +
                    "            ]," +
                    "            \"options\": []" +
                    "        }" +
                    "    ]" +
                    "}")));*/
        ProductsRequest request = new ProductsRequest("aewco3iz-8ta7-4ipp:qijx-u83bmxs9jie8");
        List<Product> products = request.getAllProductList("/products");
        assertNotNull(products);
        assertEquals(1,products.size());
    }
}
