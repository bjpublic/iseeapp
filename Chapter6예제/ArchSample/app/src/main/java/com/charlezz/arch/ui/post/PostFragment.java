package com.charlezz.arch.ui.post;

import javax.inject.Inject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlezz.arch.databinding.FragmentPostBinding;
import com.charlezz.arch.di.AppViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Lazy;
import dagger.android.support.DaggerFragment;

/**
 * 게시글 화면 구성하기
 * 멤버 인젝션을 위해 DaggerFragment 상속
 */
public class PostFragment extends DaggerFragment {

    /**
     * 오브젝트 그래프로부터 멤버 인젝션
     */
    @Inject
    FragmentPostBinding binding;
    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    PostAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    Lazy<NavController> navController;

    PostViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ViewModel 객체를 요청
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostViewModel.class);
        if (savedInstanceState == null) {
            // 데이터 요청, 프레그먼트가 재생성 되었을 때는 요청하지 않는다.
            viewModel.loadPosts();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Lifecycle Owner 등록
        binding.setLifecycleOwner(getViewLifecycleOwner());
        //RecyclerView Adapter 지정
        binding.recyclerView.setAdapter(adapter);
        //RecyclerView 레이아웃 매니저 지정
        binding.recyclerView.setLayoutManager(layoutManager);
        //바인딩 클래스에 ViewModel 연결
        binding.setViewModel(viewModel);
        //ViewModel이 가지고 있는 게시글 목록을 구독하여 Adapter에 반영
        viewModel.getLivePosts()
                .observe(getViewLifecycleOwner(), list -> adapter.setItems(list));
        //게시글이 클릭 되었을 때 다음 목적지로 이동
        viewModel.getPostClickEvent()
                .observe(getViewLifecycleOwner(), postItem ->
                        navController.get().navigate(PostFragmentDirections
                                .actionPostFragmentToPostDetailFragment(postItem.getPost())));
    }
}
