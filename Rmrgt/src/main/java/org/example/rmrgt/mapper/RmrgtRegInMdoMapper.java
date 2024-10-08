package org.example.rmrgt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.rmrgt.dao.mdo.RmrgtRegInMdo;

@Mapper
public interface RmrgtRegInMdoMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String remittanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    int insert(RmrgtRegInMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    int insertSelective(RmrgtRegInMdo record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    RmrgtRegInMdo selectByPrimaryKey(String remittanceId);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RmrgtRegInMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_in
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RmrgtRegInMdo record);

    RmrgtRegInMdo selectByRemitNo(String remitNo);

}