package com.easypoi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * Title: SyniverseCDR
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/19
 */
@ExcelTarget("syniverseCDR")
public class SyniverseCDR {
    @Excel(name = "usage_e_time")
    private String startTime;
    @Excel(name = "home_mno_name")
    private String homeMnoName;
    @Excel(name = "imsi")
    private String imsi;
    @Excel(name = "msisdn")
    private String msisdn;
    @Excel(name = "imei")
    private String imei;
    @Excel(name = "subscriber_group_name")
    private String subscriberGroupName;
    @Excel(name = "retail_plan")
    private String retailPlan;
    @Excel(name = "retail_plan_product_code")
    private String retailPlanProductCode;
    @Excel(name = "wholesale_plan")
    private String wholeSalePlan;
    @Excel(name = "apn")
    private String apn;
    @Excel(name = "visited_mno_name")
    private String visitedMnoName;
    @Excel(name = "visited_mno_country")
    private String visitedMnoCountry;
    @Excel(name = "visited_network_mccmnc")
    private String visitedNetworkMccMnc;
    @Excel(name = "uplink_usage")
    private String uplinkUsage;
    @Excel(name = "downlink_usage")
    private String downlinkUsage;
    @Excel(name = "drop_uplink_usage")
    private String dropUplinkUsage;
    @Excel(name = "drop_downlink_usage")
    private String dropDownlinkUsage;
    @Excel(name = "total_usage")
    private String totalFlow;
    @Excel(name = "end_user_ip")
    private String endUserIp;
    @Excel(name = "ggsn_ip")
    private String ggsnIp;
    @Excel(name = "rat_type")
    private String ratType;
    @Excel(name = "gtp_session_id")
    private String gtpSessionId;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getHomeMnoName() {
        return homeMnoName;
    }

    public void setHomeMnoName(String homeMnoName) {
        this.homeMnoName = homeMnoName;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSubscriberGroupName() {
        return subscriberGroupName;
    }

    public void setSubscriberGroupName(String subscriberGroupName) {
        this.subscriberGroupName = subscriberGroupName;
    }

    public String getRetailPlan() {
        return retailPlan;
    }

    public void setRetailPlan(String retailPlan) {
        this.retailPlan = retailPlan;
    }

    public String getRetailPlanProductCode() {
        return retailPlanProductCode;
    }

    public void setRetailPlanProductCode(String retailPlanProductCode) {
        this.retailPlanProductCode = retailPlanProductCode;
    }

    public String getWholeSalePlan() {
        return wholeSalePlan;
    }

    public void setWholeSalePlan(String wholeSalePlan) {
        this.wholeSalePlan = wholeSalePlan;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getVisitedMnoName() {
        return visitedMnoName;
    }

    public void setVisitedMnoName(String visitedMnoName) {
        this.visitedMnoName = visitedMnoName;
    }

    public String getVisitedMnoCountry() {
        return visitedMnoCountry;
    }

    public void setVisitedMnoCountry(String visitedMnoCountry) {
        this.visitedMnoCountry = visitedMnoCountry;
    }

    public String getVisitedNetworkMccMnc() {
        return visitedNetworkMccMnc;
    }

    public void setVisitedNetworkMccMnc(String visitedNetworkMccMnc) {
        this.visitedNetworkMccMnc = visitedNetworkMccMnc;
    }

    public String getUplinkUsage() {
        return uplinkUsage;
    }

    public void setUplinkUsage(String uplinkUsage) {
        this.uplinkUsage = uplinkUsage;
    }

    public String getDownlinkUsage() {
        return downlinkUsage;
    }

    public void setDownlinkUsage(String downlinkUsage) {
        this.downlinkUsage = downlinkUsage;
    }

    public String getDropUplinkUsage() {
        return dropUplinkUsage;
    }

    public void setDropUplinkUsage(String dropUplinkUsage) {
        this.dropUplinkUsage = dropUplinkUsage;
    }

    public String getDropDownlinkUsage() {
        return dropDownlinkUsage;
    }

    public void setDropDownlinkUsage(String dropDownlinkUsage) {
        this.dropDownlinkUsage = dropDownlinkUsage;
    }

    public String getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(String totalFlow) {
        this.totalFlow = totalFlow;
    }

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }

    public String getGgsnIp() {
        return ggsnIp;
    }

    public void setGgsnIp(String ggsnIp) {
        this.ggsnIp = ggsnIp;
    }

    public String getRatType() {
        return ratType;
    }

    public void setRatType(String ratType) {
        this.ratType = ratType;
    }

    public String getGtpSessionId() {
        return gtpSessionId;
    }

    public void setGtpSessionId(String gtpSessionId) {
        this.gtpSessionId = gtpSessionId;
    }
}
