package com.example.redoassignment7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class WithdrawFragment extends Fragment {

    private TextView balanceDisplay;
    private EditText withdrawAmount;
    private Button withdrawSubmit;
    private User user;
    private static final String ARG_USER = "user";


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
        return inflater.inflate(R.layout.fragment_withdraw2, container, false);
    }


    public static WithdrawFragment newInstance(User user) {
        WithdrawFragment fragment = new WithdrawFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RadioGroup switchRadioGroup = view.findViewById(R.id.switchRadioGroup);
        balanceDisplay = view.findViewById(R.id.balanceDisplay);
        withdrawAmount = view.findViewById(R.id.withdrawAmount);
        withdrawSubmit = view.findViewById(R.id.withdrawSubmit);

        // Display the initial balance
        balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));

        switchRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.checkingSwitch) {
                balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));
            } else if (checkedId == R.id.savingsSwitch) {
                balanceDisplay.setText(String.format("%.2f", user.getSavingsAccount().getBalance()));
            }
        });

        withdrawSubmit.setOnClickListener(v -> {
            // Get the withdrawal amount entered by the user
            String withdrawAmountText = withdrawAmount.getText().toString();
            if (!withdrawAmountText.isEmpty()) {
                double withdrawAmountValue = Double.parseDouble(withdrawAmountText);

                // Perform the desired action for withdrawing the amount
                int checkedId = switchRadioGroup.getCheckedRadioButtonId();
                if (checkedId == R.id.checkingSwitch) {
                    user.getCheckingAccount().withdraw(withdrawAmountValue);
                    balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));
                } else if (checkedId == R.id.savingsSwitch) {
                    user.getSavingsAccount().withdraw(withdrawAmountValue);
                    balanceDisplay.setText(String.format("%.2f", user.getSavingsAccount().getBalance()));
                }
            }
        });
    }
}