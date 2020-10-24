package dat.project.listcontex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewBaseAdapter extends BaseAdapter {
    public ArrayList<ListViewBean> arrayingListener;
    Context mContext;
    public ListViewBaseAdapter(ArrayList<ListViewBean> mListenerList, Context context) {
        mContext = context;
        this.arrayingListener = new ArrayList<ListViewBean>();
        this.arrayingListener =mListenerList;
    }

    public void notifyDataSetChanged() {
    }

    public class ViewHolder {
        ImageView mItemPic;
        TextView mFoodProduct,mPrice;
    }

    @Override
    public int getCount() {
        return arrayingListener.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayingListener.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) view.findViewById(R.id.image);
            holder.mFoodProduct=(TextView)view.findViewById(R.id.txtProduct);
            holder.mPrice=(TextView)view.findViewById(R.id.txtPrice);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            int image = arrayingListener.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mFoodProduct.setText(arrayingListener.get(position).getFoodProduct());
            holder.mPrice.setText(arrayingListener.get(position).getPrice());
        }catch (Exception ex){
        }
        return view;
    }


}
