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

package com.jlu.chengjie.zhihu.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.adapter.BaseRecyclerViewAdapter;
import com.jlu.chengjie.zhihu.event.IHandler;
import com.jlu.chengjie.zhihu.fragment.IScrollToHead;
import com.jlu.chengjie.zhihu.model.IDisplayItem;
import com.jlu.chengjie.zhihu.util.TaskRunner;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseRecycleFragment extends Fragment implements IScrollToHead, IHandler {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.refresh)
    RefreshLayout refreshLayout;

    private static final int REFRESH_STOP = 0;
    private static final int SCROLL_VERTICALLY_UP = -1;

    private List<IDisplayItem> list = new ArrayList<>();

    private BaseRecyclerViewAdapter adapter;

    private OnRefreshListener refreshListener = refreshLayout ->
            TaskRunner.execute(() -> {
                onRefresh(list, adapter);
                adapter.notifyDataChanged(false);
                refreshLayout.finishRefresh();
            });

    private OnLoadMoreListener loadMoreListener = refreshLayout ->
            TaskRunner.execute(() -> {
                onLoadMore(list, adapter);
                adapter.notifyDataChanged(true);
                refreshLayout.finishLoadMore();
            });


    protected abstract void onListInit(List<IDisplayItem> list);

    protected abstract void onLoadMore(List<IDisplayItem> list, BaseRecyclerViewAdapter adapter);

    protected abstract void onRefresh(List<IDisplayItem> list, BaseRecyclerViewAdapter adapter);

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);

        onListInit(list);
        adapter = new BaseRecyclerViewAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(refreshListener);
        refreshLayout.setOnLoadMoreListener(loadMoreListener);
        return view;
    }

    @Override
    public void scrollToHead() {
        if (refreshLayout.getState() == RefreshState.Loading) return;
        if (recyclerView.getScrollState() != REFRESH_STOP) return;
        if (recyclerView.canScrollVertically(SCROLL_VERTICALLY_UP))
            recyclerView.scrollToPosition(0);
        else refreshLayout.autoRefresh();
    }
}
