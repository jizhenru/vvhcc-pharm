package com.vvcs.datasource;

public class DataSourceContextHandler {
	/** 
     * 线程局部变量，只为该线程服务 
     */  
    private final static ThreadLocal<String> contextHandler = new ThreadLocal<String>();  
      
    /** 
     * 设置该线程数据源id 
     * @param dataSourceType 
     */  
    public static void setDataSourceContext(String dataSourceType){  
        contextHandler.set(dataSourceType);  
    }  
    /** 
     * 得到该线程数据源id 
     * @return 
     */  
    public static String getDataSourceContext(){  
        return contextHandler.get();  
    }  
    /** 
     * 清除该线程的数据源变量值 
     */  
    public static void clearDataSourceContext(){  
        contextHandler.remove();  
    }  
}
