package cn.geneticmemory.im.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Goods {
    private int avatar;
    private String goods_name;
    private String goods_number;
    private String goods_price;
    private String create_date;
    private String goods_suppers;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getGoods_suppers() {
        return goods_suppers;
    }

    public void setGoods_suppers(String goods_suppers) {
        this.goods_suppers = goods_suppers;
    }

    public Goods(int avatar, String goods_name, String goods_number, String goods_price, String create_date, String goods_suppers) {
        this.avatar = avatar;
        this.goods_name = goods_name;
        this.goods_number = goods_number;
        this.goods_price = goods_price;
        this.create_date = create_date;
        this.goods_suppers = goods_suppers;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "avatar=" + avatar +
                ", goods_name='" + goods_name + '\'' +
                ", goods_number='" + goods_number + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", create_date='" + create_date + '\'' +
                ", goods_suppers='" + goods_suppers + '\'' +
                '}';
    }
}
