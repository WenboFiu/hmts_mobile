package edu.fiu.hmts_cu.customcontrol;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.fiu.hmts_cu.R;

/**
 * Created by Wenbo on 4/6/2016.
 */
public class ProductListAdapter extends BaseAdapter {

    JSONArray items;
    Context context;
    private static LayoutInflater inflater = null;

    class Holder{
        TextView name;
        TextView type;
        TextView price;
        Spinner quantity;
        Button buy;
        TextView brief;
    }

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

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listview_product_row, null);
        holder.name = (TextView)rowView.findViewById(R.id.productname);
        holder.type = (TextView)rowView.findViewById(R.id.producttype);
        holder.price = (TextView)rowView.findViewById(R.id.productprice);
        holder.buy = (Button)rowView.findViewById(R.id.productbuy);
        holder.brief = (TextView)rowView.findViewById(R.id.productbrief);

        try {
            holder.name.setText(items.getJSONObject(position).get("name").toString());
            holder.type.setText(items.getJSONObject(position).get("type").toString());
            holder.price.setText(items.getJSONObject(position).get("price").toString());
            holder.brief.setText(items.getJSONObject(position).get("brief").toString());
            holder.buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
