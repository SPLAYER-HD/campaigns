package com.fi.crm.campaigns.persistence.mappers;

import com.fi.crm.campaigns.persistence.model.CpPerfil;
import com.fi.crm.campaigns.persistence.model.CpPerfilExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpPerfilMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int countByExample(CpPerfilExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByExample(CpPerfilExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByPrimaryKey(Integer perfilId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insert(CpPerfil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insertSelective(CpPerfil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    List<CpPerfil> selectByExample(CpPerfilExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    CpPerfil selectByPrimaryKey(Integer perfilId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExampleSelective(@Param("record") CpPerfil record, @Param("example") CpPerfilExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExample(@Param("record") CpPerfil record, @Param("example") CpPerfilExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKeySelective(CpPerfil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CAMPAIGNS.CP_PERFIL
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKey(CpPerfil record);
}