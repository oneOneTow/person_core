
package com.ccbcfx.learn.tables.daos;


import com.ccbcfx.learn.enums.StaffStatusType;
import com.ccbcfx.learn.tables.pojos.Staff;
import com.ccbcfx.learn.tables.records.StaffRecord;
import org.jooq.*;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


import static com.ccbcfx.learn.tables.Staff.STAFF;

@Component
public class StaffDao {
    private static final Logger logger = LoggerFactory.getLogger(StaffDao.class);

    @Autowired
    DSLContext dslContext;

    /**
     * 插入一条数据
     *
     * @param staff
     * @return 插入数据的id
     */
    public int addStaff(Staff staff) {
        UInteger res = dslContext.insertInto(STAFF)
                .set(STAFF.NAME, staff.getName())
                .set(STAFF.GENDER, staff.getGender())
                .set(STAFF.BIRTHDAY, staff.getBirthday())
                .set(STAFF.STATUS, staff.getStatus())
                .set(STAFF.DOCUMENT_TYPE, staff.getDocumentType())
                .set(STAFF.DOCUMENT_NUMBER, staff.getDocumentNumber())
                .set(STAFF.CREATE_BY, staff.getCreateBy())
                .set(STAFF.CREATE_AT, staff.getCreateAt())
                .returning(STAFF.ID)
                .fetchOne()
                .get(STAFF.ID);
        return res.intValue();
    }

    /**
     * 删除指定主键的记录
     *
     * @param id
     * @return
     */
    public boolean delete(int id, int deleteBy) {
        int result = dslContext.update(STAFF)
                .set(STAFF.ENABLED, (byte) 0)
                .set(STAFF.DELETE_BY, deleteBy)
                .where(STAFF.ID.eq(UInteger.valueOf(id)))
                .execute();
        return result > 0 ? true : false;
    }

    /**
     * 根据主键查询数据
     *
     * @param id
     * @return
     */
    public Staff findById(UInteger id) {
        Field<?>[] fields =
                {STAFF.ID,
                        STAFF.NAME,
                        STAFF.GENDER,
                        STAFF.PHONE,
                        STAFF.STATUS,
                        STAFF.DOCUMENT_TYPE,
                        STAFF.IMG_URL,
                        STAFF.DOCUMENT_NUMBER,
                        STAFF.CREATE_BY,
                        STAFF.CREATE_AT,
                        STAFF.UPDATE_BY,
                        STAFF.UPDATE_AT,
                        STAFF.DELETE_BY,
                        STAFF.DELETE_AT};
        SelectConditionStep<Record> result = dslContext.select(fields).from(STAFF).where(STAFF.ID.eq(id));
        return result.fetchOneInto(Staff.class);
    }


    /**
     * 查询所有数据
     *
     * @param offset
     * @param size
     * @return
     */
    public List<Staff> findAll(int offset, int size) {
        List staffs = new ArrayList<Staff>();
        Result<Record1<UInteger>> result = dslContext.select(STAFF.ID)
                .from(STAFF)
                .limit(offset, size)
                .fetch();
        for (Record1<UInteger> record : result) {
            staffs.add(findById(record.getValue(STAFF.ID)));
        }
        return staffs;
    }


    /**
     * 修改员工状态
     *
     * @param id
     * @param status
     * @return
     */
    public boolean updateStatus(UInteger id, StaffStatusType status) {
        int result = dslContext.update(STAFF)
                .set(STAFF.STATUS, status)
                .where(STAFF.ID.eq(id))
                .execute();
        return result > 0;
    }

    /**
     * 修改指定id的员工
     *
     * @return
     */
    public int update(Staff staff) {
        StaffRecord record = dslContext.newRecord(STAFF, staff);
        return dslContext.executeUpdate(record);
    }

    public Staff updateWithReturn(Staff staff, UInteger id) {
        int resultResult = dslContext.update(STAFF)
                .set(STAFF.NAME,staff.getName())
                .set(STAFF.GENDER,staff.getGender())
                .set(STAFF.PHONE,staff.getPhone())
                .set(STAFF.DOCUMENT_TYPE,staff.getDocumentType())
                .set(STAFF.DOCUMENT_NUMBER,staff.getDocumentNumber())
                .set(STAFF.BIRTHDAY,staff.getBirthday())
                .where(STAFF.ID.eq(id))
                .execute();

        return resultResult>0;
    }

    public boolean updateImgUrl(int id, String imgUrl) {
        int result = dslContext.update(STAFF)
                .set(STAFF.IMG_URL, imgUrl)
                .where(STAFF.ID.eq(UInteger.valueOf(id)))
                .execute();
        return result > 0;
    }

    /**
     * 根据查询条件获取数据
     *
     * @param conditions
     * @param offset
     * @param size
     * @return
     */
    public List<Staff> findStaffByConditions(Condition[] conditions, int offset, int size) {
        List staffs = new ArrayList<Staff>();
        SelectQuery selectQuery = dslContext.selectQuery();
        selectQuery.addSelect(STAFF.ID);
        selectQuery.addFrom(STAFF);
        selectQuery.addConditions(conditions);
        selectQuery.addLimit(offset, size);
        Result<Record1<UInteger>> records = selectQuery.fetch();
        for (Record1<UInteger> record : records) {
            staffs.add(findById(record.getValue(STAFF.ID)));
        }
        return staffs;
    }
}
