package io.pandora.mall.search.service;

import io.pandora.mall.search.domain.TestBean;

import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/31
 */
public interface EsProductService {

    Iterable<TestBean> findAll();

    void save(List<TestBean> list);

    void save(TestBean bean);

    List<TestBean> findByName(String text);

    List<TestBean> findByNameOrDesc(String name);

}
