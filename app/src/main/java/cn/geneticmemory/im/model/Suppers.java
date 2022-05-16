package cn.geneticmemory.im.model;

public class Suppers {
    private int avatar;
    private String suppers_name;
    private String suppers_phone;
    private String suppers_address;
    private String last_time;
    private String create_date;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getSuppers_name() {
        return suppers_name;
    }

    public void setSuppers_name(String suppers_name) {
        this.suppers_name = suppers_name;
    }

    public String getSuppers_phone() {
        return suppers_phone;
    }

    public void setSuppers_phone(String suppers_phone) {
        this.suppers_phone = suppers_phone;
    }

    public String getSuppers_address() {
        return suppers_address;
    }

    public void setSuppers_address(String suppers_address) {
        this.suppers_address = suppers_address;
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

    public Suppers(int avatar, String suppers_name, String suppers_phone, String suppers_address, String last_time, String create_date) {
        this.avatar = avatar;
        this.suppers_name = suppers_name;
        this.suppers_phone = suppers_phone;
        this.suppers_address = suppers_address;
        this.last_time = last_time;
        this.create_date = create_date;
    }

    public Suppers() {
    }
}
