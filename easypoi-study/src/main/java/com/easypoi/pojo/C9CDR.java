package com.easypoi.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * Title: C9CDR
 * Description: 本类采用getter setter方式，因为easypoi通过反射原理对属性进行赋值，
 *              lombok是在编译阶段进行getter setter的增加，会有先后顺序，导致对
 *              属性赋值无法找到setter方法
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/19
 */

@ExcelTarget("c9CDR")
public class C9CDR {
    @Excel(name = "\"ANumber\"")
    private String aNumber;
    @Excel(name = "\"CustomerID\"")
    private String customerId;
    @Excel(name = "\"Customer\"")
    private String customer;
    @Excel(name = "\"UserID\"")
    private String userId;
    @Excel(name = "\"User\"")
    private String user;
    @Excel(name = "\"CallID\"")
    private String callId;
    @Excel(name = "\"CallType\"")
    private String callType;

    @Excel(name = "\"AType\"")
    private String aType;
    @Excel(name = "\"ADescription\"")
    private String aDescription;
    @Excel(name = "\"AConnect\"")
    private String startTime;
    @Excel(name = "\"AEnd\"")
    private String endTime;
    @Excel(name = "\"BNumber\"")
    private String bNumber;
    @Excel(name = "\"BType\"")
    private String bType;
    @Excel(name = "\"BDescription\"")
    private String bDescription;
    @Excel(name = "\"BConnect\"")
    private String bConnect;
    @Excel(name = "\"BEnd\"")
    private String bEnd;
    @Excel(name = "\"CreateTime\"")
    private String createTime;
    @Excel(name = "\"Seconds\"")
    private String totalFlow;
    @Excel(name = "\"RetailCost\"")
    private String retailCost;
    @Excel(name = "\"AgentCost\"")
    private String agentCost;
    @Excel(name = "\"Reference\"")
    private String reference;
    @Excel(name = "\"MnoID\"")
    private String mnoId;
    @Excel(name = "\"AgentID\"")
    private String agentId;
    @Excel(name = "\"TransactionID\"")
    private String transactionId;
    @Excel(name = "\"AgentTransID\"")
    private String agentTransId;
    @Excel(name = "\"BundleID\"")
    private String bundleId;
    @Excel(name = "\"SubscriptionId\"")
    private String subscriptionId;
    @Excel(name = "\"IMSI\"")
    private String imsi;

    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaDescription() {
        return aDescription;
    }

    public void setaDescription(String aDescription) {
        this.aDescription = aDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getbNumber() {
        return bNumber;
    }

    public void setbNumber(String bNumber) {
        this.bNumber = bNumber;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getbDescription() {
        return bDescription;
    }

    public void setbDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    public String getbConnect() {
        return bConnect;
    }

    public void setbConnect(String bConnect) {
        this.bConnect = bConnect;
    }

    public String getbEnd() {
        return bEnd;
    }

    public void setbEnd(String bEnd) {
        this.bEnd = bEnd;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(String totalFlow) {
        this.totalFlow = totalFlow;
    }

    public String getRetailCost() {
        return retailCost;
    }

    public void setRetailCost(String retailCost) {
        this.retailCost = retailCost;
    }

    public String getAgentCost() {
        return agentCost;
    }

    public void setAgentCost(String agentCost) {
        this.agentCost = agentCost;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMnoId() {
        return mnoId;
    }

    public void setMnoId(String mnoId) {
        this.mnoId = mnoId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAgentTransId() {
        return agentTransId;
    }

    public void setAgentTransId(String agentTransId) {
        this.agentTransId = agentTransId;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
}
