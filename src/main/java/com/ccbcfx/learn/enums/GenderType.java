package com.ccbcfx.learn.enums;

public enum GenderType {
    /**
     * 男
     */
    man(0,"男"),
    /**
     * 女
     */
    woman(1,"女");
    private int order;
    private String desc;
    GenderType(int order, String desc){
        this.order=order;
        this.desc=desc;
    }
    public int getOrder() {
        return order;
    }
    public static GenderType getEnumByOrder(int order){
        for(GenderType type: GenderType.values()){
            if(type.getOrder()==order)return type;
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public static GenderType getEnumByDesc(String desc){
        for(GenderType type: GenderType.values()){
            if(desc.equals(type.getDesc()))return type;
        }
        return null;
    }
}
