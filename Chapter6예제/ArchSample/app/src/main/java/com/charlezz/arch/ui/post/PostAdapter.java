package com.charlezz.arch.ui.post;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.view.ViewGroup;

import com.charlezz.arch.BR;
import com.charlezz.arch.R;
import com.charlezz.arch.util.ViewBindingHolder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 게시글 목록을 위한 Adapter
 */
public class PostAdapter extends RecyclerView.Adapter<ViewBindingHolder> {

    //뷰홀더용 뷰모델 리스트 변수
    private final List<PostItem> items = new ArrayList<>();

    //생성자 인젝션
    @Inject
    public PostAdapter(){

    }

    //레이아웃 종류
    @Override
    public int getItemViewType(int position) {
        return R.layout.view_post;
    }

    //뷰홀더 생성
    @NonNull
    @Override
    public ViewBindingHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewBindingHolder(parent.getContext(), viewType);
    }

    //뷰홀더와 뷰모델을 바인딩 한다
    @Override
    public void onBindViewHolder(@NonNull ViewBindingHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, items.get(position));
        holder.getBinding().executePendingBindings();
    }

    //외부로부터 게시글목록을 받아서 UI를 갱신한다.
    public void setItems(List<PostItem> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    //게시글 목록수
    @Override
    public int getItemCount() {
        return items.size();
    }
}
