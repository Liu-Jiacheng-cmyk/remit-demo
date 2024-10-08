package org.example.rmrgt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.rmrgt.dao.po.RmrgtRegOutPo;

@Mapper
public interface RmrgtRegOutPoMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String remittanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int insert(RmrgtRegOutPo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int insertSelective(RmrgtRegOutPo record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    RmrgtRegOutPo selectByPrimaryKey(String remittanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RmrgtRegOutPo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RmrgtRegOutPo record);
}