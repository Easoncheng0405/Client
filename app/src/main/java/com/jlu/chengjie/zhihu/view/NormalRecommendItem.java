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

package com.jlu.chengjie.zhihu.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;
import com.jlu.chengjie.zhihu.modeal.RecommendQuestion;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalRecommendItem extends LinearLayout implements IDisplay {

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

    public NormalRecommendItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBind(IDisplayItem item) {
        RecommendQuestion question = (RecommendQuestion) item;
        ButterKnife.bind(this);
        title.setText(question.title);
        authorName.setText(question.authorName);
        signature.setText(question.signature);
        content.setText(question.content);
        questionInfo.setText(question.questionInfo);
        Glide.with(getContext())
                .load(question.imageUrl)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(avatar);
    }
}
