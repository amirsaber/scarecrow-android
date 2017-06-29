package com.geekofwallstreet.scarecrow;

/**
 * Created by amirsaber on 6/29/17.
 */

public class Order {
    public String type;
    public String grain;
    public Double amount;
    public String uid;
    public String email;

    public Order(){
    }

    public Order(String type, String grain, Double amount, String uid, String email) {
        this.type = type;
        this.grain = grain;
        this.amount = amount;
        this.uid = uid;
        this.email = email;
    }

    public String toString(){
        return String.format("%s %s %f %s %s", this.type, this.grain, this.amount, this.uid, this.email);
    }
}
