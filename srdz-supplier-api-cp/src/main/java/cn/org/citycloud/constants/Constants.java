package cn.org.citycloud.constants;

/**
 * 常量类
 * 
 * @author lanbo
 *
 */
public class Constants
{
    
    public static final long TOKEN_EXPIRES_IN = 86400;
    
    public static final String TOKEN_SECRET = "IFFa52XkBEQ9AoO8";
    
    
    // 会员的状态 1为开启 0为关闭
    public static final int MEMBER_STATE_OPEN = 1;
    
    public static final int MEMBER_STATE_CLOSED = 0;
    
    
    // 订单状态：0(已取消)10(默认):未付款;20:已付款;30:已接单;40:已使用;50:已评价
    public static final int ORDER_STATUS_CANCELD = 0;
    
    public static final int ORDER_STATUS_DEFAULT = 10;
    
    public static final int ORDER_STATUS_PAYED = 20;
    
    public static final int ORDER_STATUS_RECEIVED = 30;
    
    public static final int ORDER_STATUS_EVALUATED = 50;
    
    // 订单类型: 1 商品类订单; 2 服务类订单
    public static final int ORDER_TYPE_GOODS = 1;
    
    public static final int ORDER_TYPE_SERVICE = 2;
    
    
    // 支付状态
    public static final int PAY_STATE_PAYED = 1;
    
    // 商品类型 1默认2团购商品3限时折扣商品4组合套装5赠品
    public static final int GOODS_TYPE_DEFAULT = 1;
    
    // 评价状态 0 未评价 1买家评价 2卖家评价 3双方互评
    public static final int GEVAL_TYPE_BUYERS = 1;
    
    // 商品状态 0下架，1正常，10违规（禁售）
    public static final int GOODS_STATE_OFFLINE = 0;
    
    public static final int GOODS_STATE_NORMAL = 1;
    
    public static final int GOODS_STATE_DISABLE = 0;
    
    // 服务类型 1 上门服务 ;2 到店服务
    public static final int SERVICE_TYPE_CALL = 1;
    
    public static final int SERVICE_TYPE_STORE = 2;
    
    // 优惠劵状态 10为正常，20为失效
    public static final int COUPON_STATUS_NORMAL = 10;
    
    public static final int COUPON_STATUS_INVALID = 20;
    
    // 优惠劵使用状态
    public static final int COUPON_UNUSED = 10;
    
    public static final int COUPON_USED = 20;
    
    // 服务状态 0下架，1正常，10违规（禁售）
    public static final int SERVICE_STATE_OFFLINE = 0;
    
    public static final int SERVICE_STATE_NORMAL = 1;
    
    public static final int SERVICE_STATE_DISABLE = 0;
    
    // 服务审核 1 审核通过，0未通过，2 驳回
    public static final int SERVICE_VERIFY_PASS = 1;
    
    public static final int SERVICE_VERIFY_NOTPASS = 0;
    
    public static final int SERVICE_VERIFY_REJECT = 2;
    
    // 服务订单状态：0(已取消)10(默认):未付款;20:已付款;30:已接单;40:已使用;50:已评价
    public static final int SERVICE_ORDER_CANCELD = 0;
    
    public static final int SERVICE_ORDER_DEFAULT = 10;
    
    public static final int SERVICE_ORDER_PAYED = 20;
    
    public static final int SERVICE_ORDER_ACCEPT = 30;
    
    public static final int SERVICE_ORDER_USED = 40;
    
    public static final int SERVICE_ORDER_EVALUATED = 50;
    
    //  账户类型(1：平台；2：供应商  3：服务中心  4 ：分销商)
    public static final int ACC_TYPE_SRDZ = 1;
    
    public static final int ACC_TYPE_SUPPLIER = 2;
    
    public static final int ACC_TYPE_SERVICE_CENTER = 3;
    
    public static final int ACC_TYPE_SALES = 3;
    
    // 平台消息类型
    public static final int PLATFORM_ADMIN = 1;
    
    public static final int PLATFORM_SUPPLIER = 2;
    
    public static final int PLATFORM_SERVICECENTER= 3;
    
    public static final int PLATFORM_MEMBER = 4;
    
    // 订单退款状态
    
    
    
    //默认验证码
    public static final String MAGIC_CODE = "666666";
    
}
