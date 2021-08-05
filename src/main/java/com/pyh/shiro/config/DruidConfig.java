package com.pyh.shiro.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DruidDataSource druidDataSource()
    {
        return new DruidDataSource();
    }

//    后台监控 相当于web.xml   注册servlet需要用ServletRegistrationBean，没有xml文件来注册servlet了。
    @Bean
    public ServletRegistrationBean statViewServlet()
    {
        ServletRegistrationBean servletRegistrationBean=
                new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","pyh");//key值是固定的不能改。
        initParameters.put("loginPassword","6");

        //允许谁都可以访问：         value值写为空即可。
        initParameters.put("allow","");
        //禁止谁可以访问：        initParameters.put("xixi","ip 地址"); 直接写人和ip地址就可以了
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    //filters 同上 没有了web.xml 需要用这个类来添加filter
    @Bean
    public FilterRegistrationBean webStatFilter()
    {
        FilterRegistrationBean filterRegistrationBean=
                new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,String> initParameters=new HashMap<>();
        initParameters.put("exclusions","");//这些东西不进行统计
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
