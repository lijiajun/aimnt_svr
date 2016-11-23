package com.ai.mnt.exec;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.mnt.business.base.BaseBusiness;

public class StartTask {

    private static Logger logger = Logger.getLogger(StartTask.class);
    public static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-core.xml");
    
    public static void main(String[] args) {
        
        Map<String, String> paraMap = parseParamters(args);
        if(validateParam(paraMap)) {
            //需求故障跟踪报表
            BaseBusiness baseBusiness = (BaseBusiness) ctx.getBean(paraMap.get("task"));
            baseBusiness.run();
        }
        
    }

    
    private static boolean validateParam(Map<String, String> paraMap) {
        if(paraMap.get("task") == null) {
            logger.error("任务[task]名称不能为空！");
            return false;
        }
        return true;
    }


    private static Map<String, String> parseParamters(String[] args) {
        Map<String, String> paraMap = new HashMap<>();
        for(String arg : args) {
            if(arg.indexOf("=") != -1) {
                String[] str = arg.split("=");
                paraMap.put(str[0], str[1]);
            }else {
                paraMap.put(arg, arg);
            }
        }
        return paraMap;
    }

}
