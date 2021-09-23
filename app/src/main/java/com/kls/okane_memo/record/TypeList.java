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

        outTypeList.add(new TypeBean(0, R.drawable.ic_investment1, -1,"投资"));
        outTypeList.add(new TypeBean(1, R.drawable.ic_food1, -1,"食物"));
        outTypeList.add(new TypeBean(2, R.drawable.ic_clothes1, -1, "衣服"));
        outTypeList.add(new TypeBean(2, R.drawable.ic_medication1, -1, "药品"));
        outTypeList.add(new TypeBean(2, R.drawable.ic_shopping1, -1, "购物"));
        outTypeList.add(new TypeBean(1, R.drawable.ic_study1, -1,"学习"));
        outTypeList.add(new TypeBean(1, R.drawable.ic_travel1, -1,"旅游"));
    }

}
