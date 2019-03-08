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
import com.jlu.chengjie.zhihu.modeal.FollowDynamics;
import com.jlu.chengjie.zhihu.modeal.IDisplayItem;

import java.util.List;

public class FollowFragment extends BaseRecycleFragment {

    @Override
    protected void onListInit(List<IDisplayItem> list) {
        for (int i = 0; i < 10; i++) {
            FollowDynamics dynamics = new FollowDynamics();
            dynamics.title = "做一个网页是先把大致布局搭好还是慢慢从头到尾搭?";
            dynamics.content = "陈龙: 先把大致布局搭好。以前公司招聘了一个做平面设计" +
                    "的女生做网页，她有美术基础，会画画。但是要我从HTML开始教她。开始做" +
                    "网页的时候，我说: 咋们先把页...";
            dynamics.authorName = "知乎刘看山";
            dynamics.metaInfo = "赞同了回答 · 4 小时前";
            dynamics.itemInfo = "94 赞同 · 245 评论 · 9 收藏";
            list.add(dynamics);
        }
    }

    @Override
    protected void onLoadMore(List<IDisplayItem> list, BaseRecyclerViewAdapter adapter) {
        refreshLayout.finishLoadMore(2000, false, true);
        for (int i = 0; i < 10; i++) {
            FollowDynamics dynamics = new FollowDynamics();
            dynamics.title = "做一个网页是先把大致布局搭好还是慢慢从头到尾搭?";
            dynamics.content = "陈龙: 先把大致布局搭好。以前公司招聘了一个做平面设计" +
                    "的女生做网页，她有美术基础，会画画。但是要我从HTML开始教她。开始做" +
                    "网页的时候，我说: 咋们先把页...";
            dynamics.authorName = "小米吴彦祖";
            dynamics.metaInfo = "赞同了回答 · 4 小时前";
            dynamics.itemInfo = "94 赞同 · 245 评论 · 9 收藏";
            list.add(dynamics);
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
