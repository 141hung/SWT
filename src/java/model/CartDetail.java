/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamd
 */
public class CartDetail {

    private int cartid, productid, quantity; // Change quanlity to quantity
    private double price;

    public CartDetail() {
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() { // Change getQuanlity to getQuantity
        return quantity;
    }

    public void setQuantity(int quantity) { // Change setQuanlity to setQuantity
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CartDetail(int cartid, int productid, int quantity, double price) { // Change quanlity to quantity
        this.cartid = cartid;
        this.productid = productid;
        this.quantity = quantity; // Change quanlity to quantity
        this.price = price;
    }

}

