package io.pandora.mall.search.dao;

import io.pandora.mall.search.domain.TestBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Created by mr_zhou on 2020/12/31
 */
public interface TestDao extends ElasticsearchRepository<TestBean,Long> {

    List<TestBean> findByName(String name);

    List<TestBean> findByNameOrDesc(String text);
}
