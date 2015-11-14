package com.dream.service.util;

public enum AjaxStatusCodeType {

	UNAMEORPWD_ERROR(111, "用户名或密码错误"), RANDCOE_ERROR(112, "验证码错误"), USER_INACTIVATE(
			113, "用户已经失效"), LOGIN_TIMEOUT(114, "登录超时"), USER_STATUS_ERROR(115, "用户状态异常"),
	USER_LKEY_ERROR(116, "由于有其他用户用您的账号在其他设备上登录，导致了您的异常退出。如有问题，请联系蜘蛛客服。"),

	USED_ERROR(120, "用户名或邮箱已被使用"), EMAIL_ILLEGAL(121, "邮箱不合法"), PWD_ILLEGAL(
			122, "密码不合法"), PWD_DISAFFINITY(123, "密码两次输入不同"), UPLOAD_ERROR(124,
			"上传文件出错"), IN_AUDIT(125, "信息在审核中"), PHONE_ILLEGAL(126, "手机号不合法"), IDCARD_ILLEGAL(
			127, "身份证号不合法"), URL_ILLEGAL(128, "网站不合法"),

	EMPTY_CITY(130, "城市为空"), EMPTY_AREA(131, "行政区为空"), EMPTY_TIME(132, "时间选择为空"), EMPTY_ROOM(
			133, "房型选择为空"), EMPTY_RESPONSE_TIME(134, "反馈时间不合法"), EMPTY_AMOUNT_TIME(135, "反馈时间不合法"), 
			EMPTY_PAYRATIO(136, "设置预付比例"), EMPTY_BOOKING_GUEST(137, "入住人为空"), EMPTY_BOOKING_GUESTPHONE(138, "入住人为空"),

	PAY_PWD_ERROR(140, "支付密码错误"), PAY_INSUFFICIENT(141, "余额不足"), PAY_NO_PAYCHANNEL(142, "未选择支付方式"), 
	PAY_BIDSTATUS_ERROR(143, "订单状态异常"), PAY_ORDER_PAID(145, "订单状态异常"), PAY_COUPON_ERROR(146, "红包不能使用"),


	SUCCESS(200, "操作成功"),

	REDIREACT_300(300, "页面重定向"), 
	
	NOTFOUND_404(404, "找不到页面"),

	OPERATION_ERROR(500, "操作出现异常"), OPERATION_ILLEGAL(501, "非法操作"), OPERATION_DENIED(
			502, "非授权操作"), OPERATION_TIMEOUT(503, "操作超时"),

	ERROR_ACTIVATION_CODE(600, "激活码错误"), ERROR_USER_EMPTY(601, "用户不存在"),ERROR_USER_EXIST(602, "用户存在"),
	
	ERROR(700, "出错了"), OPRATER_ILLEGAL(701, "非法操作，没有权限访问，请联系管理员。"), BREACH_CONTRACT(702, "已违约"), 
	BREACH_CONTRACT_NUMBER(703, "多次违约，封账号"), MaliciousTestPrice(704, "恶意试价"),
	
	USERNAME_EMPTY(800, "请输入您的用户名"), USERNAME_LENGTH(801, "用户名长度不合法"), USERNAME_INVALID(
            802, "请输入正确的邮箱地址或者手机号码"),   USERNAME_UNEXIST(803, "用户名不存在"),

    PASSWORD_EMPTY(810, "请输入您的密码"), PASSWORD_LENGTH(811, "密码长度不合法"), PASSWORD_NOTMATCH(
            812, "确认密码不匹配"), PASSWORD_INVALID(813, "请输入符合要求的密码"),

    LOGIN_FAIL(820, "登录失败：用户名或密码错误"), LOGIN_NOT_ACTIVATED(821, "账户尚未激活"), LOGIN_DISABLED(
            822, "账户已经被冻结"),LOGIN_SESSION_OUT(825, "登录失败：用户未登录"),LOGIN_NOT_AUDIT(826, "账户待审核"), LOGIN_AUDIT_SUBMIT(827, "账户审核中"), 
    LOGIN_AUDIT_FAIL(828, "账户未审核通过"), LOGIN_AUDIT_SUSSCES(829, "账户审核通过"),

    EMAIL_EMPTY(830, "邮件地址不能为空"), EMAIL_LENGTH(831, "邮件地址长度不合法"), EMAIL_INVALID(
            832, "请输入正确的邮件地址"), EMAIL_DUPLICATE(833, "该电子邮件已经被注册"), 

    MOBILE_EMPTY(840, "手机号码不能为空"), MOBILE_INVALID(841, "请输入合法的手机号码"), MOBILE_DUPLICATE(
            842, "该手机号码已经被注册"),

    REALNAME_EMPTY(850, "姓名不能为空"), REALNAME_LENGTH(851, "姓名长度不合法"),

    RNDCODE_EMPTY(860, "请填写验证码"), RNDCODE_LENGTH(861, "验证码长度不合法"), RNDCODE_INVALID(
            862, "验证码不正确"),

    UPLOAD_EMPTY(870, "上传文件不能为空"), UPLOAD_SIZE(871, "上传文件尺寸太大"), UPLOAD_INVALID(
            872, "上传文件类型不合法"),
	
	HOTEL_NAME_EMPTY(900, "酒店名称不能为空"), HOTEL_NAME_LENGTH(901, "酒店名称长度不合法"), HOTEL_BLICENSE_EMPTY(
            902, "请上传营业执照图片"), HOTEL_OLICENSE_EMPTY(903, "请上传组织机构代码证件图片"), HOTEL_TLICENSE_EMPTY(
            904, "请上传特殊经营许可证图片"),HOTEL_ALREADY_EXISTS(905, "酒店已存在"),HOTEL_STAR_EXISTS(906, "星级必选"),

    ADDRESS_EMPTY(910, "地址不能为空"), ADDRESS_LENGTH(911, "地址长度不合法"),

    PHONE_EMPTY(920, "电话号码不能为空"), PHONE_INVALIDE(921, "请填写正确的电话号码"),PHONE_SMS_OFTEN(922, "短信操作太频繁,请稍后再试"),PHONE_SMS_ERROR(923, "短信验证码错误"),
    
    PWD_SAME(924, "新老密码密码相同"),

    FAX_EMPTY(930, "传真号码不能为空"), FAX_INVALID(931, "请填写正确的传真号码"),

    BANK_NAME_EMPTY(940, "开户银行不能为空"), BANK_ACCOUNT_EMPTY(941, "银行账户不能为空"), BANK_TITLE_EMPTY(
            942, "账户名称不能为空"), BANK_NAME_LENGTH(943, "开户银行长度不合法"), BANK_ACCOUNT_LENGTH(
            944, "银行账户长度不合法"), BANK_TITLE_LENGTH(945, "账户名称长度不合法"),FINANCE_TRADING_PWD(946,"交易密码为空"),

    CITY_EMPTY(950, "城市不能为空"),

    AREA_EMPTY(960, "区域不能为空"),
    
    ROOM_NAME_ERROR(980,"房型名称已存在"),
    
    BIDDING_QUOTES_RE(990, "总报价与客户预计价格接近提示"), HOTEL_CHECKIN(555,"签到失败，当天至少需要一种房型处于可售状态才能完成签到，请检查您的客房状态"),
	
	BOOKING_CART_HASITEM(1200, "购物车已有其它酒店"), BOOKING_CART_NO_ROOMNUM(1201, "房量不足"), BOOKING_CART_SUBMIT_ERROR(1202, "购物车提交错误"),
	IN_HOTEL_BAN_LIST(1210, "被酒店禁止下单");
	
	public Integer code;

	public String name;

	private AjaxStatusCodeType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static AjaxStatusCodeType getById(Integer code) {
		if (null == code) {
			return null;
		}
		for (AjaxStatusCodeType tmp : AjaxStatusCodeType.values()) {
			if (tmp.code.intValue() == code.intValue()) {
				return tmp;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
