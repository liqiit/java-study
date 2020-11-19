package com.easypoi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * Title: UnicomCDR
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/19
 */
@ExcelTarget("unicomCDR")
public class UnicomCDR {
    @Excel(name = "startTime", fixedIndex = 0)
    private String startTime;
    @Excel(name = "msisdn", fixedIndex = 1)
    private String msisdn;
    @Excel(name = "imsi", fixedIndex = 2)
    private String imsi;
    @Excel(name = "ip", fixedIndex = 3)
    private String ip;
    @Excel(name = "mccmnc", fixedIndex = 4)
    private String mccmnc;
    @Excel(name = "rateGroup",fixedIndex = 5)
    private String rateGroup;
    @Excel(name = "totalFlow",fixedIndex = 6)
    private String totalFlow;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMccmnc() {
        return mccmnc;
    }

    public void setMccmnc(String mccmnc) {
        this.mccmnc = mccmnc;
    }

    public String getRateGroup() {
        return rateGroup;
    }

    public void setRateGroup(String rateGroup) {
        this.rateGroup = rateGroup;
    }

    public String getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(String totalFlow) {
        this.totalFlow = totalFlow;
    }
}
