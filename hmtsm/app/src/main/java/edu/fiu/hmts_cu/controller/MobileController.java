package edu.fiu.hmts_cu.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.fiu.hmts_cu.model.HttpService;

/**
 * Class for mobile controller
 * Created by Wenbo on 3/21/2016.
 */
public class MobileController {

    /**
     * Login.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject login(JSONObject data){
        try {
            data.put("target", "/login");
            String res = HttpService.requestService(data);
            JSONObject loginRes = new JSONObject(res).getJSONObject("data");
            return loginRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Logout.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject logout(JSONObject data){
        try {
            data.put("target", "/logout");
            String res = HttpService.requestService(data);
            JSONObject logoutRes = new JSONObject(res);
            return logoutRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Register.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject register(JSONObject data){
        try {
            data.put("target", "/register");
            String res = HttpService.requestService(data);
            JSONObject regRes = new JSONObject(res);
            return regRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Display the menu.
     *
     * @return Results json array
     */
    public static JSONArray displayMenu(){
        try {
            JSONObject data = new JSONObject();
            data.put("target", "/displaymenu");
            String res = HttpService.requestService(data);
            JSONArray menuRes = new JSONObject(res).getJSONArray("data");
            return menuRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    /**
     * Display the shopping cart.
     *
     * @param data required info
     * @return Results json array
     */
    public static JSONArray displayCart(JSONObject data){
        try {
            data.put("target", "/displaycart");
            String res = HttpService.requestService(data);
            JSONArray cartRes = new JSONObject(res).getJSONArray("data");
            return cartRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    /**
     * Add a product into shopping cart.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject addProductCart(JSONObject data){
        try {
            data.put("target", "/selectproduct");
            String res = HttpService.requestService(data);
            JSONObject cartRes = new JSONObject(res);
            return cartRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Delete a product from shopping cart.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject delProductCart(JSONObject data){
        try {
            data.put("target", "/selectproduct");
            String res = HttpService.requestService(data);
            JSONObject cartRes = new JSONObject(res);
            return cartRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Create an order.
     *
     * @param data required info
     * @return Results json object
     */
    public static JSONObject newOrder(JSONObject data){
        try {
            data.put("target", "/placeorder");
            String res = HttpService.requestService(data);
            JSONObject orderRes = new JSONObject(res);
            return orderRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * Get secure questions.
     *
     * @return Results json object
     */
    public static JSONObject getQuestions(){
        try {
            JSONObject data = new JSONObject();
            data.put("target", "/getquestions");
            String res = HttpService.requestService(data);
            JSONObject quesRes = new JSONObject(res);
            return quesRes;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
