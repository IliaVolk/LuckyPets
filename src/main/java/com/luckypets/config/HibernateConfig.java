package com.luckypets.config;


import com.luckypets.dao.ClinicCommentDao;
import com.luckypets.dao.ClinicDao;
import com.luckypets.dao.UserDao;
import com.luckypets.dao.impl.ClinicCommentDaoImpl;
import com.luckypets.dao.impl.ClinicDaoImpl;
import com.luckypets.dao.impl.UserDaoImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public AnnotationSessionFactoryBean sessionFactory(BasicDataSource dataSource) {
        AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(
                new String[]{
                        "com"
                }
        );//TODO: write better package name
        Properties properties = new Properties();
        properties.getProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
        factory.setHibernateProperties(properties);

        return factory;
    }

    @Bean
    public UserDao userDao(SessionFactory sessionFactory) {
        UserDaoImpl userDao = new UserDaoImpl();
        //userDao.setSessionFactory(sessionFactory);
        return userDao;
    }

    @Bean
    public ClinicDao clinicDao(SessionFactory sessionFactory) {
        ClinicDaoImpl clinicDao = new ClinicDaoImpl();
        //clinicDao.setSessionFactory(sessionFactory);
        return clinicDao;
    }

    @Bean
    ClinicCommentDao clinicCommentDao(SessionFactory sessionFactory) {
        ClinicCommentDaoImpl clinicCommentDao = new ClinicCommentDaoImpl();
        //clinicCommentDao.setSessionFactory(sessionFactory);
        return clinicCommentDao;
    }

    /*
    <bean id="sessionFactory"
          class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan"><!--also can be initialized as
        <property name="packagesToScan" value="package"/>-->
            <list>
                <value>com.springapp.entities</value>
            </list>
        </property>
        <property name="hibernateProperties">
            <props>
                <prop key="dialect">org.hibernate.dialect.MySQL5Dialect</prop>
            </props>
        </property>
    </bean>
     */
}
