package org.example.bdus1.domain.dao.mdo;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerAccountInfoMdo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.account_number
     *
     * @mbg.generated
     */
    private String accountNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.customer_number
     *
     * @mbg.generated
     */
    private String customerNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.account_password
     *
     * @mbg.generated
     */
    private String accountPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.account_balance
     *
     * @mbg.generated
     */
    private BigDecimal accountBalance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.account_status

     *
     * @mbg.generated
     */
    private String accountStatus
;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_customer_account_info.updated_at
     *
     * @mbg.generated
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.account_number
     *
     * @return the value of tb_customer_account_info.account_number
     *
     * @mbg.generated
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.account_number
     *
     * @param accountNumber the value for tb_customer_account_info.account_number
     *
     * @mbg.generated
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.customer_number
     *
     * @return the value of tb_customer_account_info.customer_number
     *
     * @mbg.generated
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.customer_number
     *
     * @param customerNumber the value for tb_customer_account_info.customer_number
     *
     * @mbg.generated
     */
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber == null ? null : customerNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.account_password
     *
     * @return the value of tb_customer_account_info.account_password
     *
     * @mbg.generated
     */
    public String getAccountPassword() {
        return accountPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.account_password
     *
     * @param accountPassword the value for tb_customer_account_info.account_password
     *
     * @mbg.generated
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword == null ? null : accountPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.account_balance
     *
     * @return the value of tb_customer_account_info.account_balance
     *
     * @mbg.generated
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.account_balance
     *
     * @param accountBalance the value for tb_customer_account_info.account_balance
     *
     * @mbg.generated
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.account_status

     *
     * @return the value of tb_customer_account_info.account_status

     *
     * @mbg.generated
     */
    public String getAccountStatus
() {
        return accountStatus
;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.account_status

     *
     * @param accountStatus
 the value for tb_customer_account_info.account_status

     *
     * @mbg.generated
     */
    public void setAccountStatus
(String accountStatus
) {
        this.accountStatus
 = accountStatus
 == null ? null : accountStatus
.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.created_at
     *
     * @return the value of tb_customer_account_info.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.created_at
     *
     * @param createdAt the value for tb_customer_account_info.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_customer_account_info.updated_at
     *
     * @return the value of tb_customer_account_info.updated_at
     *
     * @mbg.generated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_customer_account_info.updated_at
     *
     * @param updatedAt the value for tb_customer_account_info.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}