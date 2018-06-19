package com.imooc.service;

import com.imooc.domain.Girl;
import com.imooc.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author: JefferyJu
 * @date: 2018/6/19
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional(rollbackFor=Exception.class)
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(58);
        girlRepository.save(girlA);


        Girl girlB = new Girl();
        girlB.setCupSize("BB");
        girlB.setAge(59);
        girlRepository.save(girlB);
    }
}
