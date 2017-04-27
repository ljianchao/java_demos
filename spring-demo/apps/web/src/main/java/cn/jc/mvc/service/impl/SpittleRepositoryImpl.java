package cn.jc.mvc.service.impl;

import cn.jc.mvc.service.SpittleRepository;
import cn.jc.mvc.vo.Spittle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> list = new ArrayList<>(count);

        for (int i = 0; i < count; i++){
            list.add(new Spittle(String.valueOf(i), new Date(System.currentTimeMillis()), (double) i, (double)i));
        }
        return list;
    }
}
