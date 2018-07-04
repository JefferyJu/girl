package com.imooc.service;

import com.imooc.controller.GirlApplicationTests;
import com.imooc.domain.Girl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author: JefferyJu
 * @date: 2018/7/4
 */
public class GirlServiceTest extends GirlApplicationTests {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest() {
        Girl girl = girlService.findOne(10);
        Assert.assertEquals(new Integer(9),girl.getAge());
    }
}