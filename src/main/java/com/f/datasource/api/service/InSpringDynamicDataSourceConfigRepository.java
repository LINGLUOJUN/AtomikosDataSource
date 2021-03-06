package com.f.datasource.api.service;

import com.f.datasource.api.config.DynamicDataSourceConfigRepository;
import com.f.datasource.api.config.InSpringDynamicDataSourceConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rebysfu@gmail.com
 * @description：
 * @create 2018-09-20 下午1:49
 **/
public class InSpringDynamicDataSourceConfigRepository implements DynamicDataSourceConfigRepository<InSpringDynamicDataSourceConfig>, BeanPostProcessor {
    private Map<String, InSpringDynamicDataSourceConfig> configMap = new HashMap<>();

    @Override
    public List<InSpringDynamicDataSourceConfig> findAll() {
        return new ArrayList<>(configMap.values());
    }

    @Override
    public InSpringDynamicDataSourceConfig findById(String dataSourceId) {
        return configMap.get(dataSourceId);
    }

    @Override
    public InSpringDynamicDataSourceConfig add(InSpringDynamicDataSourceConfig config) {
        return configMap.put(config.getId(), config);
    }

    @Override
    public InSpringDynamicDataSourceConfig remove(String dataSourceId) {
        return configMap.remove(dataSourceId);
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof DataSource) {
            InSpringDynamicDataSourceConfig config = new InSpringDynamicDataSourceConfig();
            config.setId(s);
            config.setBeanName(s);
            config.setName(s);
            add(config);
        }
        return o;
    }
}
