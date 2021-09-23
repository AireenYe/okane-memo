package com.kls.okane_memo.record;

public class TypeBean {
    int typeId;     // 类型id
    int imageId;    // 类型图片
    int kind;   // 1为收入，-1为支出
    String typename;    // 类型名字

    public TypeBean(int typeId, int imageId, int kind, String typename) {
        this.typeId = typeId;
        this.imageId = imageId;
        this.kind = kind;
        this.typename = typename;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int id) {
        this.typeId = id;
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
