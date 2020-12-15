package com.example.demo.repo;

import com.example.demo.domain.Stu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:17
 */
public interface StuRepo extends ReactiveCrudRepository<Stu, Long> {

}
