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

package com.jlu.chengjie.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.adapter.FragmentAdapter;
import com.jlu.chengjie.zhihu.fragment.home.FollowFragment;
import com.jlu.chengjie.zhihu.fragment.home.Recommend;
import com.jlu.chengjie.zhihu.util.ZLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements IScrollToHead {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private final int[] title = new int[]{R.string.tab_top_follow, R.string.tab_top_recommend
            , R.string.tab_top_billboard, R.string.tab_top_article};

    private Fragment currentFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initTabLayout();
        return view;
    }

    private List<Fragment> fragments = new ArrayList<Fragment>() {
        {
            add(new FollowFragment());
            add(new Recommend());
            add(new MessageFragment());
            add(new MyFragment());
        }
    };

    private void initTabLayout() {
        viewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        for (int i = 1; i <= tabLayout.getTabCount(); i++) {
            viewPager.setCurrentItem(i % tabLayout.getTabCount());
        }
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            ZLog.d(TAG, tab.getText() + " onTabSelected");
            tab.setText(title[position]);
            currentFragment = fragments.get(position);

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            ZLog.d(TAG, tab.getText() + " onTabUnselected");
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            ZLog.d(TAG, tab.getText() + " onTabReselected");
            scrollToHead();
        }
    };

    @Override
    public void scrollToHead() {
        if (currentFragment instanceof IScrollToHead) {
            ((IScrollToHead) currentFragment).scrollToHead();
        }
    }
}
