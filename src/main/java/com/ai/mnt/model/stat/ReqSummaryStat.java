package com.ai.mnt.model.stat;

public class ReqSummaryStat {

    private String baseName;
    private String prodName;
    private int baseTotalCount;
    private int sccbCount;
    private int reqAnalyCount;
    private int devCount;
    private int qaCount;
    private int prodOrder;

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getBaseTotalCount() {
        return this.sccbCount + this.reqAnalyCount + this.devCount + this.qaCount;
    }

    public void setBaseTotalCount(int baseTotalCount) {
        this.baseTotalCount = baseTotalCount;
    }

    public int getSccbCount() {
        return sccbCount;
    }

    public void setSccbCount(int sccbCount) {
        this.sccbCount = sccbCount;
    }

    public int getReqAnalyCount() {
        return reqAnalyCount;
    }

    public void setReqAnalyCount(int reqAnalyCount) {
        this.reqAnalyCount = reqAnalyCount;
    }

    public int getDevCount() {
        return devCount;
    }

    public void setDevCount(int devCount) {
        this.devCount = devCount;
    }

    public int getQaCount() {
        return qaCount;
    }

    public void setQaCount(int qaCount) {
        this.qaCount = qaCount;
    }

    public int getProdOrder() {
        return prodOrder;
    }

    public void setProdOrder(int prodOrder) {
        this.prodOrder = prodOrder;
    }

    @Override
    public String toString() {
        return "ReqSummaryStat [baseName=" + baseName + ", prodName="
                + prodName + ", baseTotalCount=" + baseTotalCount
                + ", sccbCount=" + sccbCount + ", reqAnalyCount="
                + reqAnalyCount + ", devCount=" + devCount + ", qaCount="
                + qaCount + ", prodOrder=" + prodOrder + "]";
    }

}
