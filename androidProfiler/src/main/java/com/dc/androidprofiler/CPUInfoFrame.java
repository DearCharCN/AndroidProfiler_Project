package com.dc.androidprofiler;

public class CPUInfoFrame {
    public void set(long totaljiffie,long usagejiffie){
        this.totaljiffie = totaljiffie;
        this.usagejiffie = usagejiffie;
    }
    //总时间数
     public long totaljiffie;
    //空闲时间数
     public long usagejiffie;


}
