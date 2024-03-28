package com.huohu.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Currency implements Serializable {
    //币种名称
    private String s;
    //币种符号
    private String S;
    //价格（USD）
    private String u;
    //价格(BTC)
    private String b;
    //交易量(USD)
    private String v;
    //时间戳(毫秒)
    private String T;
    //	交易量(单位为当前币种)
    private String a;
    //报告交易量(单位为当前币种)
    private String ra;
    //报告交易量(USD)
    private String rv;
    //市值(USD)
    private String m;
    //24小时涨跌幅
    private String c;
    //24小时最高价
    private String h;

    public Currency(String s, String s1, String u, String b, String v, String t, String a, String ra, String rv, String m, String c, String h, String l, String cw, String hw, String lw, String cm, String hm, String lm, String ha, String la) {
        this.s = s;
        S = s1;
        this.u = u;
        this.b = b;
        this.v = v;
        T = t;
        this.a = a;
        this.ra = ra;
        this.rv = rv;
        this.m = m;
        this.c = c;
        this.h = h;
        this.l = l;
        this.cw = cw;
        this.hw = hw;
        this.lw = lw;
        this.cm = cm;
        this.hm = hm;
        this.lm = lm;
        this.ha = ha;
        this.la = la;
    }

    //24小时最低价
    private String l;
    //1周涨跌幅
    private String cw;
    //1周最高价
    private String hw;
    //1周最低价
    private String lw;
    //1月涨跌幅
    private String cm;
    //1月最高价
    private String hm;
    //1月最低价
    private String lm;
    //历史最高价
    private String ha;
    //历史最低价
    private String la;
    public Currency(){

    }
}
