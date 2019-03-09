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

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.event.Event;
import com.jlu.chengjie.zhihu.event.EventBus;
import com.jlu.chengjie.zhihu.model.FollowDynamics;
import com.jlu.chengjie.zhihu.model.FollowHeader;
import com.jlu.chengjie.zhihu.model.IDisplayItem;
import com.jlu.chengjie.zhihu.util.ToastHelper;
import com.jlu.chengjie.zhihu.util.ZLog;

import java.util.List;

public class FollowFragment extends BaseRecycleFragment {

    private static final String TAG = "FollowFragment";

    public FollowFragment() {
        EventBus.getInstance().registered(this);
    }

    @Override
    protected void onListInit(List<IDisplayItem> list) {
        init(list);
    }

    @Override
    protected void onLoadMore(List<IDisplayItem> list) {
        ZLog.d(TAG, "start to load more data.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    protected void onRefresh(List<IDisplayItem> list) {
        ZLog.d(TAG, "start to refresh data.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.clear();
        init(list);
    }

    private void init(List<IDisplayItem> list) {
        FollowHeader header = new FollowHeader();
        header.avatarUrls[0] = "http://img2.imgtn.bdimg.com/it/u=2060761043,284284863&fm=26&gp=0.jpg";
        header.avatarUrls[1] = "http://img2.imgtn.bdimg.com/it/u=3135339935,1064367009&fm=26&gp=0.jpg";
        header.avatarUrls[2] = "http://img1.imgtn.bdimg.com/it/u=2161523157,1298941018&fm=26&gp=0.jpg";
        header.avatarUrls[3] = "http://img2.imgtn.bdimg.com/it/u=594281965,894601681&fm=26&gp=0.jpg";
        list.add(header);
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
    protected int getLayoutId() {
        return R.layout.fragment_base_recycle;
    }

    @Override
    public boolean handleMsg(int what, String msg, Object o) {
        switch (what) {
            case Event.Click.FOLLOW_PEOPLE_DYNAMICS:
                FollowDynamics dynamics = (FollowDynamics) o;
                ToastHelper.toastSafe(dynamics.title);
                break;
            case Event.Click.FOLLOW_DISCOVER_MORE:
                ToastHelper.toastSafe("FOLLOW_DISCOVER_MORE");
                break;
            default:
                return false;
        }
        ZLog.d(TAG, "handle message " + msg);
        return true;
    }
}
