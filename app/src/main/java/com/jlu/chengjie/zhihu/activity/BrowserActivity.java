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

package com.jlu.chengjie.zhihu.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.fragment.EditFragment;
import com.jlu.chengjie.zhihu.util.TaskRunner;
import com.jlu.chengjie.zhihu.util.ZLog;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrowserActivity extends AppCompatActivity {

    private static final String TAG = "BrowserActivity";

    private static final String HTML_HEADER = "<head>" +
            "    <link rel=\"stylesheet\" href=\"file:///android_asset/css/bootstrap.min.css\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
            "<style>img{max-width:100% !important; width:auto; height:auto;}</style>" +
            "</head>" +
            "<div class=\"container\">";

    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.title)
    TextView title;

    private Handler handler = new Handler(Looper.getMainLooper());

    private Runnable previewMarkDown = () -> {
        try {
            String content = new Markdown4jProcessor().process(EditFragment.contentHolder) + "</div>";
            handler.post(() -> webView.loadDataWithBaseURL("file:///android_asset/", HTML_HEADER + content, "text/html", "UTF-8", null));
        } catch (IOException e) {
            ZLog.e(TAG, "preview MarkDown exception", e);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        title.setText("预览");
        TaskRunner.execute(previewMarkDown);
    }

    @OnClick(R.id.back)
    void back() {
        onBackPressed();
    }
}
