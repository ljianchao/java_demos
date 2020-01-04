package cn.jc.mvc.service;

import cn.jc.mvc.vo.Spitter;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface SpitterRepository {

    boolean sava(Spitter spitter);

    Spitter findByUsername(String username);

    Spitter findById(Long id);
}
