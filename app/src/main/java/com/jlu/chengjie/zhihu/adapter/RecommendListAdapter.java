/*
 *    Copyright [2019] [chengjie.jlu@qq.com]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jlu.chengjie.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;
import com.jlu.chengjie.zhihu.view.IDisplay;

import java.util.List;

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.ViewHolder> {

    private List<IDisplayItem> items;

    private LayoutInflater inflater;

    private ScrollBottomListener listener;

    private static final int NEAR_BOTTOM_COUNT = 3;

    public RecommendListAdapter(Context context, List<IDisplayItem> items, ScrollBottomListener listener) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view = inflater.inflate(getLayoutId(type), viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.contentView.onBind(items.get(i));
        if (getItemCount() - i < NEAR_BOTTOM_COUNT) listener.onScrollBottom();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        IDisplay contentView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentView = (IDisplay) itemView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType().ordinal();
    }

    private int getLayoutId(int type) {
        switch (IDisplayItem.ViewType.values()[type]) {
            case NORMAL_QUESTION:
                return R.layout.item_recommend_question;
            case FOOTER_LOADING_MORE:
                return R.layout.item_footer_load_more;
        }
        throw new IllegalArgumentException("unknown view type: " + type);
    }

    public void notifyBottomRemoved() {
        notifyItemRemoved(getItemCount() - 1);
    }

    public void notifyBottomInsert() {
        notifyItemInserted(getItemCount() - 1);
    }


    public interface ScrollBottomListener {

        void onScrollBottom();
    }
}
