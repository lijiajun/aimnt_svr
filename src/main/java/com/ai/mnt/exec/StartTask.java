package com.ai.mnt.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.mnt.business.base.BaseBusiness;
import com.ai.mnt.business.report.BizDataSyncBusiness;

public class StartTask {

    
    public static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-core.xml");
    
    public static void main(String[] args) {
        
        //需求故障跟踪报表
        BaseBusiness bizDataSyncBusiness = ctx.getBean(BizDataSyncBusiness.class);
        bizDataSyncBusiness.run();
        
    }

}
