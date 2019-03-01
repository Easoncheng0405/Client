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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.ViewHolder> {

    private Context context;

    private List<RecommendQuestion> questions;

    private RequestManager manager;

    public RecommendListAdapter(Context context, List<RecommendQuestion> questions) {
        this.questions = questions;
        this.context = context;
        this.manager = Glide.with(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_question, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecommendQuestion question = questions.get(i);
        viewHolder.title.setText(question.title);
        viewHolder.authorName.setText(question.authorName);
        viewHolder.signature.setText(question.signature);
        viewHolder.content.setText(question.content);
        viewHolder.questionInfo.setText(question.questionInfo);
        manager.load(question.imageUrl)
                .error(R.drawable.avatar)
                .into(viewHolder.avatar);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.avatar)
        ImageView avatar;

        @BindView(R.id.author_name)
        TextView authorName;

        @BindView(R.id.signature)
        TextView signature;

        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.info)
        TextView questionInfo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.icon_more)
        void onClickQuestionMore() {
            Toast.makeText(context, "icon_more...", Toast.LENGTH_LONG).show();
        }
    }
}
