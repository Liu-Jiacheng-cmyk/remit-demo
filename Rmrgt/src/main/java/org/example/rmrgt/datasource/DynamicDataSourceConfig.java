// common/src/main/java/org/example/common/datasource/DynamicDataSourceConfig.java
package org.example.rmrgt.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DynamicDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DynamicRoutingDataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        // 获取 bdus 的数量
        int bdusCount = Integer.parseInt(env.getProperty("bdus.count", "2"));
        Map<Object, Object> dataSourceMap = new HashMap<>();

        // 动态加载所有 bdus 数据源
        for (int i = 1; i <= bdusCount; i++) {
            String bdusName = "bdus" + i;
            String prefix = "bdus.datasources." + bdusName;

            // 读取 DataSourceProperties
            DataSourceProperties dataSourceProperties = new DataSourceProperties();
            dataSourceProperties.setUrl(env.getProperty(prefix + ".url"));
            dataSourceProperties.setUsername(env.getProperty(prefix + ".username"));
            dataSourceProperties.setPassword(env.getProperty(prefix + ".password"));
            dataSourceProperties.setDriverClassName(env.getProperty(prefix + ".driver-class-name"));

            // 初始化 DataSource
            DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
            dataSourceMap.put(bdusName, dataSource);
        }

        // 设置默认数据源为 bdus1
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSourceMap.get("bdus1"));
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicRoutingDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource);
        // 设置 MyBatis 的 Mapper XML 位置
        sessionFactory.setMapperLocations(
                new org.springframework.core.io.support.PathMatchingResourcePatternResolver()
                        .getResources("classpath:domain/mapper/*.xml"));
        return sessionFactory.getObject();
    }

    // 定义动态路由数据源类
    public static class DynamicRoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DynamicDataSourceContextHolder.getDataSourceKey();
        }
    }
}
