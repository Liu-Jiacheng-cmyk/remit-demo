package org.example.common.domain.em.CommonEnum;

/**
 *  这个类的信息：
 *  @Description
 *  中文名：常用枚举类
 *  @ClassName BaseEnum
 *
 *  @author liujiacheng
 *  @date 2024/9/12 下午7:04
 */
public class BaseEnum {
    /**
     * 币种代码
     */
    public enum CurrCode implements BaseCodeEnum{
        CNY("CNY","156","人民币元");

        private final String code;        // 字母代码
        private final String value;  // 数字代码
        private final String name; // 中文名称

        /**
         * 构造函数
         */
        CurrCode(String code, String value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getValue() {
            return value;
        }
        @Override
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }

    /**
     * 账户状态代码
     * */
    public enum AccStatusCode implements BaseCodeEnum{
        ACTIVE("ACTIVE", "1", "正常"),
        FROZEN("FROZEN", "2", "冻结"),
        CLOSED("CLOSED", "3", "销户"),
        LOST("LOST", "4", "挂失"),
        INACTIVE("INACTIVE", "5", "停用"),
        NOT_ENABLE("NOT_ENABLE","6","未启用");

        private final String code;        // 状态代码
        private final String value;       // 数字代码
        private final String name;        // 中文名称

        /**
         * 构造函数
         */
        AccStatusCode(String code, String value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * 汇款方向代码
     */
    public enum RemDireCode implements BaseCodeEnum {
        INWARD("INWARD", "1", "借方"),
        OUTWARD("OUTWARD", "2", "贷方"),
        NOT_KNOW("NO_TKNOW","3","未知");

        private final String code;        // 借贷方向代码（字母代码）
        private final String value;       // 数值代码
        private final String name;        // 中文名称

        /**
         * 构造函数
         */
        RemDireCode(String code, String value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     * 职业代码
     */
    public enum OccupCode implements BaseCodeEnum {
        C100("C100", "100", "医生"),
        C101("C101", "101", "工程师"),
        C102("C102", "102", "教师"),
        C103("C103", "103", "律师"),
        C104("C104", "104", "会计"),
        C105("C105", "105", "护士"),
        C106("C106", "106", "警察"),
        C107("C107", "107", "军人"),
        C108("C108", "108", "厨师");

        private final String code;        // 职业字母代码
        private final String value;       // 职业数字代码
        private final String name;        // 职业中文名称

        /**
         * 构造函数
         */
        OccupCode(String code, String value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /**
     *
     */
}
