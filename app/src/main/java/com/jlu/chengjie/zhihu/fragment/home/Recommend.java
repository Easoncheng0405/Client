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
import com.jlu.chengjie.zhihu.adapter.RecommendListAdapter;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Recommend extends Fragment {

    @BindView(R.id.list)
    RecyclerView recommendList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initRecommendList();
        return view;
    }

    private void initRecommendList() {
        List<RecommendQuestion> list = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            RecommendQuestion question = new RecommendQuestion();
            question.title = "现在国内大公司主要用C#还是JAVA?";
            question.authorName = "Ben Lampson";
            question.signature = "已认证的官方账号";
            question.content = "两种语言其实没有太大差别.都有1L..就算有差距也就那样...我自己是" +
                    ".NET.不过大部分猎头找我都是JAVA.因为J...";
            question.questionInfo = "10赞同 · 5 评论 · 1 收藏";
            list.add(question);
        }

        RecommendListAdapter adapter = new RecommendListAdapter(getContext(), list);
        recommendList.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendList.setAdapter(adapter);
    }
}
