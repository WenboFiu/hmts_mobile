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

    //private static String SERVICE_URL = "http://192.168.1.138:8080/hmts/mobile";
    private static String SERVICE_URL = "http://10.109.72.83:8080/hmts/mobile";
    private static int CONNECTION_TIMEOUT = 20000;
    private static int DATARETRIEVAL_TIMEOUT = 20000;

    /**
     * Request service on servers.
     * @param data object info
     * @return Results
     */
    public static String requestService(JSONObject data) {
        HttpURLConnection urlConn = null;
        String res = "";
        try {
            URL url = new URL(SERVICE_URL + data.get("login").toString());
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConn.setReadTimeout(DATARETRIEVAL_TIMEOUT);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json");

            String send = data.toString();
            OutputStream outputStream = urlConn.getOutputStream();
            outputStream.write(send.getBytes());
            outputStream.flush();

            int statusCode = urlConn.getResponseCode();
            if (statusCode == 200){
                InputStream in = new BufferedInputStream(urlConn.getInputStream());
                res = getResponseText(in);
            }
            else {
                res = "Not Found";
            }

        }catch (MalformedURLException e) {
            // URL is invalid
            System.out.println(e.getMessage());
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // response body is no valid JSON string
            System.out.println(e.getMessage());
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
            return  res;
        }
    }

    /**
     * Get the response text.
     * @param inStream input stream
     * @return Results
     */
    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}