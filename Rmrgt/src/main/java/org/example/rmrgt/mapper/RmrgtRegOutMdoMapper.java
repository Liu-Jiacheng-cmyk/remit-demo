package org.example.rmrgt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.rmrgt.dao.mdo.RmrgtRegOutMdo;

@Mapper
public interface RmrgtRegOutMdoMapper {


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
    int insert(RmrgtRegOutMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int insertSelective(RmrgtRegOutMdo record);



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    RmrgtRegOutMdo selectByPrimaryKey(String remittanceId);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RmrgtRegOutMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_reg_out
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RmrgtRegOutMdo record);
}