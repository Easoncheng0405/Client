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
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.adapter.IScrollToHead;
import com.jlu.chengjie.zhihu.adapter.RecommendListAdapter;
import com.jlu.chengjie.zhihu.modeal.FooterLoadMore;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;
import com.jlu.chengjie.zhihu.util.TaskRunner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Recommend extends Fragment implements IScrollToHead, RecommendListAdapter.ScrollBottomListener {

    @BindView(R.id.list)
    RecyclerView recommendList;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private static final int REFRESH_STOP = 0;
    private static final int SCROLL_VERTICALLY_UP = -1;
    private boolean isLoadingMore = false;
    private List<IDisplayItem> list = new ArrayList<>();
    private RecommendListAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());

    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        Toast.makeText(getContext(), "refreshing....", Toast.LENGTH_LONG).show();
        refresh.setRefreshing(false);
    };

    private Runnable loadMore = () -> {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<IDisplayItem> more = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecommendQuestion question = new RecommendQuestion();
            question.title = "现在国内大公司主要用C#还是JAVA?";
            question.authorName = "Ben Lampson";
            question.signature = "已认证的官方账号";
            question.content = "两种语言其实没有太大差别.都有1L..就算有差距也就那样...我自己是" +
                    ".NET.不过大部分猎头找我都是JAVA.因为J...";
            question.questionInfo = "10赞同 · 5 评论 · 1 收藏";
            more.add(question);
        }
        loadMore(more);
    };

    private Runnable noMore = () -> {
        FooterLoadMore footer = (FooterLoadMore) list.get(list.size() - 1);
        footer.text = getString(R.string.list_footer_no_more);
        adapter.notifyItemChanged(list.size() - 1);
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        refresh.setColorSchemeResources(R.color.color_normal_button);
        initRecommendList();
        refresh.setOnRefreshListener(refreshListener);
        return view;
    }

    private void initRecommendList() {
        for (int i = 0; i < 10; i++) {
            RecommendQuestion question = new RecommendQuestion();
            question.title = "现在国内大公司主要用C#还是JAVA?";
            question.authorName = "Ben Lampson";
            question.signature = "已认证的官方账号";
            question.content = "两种语言其实没有太大差别.都有1L..就算有差距也就那样...我自己是" +
                    ".NET.不过大部分猎头找我都是JAVA.因为J...";
            question.questionInfo = "10赞同 · 5 评论 · 1 收藏";
            list.add(question);
        }
        addFooter();
        adapter = new RecommendListAdapter(getContext(), list, this);
        recommendList.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendList.setAdapter(adapter);
    }

    @Override
    public void scrollToHead() {
        if (refresh.isRefreshing()) return;
        if (recommendList.getScrollState() != REFRESH_STOP) return;
        if (recommendList.canScrollVertically(SCROLL_VERTICALLY_UP))
            recommendList.scrollToPosition(0);
        else refresh.setRefreshing(true);
    }

    private void addFooter() {
        FooterLoadMore more = new FooterLoadMore();
        more.text = getString(R.string.list_footer_load_more);
        list.add(more);
    }

    @Override
    public void onScrollBottom() {
        if (isLoadingMore) return;
        if (list.size() > 50) {
            handler.post(noMore);
            return;
        }
        isLoadingMore = true;
        TaskRunner.execute(loadMore);
    }

    private void loadMore(List<IDisplayItem> more) {
        handler.post(() -> {
            list.remove(list.size() - 1);
            adapter.notifyItemRemoved(list.size() - 1);
            for (IDisplayItem item : more) {
                list.add(item);
                adapter.notifyItemInserted(list.size() - 1);
            }
            addFooter();
            adapter.notifyItemInserted(list.size() - 1);
            isLoadingMore = false;
        });
    }
}
