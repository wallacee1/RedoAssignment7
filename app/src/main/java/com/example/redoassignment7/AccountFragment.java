package com.example.redoassignment7;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountFragment extends Fragment {
    private static final String ARG_USER = "user";
    private User user;

    public static AccountFragment newInstance(User user) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account2, container, false);

        // Display the checking and savings account balances
        TextView checkingDisplay = view.findViewById(R.id.checkingDisplay);
        TextView savingsDisplay = view.findViewById(R.id.savingsDisplay);
        if (user != null) {
            checkingDisplay.setText(String.format("$%.2f", user.getCheckingAccount().getBalance()));
            savingsDisplay.setText(String.format("$%.2f", user.getSavingsAccount().getBalance()));
        } else {
            checkingDisplay.setText("N/A");
            savingsDisplay.setText("N/A");
        }


        return view;
    }
}
