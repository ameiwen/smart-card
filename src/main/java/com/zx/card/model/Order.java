package com.zx.card.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
* generator code
*/
public class Order {

    private Long id;

    private Integer storeid;

    private Long userid;

    private Long oneuserid;

    private Long twouserid;

    private String orderno;

    private BigDecimal costs;

    private BigDecimal ordermoney;

    private BigDecimal precommision;

    private BigDecimal commision;

    private String isfrommobile;

    private String storeostatus;

    private String parentno;

    private String checkstatus;

    private BigDecimal firstcommision;

    private BigDecimal secondcommision;

    private BigDecimal thirdcommision;

    private String promotionname;

    private Integer shopid;

    private String shopname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishtime;

    private String surename;

    private String promotionid;

    private BigDecimal usercommision;

    private String unionid;

    private BigDecimal commosionrate;

    private String isdelete;

    private String invalidreason;

    private String balancestatus;

    private String userlevel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ordertime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date balancetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date prebalancetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date suretime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private String goodsname;

    private String goodspicture;

    private String username;

    private Long tzid;

    private BigDecimal tzcommision;

    private Long threeuserid;

    private Long itemid;

    private String iswqorder;

    private BigDecimal wqmoney;

    private Integer valicode;

    private String isshoudang;

    private String ispinggou;

    private String activityid;

    private String isactivity;

    private BigDecimal extrmoney;

    private String subunionid;

    private String fromsource;

    private BigDecimal finalrate;

    private Integer goodsnum;

    private Long uptzid;

    private BigDecimal uptzcommission;

    private Long upuptzid;

    private BigDecimal upuptzcommission;

    private String goodscatename;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getOneuserid() {
        return oneuserid;
    }

    public void setOneuserid(Long oneuserid) {
        this.oneuserid = oneuserid;
    }

    public Long getTwouserid() {
        return twouserid;
    }

    public void setTwouserid(Long twouserid) {
        this.twouserid = twouserid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    public BigDecimal getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(BigDecimal ordermoney) {
        this.ordermoney = ordermoney;
    }

    public BigDecimal getPrecommision() {
        return precommision;
    }

    public void setPrecommision(BigDecimal precommision) {
        this.precommision = precommision;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getIsfrommobile() {
        return isfrommobile;
    }

    public void setIsfrommobile(String isfrommobile) {
        this.isfrommobile = isfrommobile;
    }

    public String getStoreostatus() {
        return storeostatus;
    }

    public void setStoreostatus(String storeostatus) {
        this.storeostatus = storeostatus;
    }

    public String getParentno() {
        return parentno;
    }

    public void setParentno(String parentno) {
        this.parentno = parentno;
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    public BigDecimal getFirstcommision() {
        return firstcommision;
    }

    public void setFirstcommision(BigDecimal firstcommision) {
        this.firstcommision = firstcommision;
    }

    public BigDecimal getSecondcommision() {
        return secondcommision;
    }

    public void setSecondcommision(BigDecimal secondcommision) {
        this.secondcommision = secondcommision;
    }

    public BigDecimal getThirdcommision() {
        return thirdcommision;
    }

    public void setThirdcommision(BigDecimal thirdcommision) {
        this.thirdcommision = thirdcommision;
    }

    public String getPromotionname() {
        return promotionname;
    }

    public void setPromotionname(String promotionname) {
        this.promotionname = promotionname;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getPromotionid() {
        return promotionid;
    }

    public void setPromotionid(String promotionid) {
        this.promotionid = promotionid;
    }

    public BigDecimal getUsercommision() {
        return usercommision;
    }

    public void setUsercommision(BigDecimal usercommision) {
        this.usercommision = usercommision;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public BigDecimal getCommosionrate() {
        return commosionrate;
    }

    public void setCommosionrate(BigDecimal commosionrate) {
        this.commosionrate = commosionrate;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public String getInvalidreason() {
        return invalidreason;
    }

    public void setInvalidreason(String invalidreason) {
        this.invalidreason = invalidreason;
    }

    public String getBalancestatus() {
        return balancestatus;
    }

    public void setBalancestatus(String balancestatus) {
        this.balancestatus = balancestatus;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getBalancetime() {
        return balancetime;
    }

    public void setBalancetime(Date balancetime) {
        this.balancetime = balancetime;
    }

    public Date getPrebalancetime() {
        return prebalancetime;
    }

    public void setPrebalancetime(Date prebalancetime) {
        this.prebalancetime = prebalancetime;
    }

    public Date getSuretime() {
        return suretime;
    }

    public void setSuretime(Date suretime) {
        this.suretime = suretime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodspicture() {
        return goodspicture;
    }

    public void setGoodspicture(String goodspicture) {
        this.goodspicture = goodspicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTzid() {
        return tzid;
    }

    public void setTzid(Long tzid) {
        this.tzid = tzid;
    }

    public BigDecimal getTzcommision() {
        return tzcommision;
    }

    public void setTzcommision(BigDecimal tzcommision) {
        this.tzcommision = tzcommision;
    }

    public Long getThreeuserid() {
        return threeuserid;
    }

    public void setThreeuserid(Long threeuserid) {
        this.threeuserid = threeuserid;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getIswqorder() {
        return iswqorder;
    }

    public void setIswqorder(String iswqorder) {
        this.iswqorder = iswqorder;
    }

    public BigDecimal getWqmoney() {
        return wqmoney;
    }

    public void setWqmoney(BigDecimal wqmoney) {
        this.wqmoney = wqmoney;
    }

    public Integer getValicode() {
        return valicode;
    }

    public void setValicode(Integer valicode) {
        this.valicode = valicode;
    }

    public String getIsshoudang() {
        return isshoudang;
    }

    public void setIsshoudang(String isshoudang) {
        this.isshoudang = isshoudang;
    }

    public String getIspinggou() {
        return ispinggou;
    }

    public void setIspinggou(String ispinggou) {
        this.ispinggou = ispinggou;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public String getIsactivity() {
        return isactivity;
    }

    public void setIsactivity(String isactivity) {
        this.isactivity = isactivity;
    }

    public BigDecimal getExtrmoney() {
        return extrmoney;
    }

    public void setExtrmoney(BigDecimal extrmoney) {
        this.extrmoney = extrmoney;
    }

    public String getSubunionid() {
        return subunionid;
    }

    public void setSubunionid(String subunionid) {
        this.subunionid = subunionid;
    }

    public String getFromsource() {
        return fromsource;
    }

    public void setFromsource(String fromsource) {
        this.fromsource = fromsource;
    }

    public BigDecimal getFinalrate() {
        return finalrate;
    }

    public void setFinalrate(BigDecimal finalrate) {
        this.finalrate = finalrate;
    }

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }

    public Long getUptzid() {
        return uptzid;
    }

    public void setUptzid(Long uptzid) {
        this.uptzid = uptzid;
    }

    public BigDecimal getUptzcommission() {
        return uptzcommission;
    }

    public void setUptzcommission(BigDecimal uptzcommission) {
        this.uptzcommission = uptzcommission;
    }

    public Long getUpuptzid() {
        return upuptzid;
    }

    public void setUpuptzid(Long upuptzid) {
        this.upuptzid = upuptzid;
    }

    public BigDecimal getUpuptzcommission() {
        return upuptzcommission;
    }

    public void setUpuptzcommission(BigDecimal upuptzcommission) {
        this.upuptzcommission = upuptzcommission;
    }

    public String getGoodscatename() {
        return goodscatename;
    }

    public void setGoodscatename(String goodscatename) {
        this.goodscatename = goodscatename;
    }
}
