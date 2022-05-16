package cn.geneticmemory.im.model;

public class Orders {
    private int order_avatar;
    private String order_goods;
    private String order_count;
    private String order_price;
    private String order_money;
    private String order_client;
    private String create_date;

    public int getOrder_avatar() {
        return order_avatar;
    }

    public void setOrder_avatar(int order_avatar) {
        this.order_avatar = order_avatar;
    }

    public String getOrder_goods() {
        return order_goods;
    }

    public void setOrder_goods(String order_goods) {
        this.order_goods = order_goods;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public String getOrder_client() {
        return order_client;
    }

    public void setOrder_client(String order_client) {
        this.order_client = order_client;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Orders() {
    }

    public Orders(int order_avatar, String order_goods, String order_count, String order_price, String order_money, String order_client) {
        this.order_avatar = order_avatar;
        this.order_goods = order_goods;
        this.order_count = order_count;
        this.order_price = order_price;
        this.order_money = order_money;
        this.order_client = order_client;
    }

    public Orders(int order_avatar, String order_goods, String order_count, String order_price, String order_money, String order_client, String create_date) {
        this.order_avatar = order_avatar;
        this.order_goods = order_goods;
        this.order_count = order_count;
        this.order_price = order_price;
        this.order_money = order_money;
        this.order_client = order_client;
        this.create_date = create_date;
    }
}
