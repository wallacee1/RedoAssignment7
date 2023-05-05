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

public class DepositFragment extends Fragment {

    private TextView balanceDisplay;
    private TextView pendingBalanceDisplay;
    private EditText depositAmount;
    private Button depositSubmit;
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
        return inflater.inflate(R.layout.fragment_deposit2, container, false);
    }


    public static DepositFragment newInstance(User user) {
        DepositFragment fragment = new DepositFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup switchRadioGroup = view.findViewById(R.id.switchRadioGroup);
        balanceDisplay = view.findViewById(R.id.balanceDisplay);
        pendingBalanceDisplay = view.findViewById(R.id.pendingBalanceDisplay);
        depositAmount = view.findViewById(R.id.depositAmount);
        depositSubmit = view.findViewById(R.id.depositSubmit);

        // Display the initial balance
        balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));

        switchRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.checkingSwitch) {
                balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));
            } else if (checkedId == R.id.savingsSwitch) {
                balanceDisplay.setText(String.format("%.2f", user.getSavingsAccount().getBalance()));
            }
        });

        depositSubmit.setOnClickListener(v -> {
            // Get the deposit amount entered by the user
            String depositAmountText = depositAmount.getText().toString();
            if (!depositAmountText.isEmpty()) {
                double depositAmountValue = Double.parseDouble(depositAmountText);

                // Perform the desired action for depositing the amount
                int checkedId = switchRadioGroup.getCheckedRadioButtonId();
                if (checkedId == R.id.checkingSwitch) {
                    user.getCheckingAccount().deposit(depositAmountValue);
                    double newCheckingBalance = user.getCheckingAccount().getBalance();
                    pendingBalanceDisplay.setText(String.format("Pending: %.2f", newCheckingBalance));
                } else if (checkedId == R.id.savingsSwitch) {
                    user.getSavingsAccount().deposit(depositAmountValue);
                    double newSavingsBalance = user.getSavingsAccount().getBalance();
                    pendingBalanceDisplay.setText(String.format("Pending: %.2f", newSavingsBalance));
                }
            }
        });
    }
}