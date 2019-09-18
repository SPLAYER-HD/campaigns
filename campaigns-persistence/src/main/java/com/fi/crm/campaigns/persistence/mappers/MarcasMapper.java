package com.fi.crm.campaigns.persistence.mappers;

import com.fi.crm.campaigns.persistence.model.Marcas;
import com.fi.crm.campaigns.persistence.model.MarcasExample;
import com.fi.crm.campaigns.persistence.model.MarcasKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarcasMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int countByExample(MarcasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByExample(MarcasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int deleteByPrimaryKey(MarcasKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insert(Marcas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int insertSelective(Marcas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    List<Marcas> selectByExample(MarcasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    Marcas selectByPrimaryKey(MarcasKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExampleSelective(@Param("record") Marcas record, @Param("example") MarcasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByExample(@Param("record") Marcas record, @Param("example") MarcasExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKeySelective(Marcas record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table NAF6.MARCAS
     *
     * @mbggenerated Tue Apr 21 12:41:59 COT 2015
     */
    int updateByPrimaryKey(Marcas record);
}