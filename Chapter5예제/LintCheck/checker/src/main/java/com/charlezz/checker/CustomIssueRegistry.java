package com.charlezz.checker;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

public class CustomIssueRegistry extends IssueRegistry {

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(
                SetContentViewDetector.ISSUE
        );
    }
}
