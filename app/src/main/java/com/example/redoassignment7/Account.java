package com.example.redoassignment7;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable{
    private String accountNumber;
    private double balance;
    private boolean isPendingDeposit;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isPendingDeposit = false;
    }

    protected Account(Parcel in) {
        accountNumber = in.readString();
        balance = in.readDouble();
        isPendingDeposit = in.readByte() != 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasPendingDeposit() {
        return isPendingDeposit;
    }

    public void setPendingDeposit(boolean pending) {
        this.isPendingDeposit = pending;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(accountNumber);
        parcel.writeDouble(balance);
        parcel.writeByte((byte) (isPendingDeposit ? 1 : 0));
    }
}

