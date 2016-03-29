package com.luckypets.config;


import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public BasicDataSource createDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/lucky_pets");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(1);
        return dataSource;
    }
    /*<!--data source DBCP (pools of connections) begins-->
    <bean id="dataSource"
    class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springbl"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
    <property name="initialSize" value="1"/>
    </bean>
    <!--data source DBCP ends-->*/
}
