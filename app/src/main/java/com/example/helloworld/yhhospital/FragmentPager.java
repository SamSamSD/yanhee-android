package com.example.helloworld.yhhospital;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPager extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view;
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");

        view = inflater.inflate(R.layout.page_fragment, container, false);
        TextView textView = view.findViewById(R.id.pageNumber);
        textView.setText(Integer.toString(pageNumber));
        return view;
    }
}
