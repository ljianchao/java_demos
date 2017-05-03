package cn.jc.mvc.service.impl;

import cn.jc.mvc.service.SpitterRepository;
import cn.jc.mvc.vo.Spitter;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class SpitterRepositoryImpl implements SpitterRepository {
    @Override
    public boolean sava(Spitter spitter) {
        return true;
    }

    @Override
    public Spitter findByUsername(String username) {
        Spitter spitter = new Spitter(null, null, username, null);
        return spitter;
    }
}
