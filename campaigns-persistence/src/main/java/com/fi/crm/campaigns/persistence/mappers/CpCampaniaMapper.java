package com.fi.crm.campaigns.persistence.mappers;

import com.fi.crm.campaigns.persistence.model.CpCampania;
import com.fi.crm.campaigns.persistence.model.CpCampaniaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpCampaniaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int countByExample(CpCampaniaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByExample(CpCampaniaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByPrimaryKey(Integer campaniaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insert(CpCampania record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insertSelective(CpCampania record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    List<CpCampania> selectByExample(CpCampaniaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    CpCampania selectByPrimaryKey(Integer campaniaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExampleSelective(@Param("record") CpCampania record, @Param("example") CpCampaniaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExample(@Param("record") CpCampania record, @Param("example") CpCampaniaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKeySelective(CpCampania record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_CAMPANIA
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKey(CpCampania record);
}