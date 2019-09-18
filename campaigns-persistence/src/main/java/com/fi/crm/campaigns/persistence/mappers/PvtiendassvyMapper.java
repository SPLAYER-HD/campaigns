package com.fi.crm.campaigns.persistence.mappers;

import com.fi.crm.campaigns.persistence.model.Pvtiendassvy;
import com.fi.crm.campaigns.persistence.model.PvtiendassvyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PvtiendassvyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int countByExample(PvtiendassvyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByExample(PvtiendassvyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insert(Pvtiendassvy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insertSelective(Pvtiendassvy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    List<Pvtiendassvy> selectByExample(PvtiendassvyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExampleSelective(@Param("record") Pvtiendassvy record, @Param("example") PvtiendassvyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PVTIENDASSVY
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExample(@Param("record") Pvtiendassvy record, @Param("example") PvtiendassvyExample example);
}