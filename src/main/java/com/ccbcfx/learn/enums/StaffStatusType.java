package com.ccbcfx.learn.enums;

public enum StaffStatusType {
    /**
     * 员工在职
     */
    working(0,"在职"),
    /**
     * 员工离职
     */
    leave(1,"离职");
    private int order;
    private String desc;
    StaffStatusType(int order,String desc){
        this.order=order;
        this.desc=desc;
    }
    public int getOrder() {
        return order;
    }
    public DocumentType getEnumByOrder(int order){
        for(DocumentType type:DocumentType.values()){
            if(type.getOrder()==order)return type;
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public DocumentType getEnumByDesc(String desc){
        for(DocumentType type:DocumentType.values()){
            if(desc.equals(type.getDesc()))return type;
        }
        return null;
    }
}
