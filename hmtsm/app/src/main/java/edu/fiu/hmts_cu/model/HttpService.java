package edu.fiu.hmts_cu.model;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;


/**
 * Class for web service communication.
 * Created by Wenbo on 3/3/2016.
 */
public class HttpService {

    /**
     * The constant SERVICE_URL.
     */
    private static String SERVICE_URL = "http://192.168.1.125:8080/hmts/mobile/service";
    /**
     * The constant CONNECTION_TIMEOUT.
     */
//private static String SERVICE_URL = "http://10.109.228.27:8080/hmts/mobile/service";
    private static int CONNECTION_TIMEOUT = 20000;
    /**
     * The constant DATARETRIEVAL_TIMEOUT.
     */
    private static int DATARETRIEVAL_TIMEOUT = 20000;

    /**
     * Request service on servers.
     *
     * @param data object info
     * @return Results string
     */
    public static String requestService(JSONObject data) {
        HttpURLConnection urlConn = null;
        String res = "";
        try {
            URL url = new URL(SERVICE_URL + data.getString("target"));
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConn.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            if (data.length() > 1){
                if ("/placeorder".equals(data.getString("target")))
                    urlConn.setRequestProperty("Content-Type", "text/plain");
                else
                    urlConn.setRequestProperty("Content-Type", "application/json");
                data.remove("target");
                urlConn.setDoOutput(true);
                urlConn.setRequestMethod("POST");
                String send = data.toString();
                OutputStream outputStream = urlConn.getOutputStream();
                outputStream.write(send.getBytes());
                outputStream.flush();
            }
            else{
                urlConn.setDoOutput(false);
                urlConn.setRequestMethod("GET");
            }

            int statusCode = urlConn.getResponseCode();
            if (statusCode == 200){
                InputStream in = new BufferedInputStream(urlConn.getInputStream());
                res = getResponseText(in);
            }
            else {
                res = "{\"result\":\"failed\"}";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (urlConn != null)
            urlConn.disconnect();
        return  res;
    }

    /**
     * Get the response text.
     *
     * @param inStream input stream
     * @return Results response text
     */
    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}