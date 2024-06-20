/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phamd
 */
public class Order {

    private List<Item> items;

    public Order() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuanlityById(int id) {
        Item item = getItemById(id);
        if (item != null) {
            return item.getQuanlity();
        }
        return 0; // Hoặc một giá trị mặc định khác phù hợp
    }

    public Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getId() == id) {
                return i;
            }
        }
        return null;
    }

    //thêm sản phẩm
    public void addItem(Item t) {
        //sản phẩm đã có
        if (getItemById(t.getProduct().getId()) != null) {
            Item m = getItemById(t.getProduct().getId());
            m.setQuanlity(m.getQuanlity() + t.getQuanlity());
        } else {
            //chưa có thì add vào
            items.add(t);
        }
    }

    //Xóa sản phẩm
    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    //hàm trả về toàn bộ tiền của giỏi hàng 
    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuanlity() * i.getPrice());
        }
        return t;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public Order(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quanlity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t = new Item(p, quanlity, p.getPrice() * 2);
                    addItem(t);
                }
            }
        } catch (Exception e) {
        }

    }
}
