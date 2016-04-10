package edu.fiu.hmts_cu.customization;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.fiu.hmts_cu.R;

/**
 * Created by Wenbo on 4/6/2016.
 */
public class CartListAdapter extends BaseAdapter {

    /**
     * The Items.
     */
    JSONArray items;
    /**
     * The Context.
     */
    Context context;
    /**
     * The constant inflater.
     */
    private static LayoutInflater inflater = null;

    /**
     * The type Holder.
     */
    class Holder{
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
         * The Total.
         */
        TextView total;
    }

    /**
     * Instantiates a new Cart list adapter.
     *
     * @param activity the activity
     * @param cartlist the cartlist
     */
    public CartListAdapter(Activity activity, JSONArray cartlist){
        items = cartlist;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listview_cart_row, null);
        holder.name = (TextView)rowView.findViewById(R.id.cartname);
        holder.type = (TextView)rowView.findViewById(R.id.carttype);
        holder.price = (TextView)rowView.findViewById(R.id.cartprice);
        holder.quantity = (TextView)rowView.findViewById(R.id.cartquatity);
        holder.total = (TextView)rowView.findViewById(R.id.carttotal);

        try {
            holder.name.setText(items.getJSONObject(position).get("name").toString());
            holder.type.setText(items.getJSONObject(position).get("type").toString());
            holder.price.setText(items.getJSONObject(position).get("price").toString());
            holder.quantity.setText(items.getJSONObject(position).get("quantity").toString());
            holder.total.setText(String.format("%.2f", Double.parseDouble(holder.price.getText().toString())
                    * Double.parseDouble(holder.quantity.getText().toString())));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
