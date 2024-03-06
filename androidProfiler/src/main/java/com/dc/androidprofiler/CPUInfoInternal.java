package com.dc.androidprofiler;

public class CPUInfoInternal {
    public void set(long totaljiffie,long usagejiffie){
        this.totaljiffie = totaljiffie;
        this.usagejiffie = usagejiffie;
    }
    //总时间数
    public long totaljiffie;
    //空闲时间数
    public long usagejiffie;

    public static CPUInfoInternal getInternal(CPUInfoFrame oldFrame,CPUInfoFrame newFrame){
        CPUInfoInternal result = new CPUInfoInternal();
        long totaljiffie = newFrame.totaljiffie - oldFrame.totaljiffie;
        long usagejiffie = newFrame.usagejiffie - oldFrame.usagejiffie;
        result.set(totaljiffie,usagejiffie);
        return result;
    }
}
