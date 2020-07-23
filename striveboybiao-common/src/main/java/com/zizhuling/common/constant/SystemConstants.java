package com.zizhuling.common.constant;

/**
 *
 * @author wuchao Create on2019/8/28
 * @version 1.0
 */
public class SystemConstants {

    /**
     * 默认的业务数据库数据源beanId
     */
    public static final String  DEFAULT_BUSINESS_DATA_SOURCE_NAME = "ims-druidDataSource-drds";
    /**
     * 数据库公共字段RID
     */
    public static final String  COLUMN_R_ID = "RID";
    /**
     * 默认的缓存名称
     */
    public static final String  CACHE_NAME = "hsaf-redis";

    /**
     * 默认的redisTemplate的BeanId
     */
    public static final String  DEFAULT_REDIS_TEMPLATE_BEAN_NAME = "hsafRedisTemplate";

    /**
     * 时间格式 年月日时分秒
     */
    public static final String  DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式 年月日
     */
    public static final String  DATE_FORMAT_Y_M_D= "yyyy-MM-dd";

    /**
     * 入口函数无参
     */
    public static final String  ENTER_FUNCTION= "Enter Function Params";
    /**
     * HashMap初始化容量
     */
    public static final int INIT_HASHMAP_CAPACITY = 16;

    /**
     *应用的名称
     */
    public static final String  APP_NAME = "IMS";

    /**
     * 医保局项目
     */
    public static final String  HSA_APP_NAME = "HSA";

    /**
     * redis缓存的KEY前缀
     */
    public static final String  REDIS_KEY_PREFIX = HSA_APP_NAME + ":" + APP_NAME + ":";

    /**
     * 入口函数单个参
     */
    public static final String  ENTER_FUNCTION_SINGLE_PARAM = ENTER_FUNCTION + ": {}";

    /**
     * 入口函数两个参
     */
    public static final String  ENTER_FUNCTION_DOUBLE_PARAM = ENTER_FUNCTION_SINGLE_PARAM + ",{}";

    /**
     * 入口函数三个参
     */
    public static final String  ENTER_FUNCTION_THIRD_PARAM = ENTER_FUNCTION_DOUBLE_PARAM + ",{}";

    /**
     * 出口函数一个参
     */
    public static final String  EXIST_FUNCTION_PARAM = "Exist Function Params: {}";

    /**
     * result 对象中的key
     */
    public static final String  RESULT_LIST="list";
    public static final String  RESULT_DATA="data";
    public static final String  RESULT_CHILD="children";

    /**
     * 出口函数无参
     */
    public static final String  EXIST_FUNCTION = "Exist Function Param";

    public static final String  EXCEL_FILE_SUFFIX=".xlsx";
    public static final String  EXCEL_FILE_SUFFIX_XLS=".xls";

    /**
     *字符格式的时间格式带毫秒
     */
    public static final String  DATE_TYPE_STR_FULL = "yyyyMMddHHmmssSSS";
    /**
     *字符格式的时间格式不带毫秒
     */
    public static final String  DATE_STR="yyyyMMddHHmmss";
    public static final String  PERCENT="%";

    /**
     * 排序方式-降序
     */
    public static final String  SORT_TYPE_DESC = "DESC";
    /**
     * 排序方式-升序
     */
    public static final String  SORT_TYPE_ASC = "ASC";
    public static final String  YEAR="年";
    public static final String  MONTH="月";

    /**
     * 长文本设置输入范围
     */
    public static final Integer TEXTAREA_MIN_LENGTH = 1;
    public static final Integer TEXTAREA_MAX_LENGTH = 500;

    public static final String  WINDOWS_SPLIT = "\\";
    public static final char CHAR_WINDOWS_SPLIT = '\\';
    public static final String  OBLIQUE_LINE = "/";
    public static final char CHAR_OBLIQUE_LINE = '/';
    public static final String  OS_NAME_FLAG_WIN = "Win";
    public static final String  OS_NAME = "os.name";

    public static final String  DATE_YEAR = "yyyy";
    public static final String  DATE_MONTH = "MM";
    /**
     * 季度 Q1,Q2,Q3,Q4
     */
    public static final String  Q1 = "Q1";
    public static final String  Q2 = "Q2";
    public static final String  Q3 = "Q3";
    public static final String  Q4 = "Q4";

    /**
     * 进销存比对分析excel
     */
    public static final String  SELT_SEL_CPR = "比对分析";

    /**
     * 热力图部分常量
     */
    public static final String  HEI = "黑";
    public static final String  NEI = "内";
    public static final String  XIN = "新疆建设兵团";

    /**
     * 字符串连接分隔符
     */
    public static final String  SEPARATOR = ",";

    /**
     * 折线图单位
     */
    public static final String  RC_RS_VALUE="人";
    public static final String  WG_KK_VALUE="万元";
    /**
     * 导出excel时间格式
     */
    public static final String  YYYY_MM_DD_HH = "yyyyMMddHH";

    /**
     * 构建异常信息的key
     */
    public static final String  CODE="code";
    public static final String  MESSAGE="message";
    /**
     * 比对分析导出文件名称
     */
    public static final String  SETL_SEL_FILE_NAME="药品结算销售比对统计表";

    /**
     * 最大上传文件数量
     */
    public static final int UPLOAD_FILE_MAX_COUNT = 5;

    /**
     * 文件上传大小(M)
     */
    public static final int UPLOAD_FILE_SIZE_UNT_M = 1 << 20;

    public static final String  ERROR="ERROR: ";

    public static final String  HTMLS = "htmls";

    /**
     * 分页参数: 总数
     */
    public static final String  TOTAL="total";
    /**
     * 分页参数: 每页的数量
     */
    public static final String  PAGESIZE="pageSize";
    /**
     * 分页参数: 第几页
     */
    public static final String  CURRENTPAGE="currentPage";

    /**
     * 百分号
     */
    public static final String  PRECENT = "%";

    /**
     * 程序打包出来的jar包名称,jar包名称有变这个也需要修改
     */
    public static final String  PROJECT_NAME="hsa-ims-web";

    /**
     * 成功的编码
     */
    public static final String  SUCCESS_CODE="1";
    /**
     * excel导出的路径
     */
    public static final String  TEMPLATE_PATH="template/";
    /**
     * 空格
     */
    public static final String  BLANK_SPACE= " ";
    /**
     * 行政区划
     */
    public static final String  ADMDVS="admdvs";
    /**
     * 前端下拉框key
     */
    public static final String  LABEL="label";

    /**
     *  SONAR:Define a constant instead of duplicating this literal "E10009" 3 times.
     */
    public static final String  E_CODE = "E10009";

    /**
     * 上报附件id
     */
    public static final String ATT_UPLOAD_ID="attUploadId";
}
