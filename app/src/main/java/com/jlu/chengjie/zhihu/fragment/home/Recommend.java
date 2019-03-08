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

import android.widget.Toast;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.adapter.BaseRecyclerViewAdapter;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;

import java.util.List;

public class Recommend extends BaseRecycleFragment {

    @Override
    protected void onListInit(List<IDisplayItem> list) {
        for (int i = 0; i < 10; i++) {
            RecommendQuestion question = new RecommendQuestion();
            question.title = "现在国内大公司主要用C?";
            question.authorName = "Ben Lampson";
            question.signature = "已认证的官方账号";
            question.content = "两种语言其实没有太大差别.都有1L..就算有差距也就那样...我自己是" +
                    ".NET.不过大部分猎头找我都是JAVA.因为J...";
            question.itemInfo = "10赞同 · 5 评论 · 1 收藏";
            list.add(question);
        }
    }

    @Override
    protected void onLoadMore(List<IDisplayItem> list, BaseRecyclerViewAdapter adapter) {
        Toast.makeText(getContext(), "loading more....", Toast.LENGTH_LONG).show();
        refreshLayout.finishLoadMore(2000, false, true);
        for (int i = 0; i < 10; i++) {
            RecommendQuestion question = new RecommendQuestion();
            question.title = "现在国内大公司主要用C#还是JAVA?";
            question.authorName = "Ben Lampson";
            question.signature = "已认证的官方账号";
            question.content = "两种语言其实没有太大差别.都有1L..就算有差距也就那样...我自己是" +
                    ".NET.不过大部分猎头找我都是JAVA.因为J...";
            question.itemInfo = "10赞同 · 5 评论 · 1 收藏";
            list.add(question);
        }
    }

    @Override
    protected void onRefresh(List<IDisplayItem> list, BaseRecyclerViewAdapter adapter) {
        Toast.makeText(getContext(), "refreshing....", Toast.LENGTH_LONG).show();
        refreshLayout.finishRefresh(2000, true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycle;
    }
}
