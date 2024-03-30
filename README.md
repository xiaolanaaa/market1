虚拟货币行情接口

json格式字符串前端转成json类型对象即可

获取虚拟货币行情：
	url：http://192.168.2.100:9091/Virtual/findPrice
	
	请求方式 get请求
	
	返回json格式字符串
	instType	String	产品类型
	instId	String	产品id， 如 BTC-USDT
	uly	String	标的指数，如 BTC-USD，仅适用于交割/永续/期权
	instFamily	String	交易品种，如 BTC-USD，仅适用于交割/永续/期权
	category	String	币种类别（已废弃）
	baseCcy	String	交易货币币种，如 BTC-USDT 中的 BTC ，仅适用于币币/币币杠杆
	quoteCcy	String	计价货币币种，如 BTC-USDT 中的USDT ，仅适用于币币/币币杠杆
	settleCcy	String	盈亏结算和保证金币种，如 BTC 仅适用于交割/永续/期权
	ctVal	String	合约面值，仅适用于交割/永续/期权
	ctMult	String	合约乘数，仅适用于交割/永续/期权
	ctValCcy	String	合约面值计价币种，仅适用于交割/永续/期权
	optType	String	期权类型，C或P 仅适用于期权
	stk	String	行权价格，仅适用于期权
	listTime	String	上线时间Unix时间戳的毫秒数格式，如 1597026383085
	expTime	String	产品下线时间
	适用于币币/杠杆/交割/永续/期权，对于 交割/期权，为交割/行权日期；亦可以为产品下线时间，有变动就会推送。
	lever	String	该instId支持的最大杠杆倍数，不适用于币币、期权
	tickSz	String	下单价格精度，如 0.0001
	对于期权来说，是梯度中的最小下单价格精度，如果想要获取期权价格梯度，请使用"获取期权价格梯度"接口
	lotSz	String	下单数量精度
	合约的数量单位是张，现货的数量单位是交易货币
	minSz	String	最小下单数量
	合约的数量单位是张，现货的数量单位是交易货币
	ctType	String	合约类型
	linear：正向合约
	inverse：反向合约
	仅适用于交割/永续
	alias	String	合约日期别名
	this_week：本周
	next_week：次周
	this_month：本月
	next_month：次月
	quarter：季度
	next_quarter：次季度
	仅适用于交割
	不建议使用，用户应通过 expTime 字段获取合约的交割日期
	state	String	产品状态
	live：交易中
	suspend：暂停中
	preopen：预上线，如：交割和期权的新合约在 live 之前，会有 preopen 状态
	test：测试中（测试产品，不可交易）
	maxLmtSz	String	限价单的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是交易货币
	maxMktSz	String	市价单的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是USDT
	maxLmtAmt	String	限价单的单笔最大美元价值
	maxMktAmt	String	市价单的单笔最大美元价值
	仅适用于币币/币币杠杆
	maxTwapSz	String	时间加权单的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是交易货币
	maxIcebergSz	String	冰山委托的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是交易货币
	maxTriggerSz	String	计划委托委托的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是交易货币
	maxStopSz	String	止盈止损市价委托的单笔最大委托数量
	合约的数量单位是张，现货的数量单位是USDT
	
获取指定虚拟币行情
	url:http://192.168.2.100:9091/Virtual/findByIdPrice
	
	请求参数：instId 虚拟货币id	String类型
	
	请求方式 get请求
	
	返回格式 json格式字符串	
	instId	String	指数
	idxPx	String	最新指数价格
	high24h	String	24小时指数最高价格
	low24h	String	24小时指数最低价格
	open24h	String	24小时指数开盘价格
	sodUtc0	String	UTC 0 时开盘价
	sodUtc8	String	UTC+8 时开盘价
	ts	String	指数价格更新时间，Unix时间戳的毫秒数格式，如1597026383085
	
获取指定虚拟币行情K线
	url:http://192.168.2.100:9091/Virtual/findKLine
	
	请求参数：instId 虚拟货币id	String类型
	
	请求方式 get请求
	
	返回格式 json格式字符串	
	ts	String	开始时间，Unix时间戳的毫秒数格式，如 1597026383085
	o	String	开盘价格
	h	String	最高价格
	l	String	最低价格
	c	String	收盘价格
	confirm	String	K线状态
	0 代表 K 线未完结，1 代表 K 线已完结。
	
获取虚拟货币的总持仓量
	url:http://192.168.2.100:9091/Virtual/findPosition
	
	请求参数：instType  产品类型：	String类型

		SWAP：   永续合约   
		FUTURES：交割合约    
		OPTION： 期权	
	
	请求方式 get请求

	返回格式 json格式字符串
	instType	String	产品类型
	instId	String	产品ID
	oi	String	持仓量（按张折算）
	oiCcy	String	持仓量（按币折算）
	ts	String	数据返回时间，Unix时间戳的毫秒数格式 ，如 1597026383085

获取虚拟货币增幅
	
	url：url:http://192.168.2.100:9091/Virtual/findIncrease
	
	请求方式get请求
	
	返回值json对象
	s	币种名称
	S	币种符号
	u	价格(USD)
	b	价格(BTC)
	v	交易量(USD)
	T	时间戳(毫秒)
	a	交易量(单位为当前币种)
	ra	报告交易量(单位为当前币种)
	rv	报告交易量(USD)
	m	市值(USD)
	c	24小时涨跌幅
	h	24小时最高价
	l	24小时最低价
	cw	1周涨跌幅
	hw	1周最高价
	lw	1周最低价
	cm	1月涨跌幅
	hm	1月最高价
	lm	1月最低价
	ha	历史最高价
	la	历史最低价
