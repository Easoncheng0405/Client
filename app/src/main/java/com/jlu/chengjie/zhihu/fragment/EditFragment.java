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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.activity.BrowserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditFragment extends Fragment {

    @BindView(R.id.edit_text)
    EditText editText;

    public static String contentHolder = "# Editor.md\n" +
            "\n" +
            "![](https://pandao.github.io/editor.md/images/logos/editormd-logo-180x180.png)\n" +
            "\n" +
            "![](https://img.shields.io/github/stars/pandao/editor.md.svg) ![](https://img.shields.io/github/forks/pandao/editor.md.svg) ![](https://img.shields.io/github/tag/pandao/editor.md.svg) ![](https://img.shields.io/github/release/pandao/editor.md.svg) ![](https://img.shields.io/github/issues/pandao/editor.md.svg) ![](https://img.shields.io/bower/v/editor.md.svg)\n" +
            "\n" +
            "**目录 (Table of Contents)**\n" +
            "\n" +
            "[TOCM]\n" +
            "\n" +
            "[TOC]\n" +
            "\n" +
            "# Heading 1\n" +
            "## Heading 2\n" +
            "### Heading 3\n" +
            "#### Heading 4\n" +
            "##### Heading 5\n" +
            "###### Heading 6\n" +
            "# Heading 1 link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "## Heading 2 link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "### Heading 3 link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "#### Heading 4 link [Heading link](https://github.com/pandao/editor.md \"Heading link\") Heading link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "##### Heading 5 link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "###### Heading 6 link [Heading link](https://github.com/pandao/editor.md \"Heading link\")\n" +
            "\n" +
            "#### 标题（用底线的形式）Heading (underline)\n" +
            "\n" +
            "This is an H1\n" +
            "=============\n" +
            "\n" +
            "This is an H2\n" +
            "-------------\n" +
            "\n" +
            "### 字符效果和横线等\n" +
            "                \n" +
            "----\n" +
            "\n" +
            "~~删除线~~ <s>删除线（开启识别HTML标签时）</s>\n" +
            "*斜体字*      _斜体字_\n" +
            "**粗体**  __粗体__\n" +
            "***粗斜体*** ___粗斜体___\n" +
            "\n" +
            "上标：X<sub>2</sub>，下标：O<sup>2</sup>\n" +
            "\n" +
            "**缩写(同HTML的abbr标签)**\n" +
            "\n" +
            "> 即更长的单词或短语的缩写形式，前提是开启识别HTML标签时，已默认开启\n" +
            "\n" +
            "The <abbr title=\"Hyper Text Markup Language\">HTML</abbr> specification is maintained by the <abbr title=\"World Wide Web Consortium\">W3C</abbr>.\n" +
            "\n" +
            "### 引用 Blockquotes\n" +
            "\n" +
            "> 引用文本 Blockquotes\n" +
            "\n" +
            "引用的行内混合 Blockquotes\n" +
            "                    \n" +
            "> 引用：如果想要插入空白换行`即<br />标签`，在插入处先键入两个以上的空格然后回车即可，[普通链接](http://localhost/)。\n" +
            "\n" +
            "### 锚点与链接 Links\n" +
            "\n" +
            "[普通链接](http://localhost/)\n" +
            "\n" +
            "[普通链接带标题](http://localhost/ \"普通链接带标题\")\n" +
            "\n" +
            "直接链接：<https://github.com>\n" +
            "\n" +
            "[锚点链接][anchor-id] \n" +
            "\n" +
            "[anchor-id]: http://www.this-anchor-link.com/\n" +
            "\n" +
            "GFM a-tail link @pandao\n" +
            "\n" +
            "> @pandao\n" +
            "\n" +
            "### 多语言代码高亮 Codes\n" +
            "\n" +
            "#### 行内代码 Inline code\n" +
            "\n" +
            "执行命令：`npm install marked`\n" +
            "\n" +
            "#### 缩进风格\n" +
            "\n" +
            "即缩进四个空格，也做为实现类似`<pre>`预格式化文本(Preformatted Text)的功能。\n" +
            "\n" +
            "    <?php\n" +
            "        echo \"Hello world!\";\n" +
            "    ?>\n" +
            "    \n" +
            "预格式化文本：\n" +
            "\n" +
            "    | First Header  | Second Header |\n" +
            "    | ------------- | ------------- |\n" +
            "    | Content Cell  | Content Cell  |\n" +
            "    | Content Cell  | Content Cell  |\n" +
            "\n" +
            "#### JS代码　\n" +
            "\n" +
            "```javascript\n" +
            "function test(){\n" +
            "\tconsole.log(\"Hello world!\");\n" +
            "}\n" +
            " \n" +
            "(function(){\n" +
            "    var box = function(){\n" +
            "        return box.fn.init();\n" +
            "    };\n" +
            "\n" +
            "    box.prototype = box.fn = {\n" +
            "        init : function(){\n" +
            "            console.log('box.init()');\n" +
            "\n" +
            "\t\t\treturn this;\n" +
            "        },\n" +
            "\n" +
            "\t\tadd : function(str){\n" +
            "\t\t\talert(\"add\", str);\n" +
            "\n" +
            "\t\t\treturn this;\n" +
            "\t\t},\n" +
            "\n" +
            "\t\tremove : function(str){\n" +
            "\t\t\talert(\"remove\", str);\n" +
            "\n" +
            "\t\t\treturn this;\n" +
            "\t\t}\n" +
            "    };\n" +
            "    \n" +
            "    box.fn.init.prototype = box.fn;\n" +
            "    \n" +
            "    window.box =box;\n" +
            "})();\n" +
            "\n" +
            "var testBox = box();\n" +
            "testBox.add(\"jQuery\").remove(\"jQuery\");\n" +
            "```\n" +
            "\n" +
            "#### HTML代码 HTML codes\n" +
            "\n" +
            "```html\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <mate charest=\"utf-8\" />\n" +
            "        <title>Hello world!</title>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <h1>Hello world!</h1>\n" +
            "    </body>\n" +
            "</html>\n" +
            "```\n" +
            "\n" +
            "### 图片 Images\n" +
            "\n" +
            "Image:\n" +
            "\n" +
            "![](https://pandao.github.io/editor.md/examples/images/4.jpg)\n" +
            "\n" +
            "> Follow your heart.\n" +
            "\n" +
            "![](https://pandao.github.io/editor.md/examples/images/8.jpg)\n" +
            "\n" +
            "> 图为：厦门白城沙滩\n" +
            "\n" +
            "图片加链接 (Image + Link)：\n" +
            "\n" +
            "[![](https://pandao.github.io/editor.md/examples/images/7.jpg)](https://pandao.github.io/editor.md/examples/images/7.jpg \"李健首张专辑《似水流年》封面\")\n" +
            "\n" +
            "> 图为：李健首张专辑《似水流年》封面\n" +
            "                \n" +
            "----\n" +
            "\n" +
            "### 列表 Lists\n" +
            "\n" +
            "#### 无序列表（减号）Unordered Lists (-)\n" +
            "                \n" +
            "- 列表一\n" +
            "- 列表二\n" +
            "- 列表三\n" +
            "     \n" +
            "#### 无序列表（星号）Unordered Lists (*)\n" +
            "\n" +
            "* 列表一\n" +
            "* 列表二\n" +
            "* 列表三\n" +
            "\n" +
            "#### 无序列表（加号和嵌套）Unordered Lists (+)\n" +
            "                \n" +
            "+ 列表一\n" +
            "+ 列表二\n" +
            "    + 列表二-1\n" +
            "    + 列表二-2\n" +
            "    + 列表二-3\n" +
            "+ 列表三\n" +
            "    * 列表一\n" +
            "    * 列表二\n" +
            "    * 列表三\n" +
            "\n" +
            "#### 有序列表 Ordered Lists (-)\n" +
            "                \n" +
            "1. 第一行\n" +
            "2. 第二行\n" +
            "3. 第三行\n" +
            "\n" +
            "#### GFM task list\n" +
            "\n" +
            "- [x] GFM task list 1\n" +
            "- [x] GFM task list 2\n" +
            "- [ ] GFM task list 3\n" +
            "    - [ ] GFM task list 3-1\n" +
            "    - [ ] GFM task list 3-2\n" +
            "    - [ ] GFM task list 3-3\n" +
            "- [ ] GFM task list 4\n" +
            "    - [ ] GFM task list 4-1\n" +
            "    - [ ] GFM task list 4-2";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        editText.setText(contentHolder);
    }

    @OnClick(R.id.submit)
    void submit() {
        contentHolder = editText.getText().toString();
        startActivity(new Intent(getContext(), BrowserActivity.class));
    }
}
