package com.charlezz.arch.ui.detail;

import javax.inject.Inject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlezz.arch.databinding.FragmentPostDetailBinding;
import com.charlezz.arch.di.AppViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Lazy;
import dagger.android.support.DaggerFragment;

/**
 * 게시글 상세 화면
 */
public class PostDetailFragment extends DaggerFragment {

    @Inject
    FragmentPostDetailBinding binding;
    @Inject
    PostDetailAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    Lazy<NavController> navController;

    PostDetailViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostDetailViewModel.class);
        if (savedInstanceState == null) {
            PostDetailFragmentArgs args = PostDetailFragmentArgs.fromBundle(getArguments());
            viewModel.load(args.getPost());
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
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.setViewModel(viewModel);
        viewModel.getLiveItems().observe(getViewLifecycleOwner(), items -> adapter.setItems(items));
        viewModel.getUserClickEvent().observe(getViewLifecycleOwner(), userId -> {
            navController.get().navigate(
                    PostDetailFragmentDirections.actionPostDetailFragmentToUserFragment(userId)
            );
        });
    }
}
