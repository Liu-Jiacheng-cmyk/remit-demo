package org.example.bdus2.domain.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.bdus2.domain.dao.mdo.RmrgtLifecycleMdo;

public interface RmrgtLifecycleMdoMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String remittanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    int insert(RmrgtLifecycleMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    int insertSelective(RmrgtLifecycleMdo record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    RmrgtLifecycleMdo selectByPrimaryKey(String remittanceId);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RmrgtLifecycleMdo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_rmrgt_lifecycle
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RmrgtLifecycleMdo record);
}