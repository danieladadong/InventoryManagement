package cn.geneticmemory.im.model;

public class Client {
    private int avatar;
    private String client_name;
    private String client_phone;
    private String client_address;
    private String last_time;
    private String create_date;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Client(int avatar, String client_name, String client_phone, String client_address, String last_time, String create_date) {
        this.avatar = avatar;
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.client_address = client_address;
        this.last_time = last_time;
        this.create_date = create_date;
    }

    public Client() {
    }
}
