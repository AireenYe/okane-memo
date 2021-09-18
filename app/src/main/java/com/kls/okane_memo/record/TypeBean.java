package com.kls.okane_memo.record;

public class TypeBean {
    int id;     // 类型id
    int imageId;    // 类型图片
    String typename;    // 类型名字

    public TypeBean(int id, int imageId, String typename) {
        this.id = id;
        this.imageId = imageId;
        this.typename = typename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
