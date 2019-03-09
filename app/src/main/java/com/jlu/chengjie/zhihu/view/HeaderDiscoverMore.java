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
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.event.Event;
import com.jlu.chengjie.zhihu.event.EventBus;
import com.jlu.chengjie.zhihu.model.FollowHeader;
import com.jlu.chengjie.zhihu.model.IDisplayItem;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeaderDiscoverMore extends RelativeLayout implements IDisplay {

    public HeaderDiscoverMore(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBind(IDisplayItem item) {
        FollowHeader header = (FollowHeader) item;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof CircleImageView) {
                Log.d("HeaderDiscoverMore", "" + (i % 4));
                CircleImageView imageView = (CircleImageView) view;
                Glide.with(getContext())
                        .load(header.avatarUrls[i % 4])
                        .placeholder(R.drawable.avatar)
                        .error(R.drawable.avatar)
                        .into(imageView);
            }
        }
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getInstance().handleMsg(Event.Click.FOLLOW_DISCOVER_MORE
                , null, "HeaderDiscoverMore Click.");
    }
}
