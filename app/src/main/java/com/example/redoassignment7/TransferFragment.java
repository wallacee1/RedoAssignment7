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

public class TransferFragment extends Fragment {

    private TextView balanceDisplay;
    private EditText transferAmount;
    private Button transferSubmit;
    private User user;

    public static TransferFragment newInstance(User user) {
        TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        args.putParcelable("user", user);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_transfer2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup switchRadioGroup = view.findViewById(R.id.switchRadioGroup);
        balanceDisplay = view.findViewById(R.id.balanceDisplay);
        transferAmount = view.findViewById(R.id.transferAmount);
        transferSubmit = view.findViewById(R.id.transferSubmit);

        // Display the initial balance
        balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));

        switchRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.checkingSwitch) {
                    // Checking switch is selected
                    balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));
                } else if (checkedId == R.id.savingsSwitch) {
                    // Savings switch is selected
                    balanceDisplay.setText(String.format("%.2f", user.getSavingsAccount().getBalance()));
                }
            }
        });

        transferSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the transfer amount entered by the user
                String transferAmountText = transferAmount.getText().toString();
                if (!transferAmountText.isEmpty()) {
                    double transferAmountValue = Double.parseDouble(transferAmountText);

                    // Perform the desired action for transferring the amount
                    int checkedId = switchRadioGroup.getCheckedRadioButtonId();
                    if (checkedId == R.id.checkingSwitch) {
                        // Transfer to checking account
                        user.getCheckingAccount().deposit(transferAmountValue);
                        user.getSavingsAccount().withdraw(transferAmountValue);
                        balanceDisplay.setText(String.format("%.2f", user.getCheckingAccount().getBalance()));
                    } else if (checkedId == R.id.savingsSwitch) {
                        // Transfer to savings account
                        user.getSavingsAccount().deposit(transferAmountValue);
                        user.getCheckingAccount().withdraw(transferAmountValue);
                        balanceDisplay.setText(String.format("%.2f", user.getSavingsAccount().getBalance()));
                    }
                }
            }
        });
    }
}
