package org.example.common.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@lombok.Data
public class RemitDTO extends BaseDTO {
    /**
     * 汇款编号
     */
    @NotNull(message = "汇款编号不能为空")
    private String remitNo;

    /**
     * 付款账号
     */
    @NotNull(message = "付款账号不能为空")
    @Size(min = 13, max = 19, message = "付款账号错误")
    private String payerNo;

    /**
     * 付款人密码
     */
    @NotNull(message = "密码不能为空")
    private String payerPwd;

    /**
     * 付款人姓名
     */
    @NotNull(message = "付款人姓名不能为空")
    private String payerName;

    /**
     * 收款人账号
     */
    @NotNull(message = "收款人账号不能为空")
    @Size(min = 13, max = 19, message = "收款账号错误")
    private String payeeNo;

    /**
     * 收款人姓名
     */
    @NotNull(message = "收款人姓名不能为空")
    private String payeeName;

    /**
     * 转账时间
     */
    @NotNull(message = "收款人姓名不能为空")
    private String remitDate;

    /**
     * 转账金额
     */
    @NotNull(message = "转账金额不能为空")
    private BigDecimal remitAmount;

    /**
     * 转账附言
     */
    private String remitRemarks;


}
