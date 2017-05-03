package cn.jc.mvc.service;

import cn.jc.mvc.vo.Spittle;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);
}
