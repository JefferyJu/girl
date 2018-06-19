package com.imooc.girl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: JefferyJu
 * @date: 2018/6/19
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /**
     * 通过年龄来查询
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
