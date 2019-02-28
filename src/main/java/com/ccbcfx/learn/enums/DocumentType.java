package com.ccbcfx.learn.enums;

public enum DocumentType {
    /**
     * 居名身份证
     */
    identityId(0,"身份证"),
    /**
     * 军人身份证
     */
    armyIdentityId(1,"军人身份证"),
    /**
     * 警官证
     */
    policeIdentityId(2,"警官证"),
    /**
     * 港澳通行证
     */
    hongKongMacauPass(3,"港澳通行证"),
    /**
     * 护照
     */
    passport(4,"护照");

    private int order;
    private String desc;

    DocumentType(int order,String desc){
        this.order=order;
        this.desc=desc;
    }

    public int getOrder() {
        return order;
    }
    public static DocumentType getEnumByOrder(int order){
        for(DocumentType type:DocumentType.values()){
            if(type.getOrder()==order)return type;
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public static DocumentType getEnumByDesc(String desc){
        for(DocumentType type:DocumentType.values()){
            if(desc.equals(type.getDesc()))return type;
        }
        return null;
    }
}
