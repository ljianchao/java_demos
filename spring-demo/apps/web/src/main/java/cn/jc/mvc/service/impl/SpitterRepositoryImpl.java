package cn.jc.mvc.service.impl;

import cn.jc.mvc.service.SpitterRepository;
import cn.jc.mvc.vo.Spitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class SpitterRepositoryImpl implements SpitterRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpitterRepositoryImpl.class);

    @Override
    public boolean sava(Spitter spitter) {
        return true;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = new Spitter(null, null, username, null);
        return spitter;
    }

    @Override
    public Spitter findById(Long id) {
        LOGGER.info("调用根据id获取spitter的service.");
        Spitter spitter = new Spitter("Log", "Record", "Log Record", null);
        spitter.setId(id);
        return spitter;
    }
}
