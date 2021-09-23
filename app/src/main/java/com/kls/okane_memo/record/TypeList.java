package com.kls.okane_memo.record;

import com.kls.okane_memo.R;

import java.util.ArrayList;

public class TypeList {
    private static TypeList typeList;
    private ArrayList<TypeBean> inTypeList;
    private ArrayList<TypeBean> outTypeList;

    public static synchronized TypeList getInstance(){
        if(typeList == null){
            typeList = new TypeList();
        }
        return typeList;
    }

    public int getSizeOfInTypes(){
        return inTypeList.size();
    }

    public int getSizeOfOutTypes(){
        return outTypeList.size();
    }

    public TypeBean getInTypeBean(int id){
        return inTypeList.get(id);
    }

    public TypeBean getOutTypeBean(int id){
        return outTypeList.get(id);
    }

    private TypeList() {
        outTypeList = new ArrayList<>();
        inTypeList = new ArrayList<>();

        outTypeList.add(new TypeBean(0, R.drawable.test, -1,"测试"));
        outTypeList.add(new TypeBean(1, R.mipmap.ic_eat, -1,"餐饮"));
        outTypeList.add(new TypeBean(2, R.mipmap.ic_clothes, -1, "衣服"));
    }

}
