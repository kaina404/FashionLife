package fashionlife.com.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fashionlife.com.base.impl.OnRecycleViewItemClickListener;
import fashionlife.com.base.viewholder.RecycleBaseViewHolder;

/**
 * Created by lovexujh on 2017/10/17
 */

public abstract class RecycleBaseAdapter<T> extends RecyclerView.Adapter<RecycleBaseViewHolder> implements OnRecycleViewItemClickListener {

    private int mLayoutId;
    protected Context mContext;
    protected List<T> mData;

    public RecycleBaseAdapter(Context context, int layoutId, List<T> data) {
        mContext = context;
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData = data;
        mLayoutId = layoutId;
    }

    @Override
    public RecycleBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecycleBaseViewHolder.create(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(RecycleBaseViewHolder holder, int position) {
        holder.setPosition(position);
        setItemData(holder, mData.get(position), position);
    }

    public abstract void setItemData(RecycleBaseViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
