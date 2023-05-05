package com.example.redoassignment7;

import androidx.appcompat.app.AppCompatActivity;
import com.example.redoassignment7.databinding.ActivityMainBinding;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra("user");

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.accounts:
                    replaceFragment(AccountFragment.newInstance(user));
                    break;
                case R.id.transfer:
                    replaceFragment(TransferFragment.newInstance(user));
                    break;
                case R.id.withdraw:
                    replaceFragment(WithdrawFragment.newInstance(user));
                    break;
                case R.id.deposit:
                    replaceFragment(DepositFragment.newInstance(user));
                    break;
            }
            return true;
        });

        User user = getIntent().getParcelableExtra("user");
        Fragment accountFragment = AccountFragment.newInstance(user);
        replaceFragment(AccountFragment.newInstance(user));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
