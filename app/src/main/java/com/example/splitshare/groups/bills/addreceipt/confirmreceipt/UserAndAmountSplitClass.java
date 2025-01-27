package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import com.example.splitshare.login.user.User;

public class UserAndAmountSplitClass {
    private User user;
    private Double amount;

    public UserAndAmountSplitClass() {
    }

    public UserAndAmountSplitClass(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
