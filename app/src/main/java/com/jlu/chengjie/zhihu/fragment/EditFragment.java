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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jlu.chengjie.zhihu.R;
import com.jlu.chengjie.zhihu.activity.BrowserActivity;
import com.jlu.chengjie.zhihu.util.ZLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.qinc.edit.PerformEdit;

@SuppressWarnings("ConstantConditions")
public class EditFragment extends Fragment {

    private static final String TAG = "EditFragment";

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.title_level)
    ImageView titleLevel;

    @BindView(R.id.code_style)
    ImageView codeStyle;

    @BindView(R.id.text_style)
    ImageView textStyle;

    @BindView(R.id.list_style)
    ImageView listStyle;

    PerformEdit editHistory;

    public static String contentHolder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        editHistory = new PerformEdit(editText);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader
                    (getActivity().getAssets().open("Doc.md"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) builder.append(line).append("\n");
            contentHolder = builder.toString();
            editText.setText(contentHolder);
        } catch (IOException e) {
            ZLog.e(TAG, "read markdown doc exception", e);
        }
    }

    @OnClick(R.id.preview)
    void submit() {
        contentHolder = editText.getText().toString();
        startActivity(new Intent(getContext(), BrowserActivity.class));
    }

    @OnClick(R.id.title_level)
    void insertTile() {
        int[] id = new int[]{R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6};
        PopupMenu menu = new PopupMenu(getContext(), titleLevel);
        menu.getMenuInflater().inflate(R.menu.editor_title_level, menu.getMenu());
        menu.setOnMenuItemClickListener(item -> {
            editText.append("\n");
            for (int i : id) {
                editText.append("#");
                if (item.getItemId() == i)
                    break;
            }
            editText.append(" ");
            editText.setSelection(editText.getText().length());
            return true;
        });
        menu.show();
    }

    @OnClick(R.id.code_style)
    void insertCode() {
        PopupMenu menu = new PopupMenu(getContext(), codeStyle);
        menu.getMenuInflater().inflate(R.menu.editor_code_style, menu.getMenu());
        menu.setOnMenuItemClickListener(item -> {
            editText.append("\n");
            switch (item.getItemId()) {
                case R.id.line:
                    editText.append("``");
                    editText.setSelection(editText.getText().length() - 1);
                    break;
                case R.id.block:
                    editText.append("```\n\n```");
                    editText.setSelection(editText.getText().length() - 4);
                    break;
            }
            return true;
        });
        menu.show();
    }

    @OnClick(R.id.text_style)
    void insertText() {
        PopupMenu menu = new PopupMenu(getContext(), textStyle);
        menu.getMenuInflater().inflate(R.menu.editor_text_style, menu.getMenu());
        menu.setOnMenuItemClickListener(item -> {
            editText.append("\n");
            switch (item.getItemId()) {
                case R.id.bold:
                    editText.append("****");
                    editText.setSelection(editText.getText().length() - 2);
                    break;
                case R.id.italic:
                    editText.append("**");
                    editText.setSelection(editText.getText().length() - 1);
                    break;
                case R.id.quote:
                    editText.append("> ");
                    editText.setSelection(editText.getText().length());
                    break;
            }
            return true;
        });
        menu.show();
    }

    @OnClick(R.id.image)
    void insertImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_insert_image_title);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input, (ViewGroup) getView(), false);
        EditText address = contentView.findViewById(R.id.title);
        EditText content = contentView.findViewById(R.id.content);
        address.setHint(R.string.dialog_insert_image_address_hint);
        content.setHint(R.string.dialog_insert_image_description_hint);

        String format = "![%s](%s)";
        builder.setView(contentView);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            editText.append("\n");
            editText.append(String.format(format, content.getText().toString(), address.getText().toString()));
            editText.setSelection(editText.getText().length());
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @OnClick(R.id.link)
    void insertLink() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_insert_link_title);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input, (ViewGroup) getView(), false);
        EditText address = contentView.findViewById(R.id.title);
        EditText content = contentView.findViewById(R.id.content);
        address.setHint(R.string.dialog_insert_link_address_hint);
        content.setHint(R.string.dialog_insert_link_description_hint);
        String format = "[%s](%s)";
        builder.setView(contentView);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            editText.append("\n");
            String description = content.getText().toString();
            if (TextUtils.isEmpty(description))
                description = address.getText().toString();
            editText.append(String.format(format, description, address.getText().toString()));
            editText.setSelection(editText.getText().length());
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @OnClick(R.id.list_style)
    void insertList() {
        PopupMenu menu = new PopupMenu(getContext(), listStyle);
        menu.getMenuInflater().inflate(R.menu.editor_list_style, menu.getMenu());
        menu.setOnMenuItemClickListener(item -> {
            editText.append("\n");
            switch (item.getItemId()) {
                case R.id.order:
                    editText.append("* ");
                    break;
                case R.id.disorder:
                    editText.append("1. ");
                    break;
            }
            editText.setSelection(editText.getText().length());
            return true;
        });
        menu.show();
    }

    @OnClick(R.id.undo)
    void undo() {
        editHistory.undo();
    }

    @OnClick(R.id.redo)
    void redo() {
        editHistory.redo();
    }
}
