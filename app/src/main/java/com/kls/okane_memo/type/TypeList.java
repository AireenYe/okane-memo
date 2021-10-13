package com.kls.okane_memo.type;

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

    public int getImageByName(String name)
    {
        for(TypeBean type : outTypeList){
            if(type.getTypename() == name)
                return type.getImageId();
        }
        for(TypeBean type : inTypeList){
            if(type.getTypename() == name)
                return type.getImageId();
        }
        return 0;
    }

    private TypeList() {
        outTypeList = new ArrayList<>();
        inTypeList = new ArrayList<>();

        outTypeList.add(new TypeBean(0, R.drawable.ic_investment1, -1,"投资"));
        outTypeList.add(new TypeBean(1, R.drawable.ic_food1, -1,"食物"));
        outTypeList.add(new TypeBean(2, R.drawable.ic_clothes1, -1, "衣服"));
        outTypeList.add(new TypeBean(3, R.drawable.ic_medication1, -1, "药品"));
        outTypeList.add(new TypeBean(4, R.drawable.ic_shopping1, -1, "购物"));
        outTypeList.add(new TypeBean(5, R.drawable.ic_study1, -1,"学习"));
        outTypeList.add(new TypeBean(6, R.drawable.ic_travel1, -1,"旅游"));
        outTypeList.add(new TypeBean(7, R.drawable.ic_daily1, -1,"家居用品"));
        outTypeList.add(new TypeBean(8, R.drawable.ic_house1, -1,"住房"));
        outTypeList.add(new TypeBean(9, R.drawable.ic_communication1, -1,"通讯"));
        outTypeList.add(new TypeBean(10, R.drawable.ic_gift1, -1,"礼物"));
        outTypeList.add(new TypeBean(11, R.drawable.ic_leisure1, -1,"休闲娱乐"));

        inTypeList.add(new TypeBean(0, R.drawable.ic_redpacket, -1,"红包"));
        inTypeList.add(new TypeBean(1, R.drawable.ic_salary, -1,"工资"));
        inTypeList.add(new TypeBean(2, R.drawable.ic_invest_income, -1,"投资回报"));
    }

}