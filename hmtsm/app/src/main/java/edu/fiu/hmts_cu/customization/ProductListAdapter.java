package edu.fiu.hmts_cu.customization;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.fiu.hmts_cu.R;

/**
 * Created by Wenbo on 4/6/2016.
 */
public class ProductListAdapter extends BaseAdapter {

    /**
     * The Items.
     */
    JSONArray items;
    /**
     * The Context.
     */
    Activity context;
    /**
     * The constant inflater.
     */
    private static LayoutInflater inflater = null;

    /**
     * The type Holder.
     */
    class Holder{
        /**
         * The Id.
         */
        TextView id;
        /**
         * The Name.
         */
        TextView name;
        /**
         * The Type.
         */
        TextView type;
        /**
         * The Price.
         */
        TextView price;
        /**
         * The Quantity.
         */
        TextView quantity;
        /**
         * The Add.
         */
        Button add;
        /**
         * The Minus.
         */
        Button minus;
        /**
         * The Brief.
         */
        TextView brief;
    }

    /**
     * Instantiates a new Product list adapter.
     *
     * @param activity the activity
     * @param products the products
     */
    public ProductListAdapter(Activity activity, JSONArray products){
        items = products;
        context = activity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return items.length();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        try {
            return items.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        try {
            JSONObject object = items.getJSONObject(position);
            String itemId = object.get("productId").toString();
            long res = Long.valueOf(itemId);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        LayoutInflater inflater = context.getLayoutInflater();
       if (convertView == null){
           holder = new Holder();
           convertView = inflater.inflate(R.layout.listview_product_row, null);
           holder.id = (TextView)convertView.findViewById(R.id.prodid);
           holder.name = (TextView)convertView.findViewById(R.id.productname);
           holder.type = (TextView)convertView.findViewById(R.id.producttype);
           holder.price = (TextView)convertView.findViewById(R.id.productprice);
           holder.quantity = (TextView)convertView.findViewById(R.id.prodquatity);
           holder.add = (Button)convertView.findViewById(R.id.prodadd);
           holder.minus = (Button)convertView.findViewById(R.id.prodminus);
           holder.brief = (TextView)convertView.findViewById(R.id.productbrief);
           convertView.setTag(holder);
        }
        else {
           holder = (Holder)convertView.getTag();
        }

        try {
            holder.id.setText(items.getJSONObject(position).get("productId").toString());
            holder.name.setText(items.getJSONObject(position).get("name").toString());
            holder.type.setText(items.getJSONObject(position).get("type").toString());
            holder.price.setText(items.getJSONObject(position).get("price").toString());
            holder.brief.setText(items.getJSONObject(position).get("brief").toString());
            holder.quantity.setText(items.getJSONObject(position).get("quantity").toString());
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"20".equals(holder.quantity.getText().toString())){
                        String curQTY = String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) + 1);
                        try {
                            items.getJSONObject(position).put("quantity", curQTY);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        holder.quantity.setText(curQTY);
                    }
                }
            });
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"0".equals(holder.quantity.getText().toString())){
                        String curQTY = String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) - 1);
                        try {
                            items.getJSONObject(position).put("quantity", curQTY);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        holder.quantity.setText(curQTY);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
