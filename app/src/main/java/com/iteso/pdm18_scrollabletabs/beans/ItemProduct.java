package com.iteso.pdm18_scrollabletabs.beans;

public class ItemProduct{
    private int code;
    private String title;
    private String store;
    private Category category;
    private String phone;
    private String description;
    private Integer image;

    @Override
    public String toString() {
        return "ItemProduct{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", category=" + category +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }

    public ItemProduct(int code, String title, String store, Category category, String phone, String description, Integer image) {
        this.code = code;
        this.title = title;
        this.store = store;
        this.category = category;
        this.phone = phone;
        this.description = description;
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

}
