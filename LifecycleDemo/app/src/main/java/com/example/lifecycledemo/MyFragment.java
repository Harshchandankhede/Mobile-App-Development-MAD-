package com.example.lifecycledemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("FragmentLifecycle", "onAttach");
        Toast.makeText(context, "Fragment onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentLifecycle", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d("FragmentLifecycle", "onCreateView");
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("FragmentLifecycle", "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("FragmentLifecycle", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentLifecycle", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("FragmentLifecycle", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("FragmentLifecycle", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentLifecycle", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentLifecycle", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("FragmentLifecycle", "onDetach");
    }
}
