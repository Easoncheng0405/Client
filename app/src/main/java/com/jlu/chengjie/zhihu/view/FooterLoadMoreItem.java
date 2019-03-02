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
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.modeal.FooterLoadMore;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FooterLoadMoreItem extends RelativeLayout implements IDisplay {

    @BindView(R.id.text)
    TextView text;

    public FooterLoadMoreItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBind(IDisplayItem item) {
        FooterLoadMore more = (FooterLoadMore) item;
        ButterKnife.bind(this);
        text.setText(more.text);
    }
}
