package com.dali.www.drrerxmvpdemo.ui.easyadapter;

import android.content.Context;
import android.view.ViewGroup;

import com.dali.www.drrerxmvpdemo.R;
import com.dali.www.drrerxmvpdemo.bean.User;
import com.dali.www.drrerxmvpdemo.widget.recycleview.adapter.BaseViewHolder;
import com.dali.www.drrerxmvpdemo.widget.recycleview.adapter.RecyclerArrayAdapter;

/**
 * @author lfh.
 * @date 16/9/3.
 */
public class MainAdapter extends RecyclerArrayAdapter<User.DataBean> {


    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<User.DataBean>(parent, R.layout.item_main) {
            @Override
            public void setData(User.DataBean item) {
                holder.setText(R.id.tv,item.getType()+"");
            }
        };
    }
}
