package com.example.redoassignment7;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    private String username;
    private String password;
    private Account checkingAccount;
    private Account savingsAccount;

    public User(String username, String password, Account checkingAccount, Account savingsAccount) {
        this.username = username;
        this.password = password;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        checkingAccount = in.readParcelable(Account.class.getClassLoader());
        savingsAccount = in.readParcelable(Account.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Account getCheckingAccount() {
        return checkingAccount;
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeParcelable(checkingAccount, i);
        parcel.writeParcelable(savingsAccount, i);
    }
}
