package com.fi.crm.campaigns.persistence.mappers;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.common.dto.UserInfoDTO;

public interface PvusuariosCustomMapper {
    /**
     * This method return password encrypted.
     *
     */
    public String getPasswordEncrypted(@Param("password")  String password);
    
    /**
     * This method return User.
     */
    public UserInfoDTO getUserByUserCode(@Param("userCode")  String userCode);
}