package com.fi.crm.campaigns.persistence.mappers;

import com.fi.crm.campaigns.persistence.model.CpDetalleAudit;
import com.fi.crm.campaigns.persistence.model.CpDetalleAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpDetalleAuditMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int countByExample(CpDetalleAuditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByExample(CpDetalleAuditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByPrimaryKey(Integer detalleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insert(CpDetalleAudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insertSelective(CpDetalleAudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    List<CpDetalleAudit> selectByExample(CpDetalleAuditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    CpDetalleAudit selectByPrimaryKey(Integer detalleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExampleSelective(@Param("record") CpDetalleAudit record, @Param("example") CpDetalleAuditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExample(@Param("record") CpDetalleAudit record, @Param("example") CpDetalleAuditExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKeySelective(CpDetalleAudit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_DETALLE_AUDIT
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKey(CpDetalleAudit record);
}