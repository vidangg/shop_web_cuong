/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {
    private List<CartItems> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<CartItems> items) {
        this.items = items;
    }
    
    public CartItems getItemById(int id){
        for (CartItems i : items ){
            if  ( i.getBook().getId() == id){
                return i;
            }            
        }
        return null;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }
    
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    
    public void addItem(CartItems i){
        if(getItemById(i.getBook().getId())!=null){           
            CartItems m = getItemById(i.getBook().getId());
            m.setQuantity(m.getQuantity()+i.getQuantity());
            m.setPrice(m.getQuantity()*m.getBook().getPrice());
        }else{
            items.add(i);
        }
        CartItems n = getItemById(i.getBook().getId());
        if(n.getQuantity() > n.getBook().getQuantity()){
            n.setQuantity(n.getBook().getQuantity());
            n.setPrice(n.getQuantity()*n.getBook().getPrice());
        }
    }
    
    public void removeItem(int id){
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
    
    public double getTotalPrice(){
        double p = 0;
        for (CartItems i: items){
            p += (i.getQuantity()*i.getBook().getPrice());            
        }
        return p;
    }
}
