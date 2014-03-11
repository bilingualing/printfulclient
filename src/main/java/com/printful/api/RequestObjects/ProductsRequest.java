package com.printful.api.RequestObjects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import com.printful.api.ProductCatalog.Product;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonNode;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

/**
 *
 * HttpClient vs. HttpCore
 *
 * HttpClient is a Client-side HTTP transport library based on HttpCore.
 *
 * HttpCore is a set of components implementing the most fundamental aspects of the HTTP protocol
 * that are nonetheless sufficient to develop full-featured client-side and server-side HTTP services with
 * a minimal footprint.
 */
public class ProductsRequest
{
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private CloseableHttpResponse response;
    private HttpEntity entity;
    private Gson gson = new Gson();
    private Base64 codec = new Base64();
    private String apiKey;
    private String encodedApiKey;
    private String jsonResponse;
    private ObjectMapper mapper = new ObjectMapper();
    private String USER_AGENT = "Printful API Java Library 1.0";
    private String urlBase = "https://api.theprintful.com";

    public ProductsRequest(String apiKey)
    {
        super();
        this.apiKey = apiKey;
        this.encodedApiKey = codec.encodeBase64String(apiKey.getBytes());
    }

    public HttpResponse createRequest(String requestType, String urlPart)
    {
        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8*1204);
        ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;

        HttpHost host = new HttpHost(urlBase, 8080);


        //request to be sent
        HttpRequest request = new BasicHttpRequest(requestType,urlBase+urlPart, HttpVersion.HTTP_1_1);

        //string to be added to the request header
        String basicString = "Basic %s"+this.encodedApiKey;
        request.addHeader("Authorization",basicString.substring(0,basicString.length()-2));
        request.addHeader("User-Agent",USER_AGENT);

        //HttpRequestExecutor is a client side HTTP protocol handler based on the blocking I/O model that
        //implements the essential requirements of the HTTP protocol for the client side message processing,
        //as described by RFC 2616
        HttpRequestExecutor httpRequestExecutor = new HttpRequestExecutor();

        HttpProcessor httpProcessor = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .build();

        HttpCoreContext context = HttpCoreContext.create();

        try
        {
            if (!conn.isOpen())
            {
                Socket socket = new Socket(host.getHostName(), host.getPort());
                conn.bind(socket);
            }
            httpRequestExecutor.preProcess(request,httpProcessor,context);
            HttpResponse response = httpRequestExecutor.execute(request,conn,context);
            httpRequestExecutor.postProcess(response,httpProcessor,context);

            if (!connStrategy.keepAlive(response, context)) {
                conn.close();
            }

            conn.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }


        return response;

    }

    /**
     * For some reason using httpclient isn't working. I'm now reading the documentation for the underlying httpcore
     * to see if I can figure out what is going on. From looking at their other examples, it also looks like this might
     * be a better way to write the client anyways.
     * @return
     */
    public List<Product> getAllProductList(String urlPart)
    {
        //the list of products that will be returned
        List<Product> returnedProducts = null;

        //request to be sent
        String basicString = "Basic %s"+this.encodedApiKey;
        HttpGet get = new HttpGet(urlBase+urlPart);
        get.addHeader("Authorization",basicString.substring(0,basicString.length()-2));
        get.addHeader("User-Agent",USER_AGENT);

        try
        {
            response = httpClient.execute(get);
            if(response.getStatusLine().getStatusCode() == 200)
            {
                entity = response.getEntity();
                jsonResponse = EntityUtils.toString(entity);
                Type listType = new TypeToken<List<Product>>(){}.getType();
                JsonNode root = mapper.readTree(jsonResponse);
                ArrayNode products = (ArrayNode) root.path("result");
                returnedProducts = (List<Product>) gson.fromJson(products.toString(),listType);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return returnedProducts;
    }
}
