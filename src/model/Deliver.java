/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ZeyuLiao
 */
public class Deliver {

    private String idfordelivery;
    private String delivery_NO;
    private String order_id;
    private String delivery_company;
    private String delivery_status;
    private String Delivered_time;

    public String getIdfordelivery() {
        return idfordelivery;
    }

    public void setIdfordelivery(String idfordelivery) {
        this.idfordelivery = idfordelivery;
    }

    public String getDelivery_NO() {
        return delivery_NO;
    }

    public void setDelivery_NO(String delivery_NO) {
        this.delivery_NO = delivery_NO;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDelivery_company() {
        return delivery_company;
    }

    public void setDelivery_company(String delivery_company) {
        this.delivery_company = delivery_company;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public String getDelivered_time() {
        return Delivered_time;
    }

    public void setDelivered_time(String Delivered_time) {
        this.Delivered_time = Delivered_time;
    }
       

    @Override
    public String toString() {
        return "Order{" + "idfordelivery=" + idfordelivery + ", delivery_NO=" + delivery_NO + ", order_id=" + order_id + ", delivery_company=" + delivery_company + ", delivery_status=" + delivery_status + ", Delivered_time=" + Delivered_time + "}";
    }


    
}
