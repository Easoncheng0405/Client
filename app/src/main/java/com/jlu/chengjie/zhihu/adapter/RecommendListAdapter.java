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
import android.widget.TextView;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.ViewHolder> {

    private Context context;

    private List<RecommendQuestion> questions;

    public RecommendListAdapter(Context context, List<RecommendQuestion> questions) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_question, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(questions.get(i).title);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
