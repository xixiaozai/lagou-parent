package com.xu.dao;

import com.xu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author xushiwei
 * @Date 2022/11/8 15:35
 * @Version 1.0
 */
public interface ResumeDao extends JpaRepository<Resume, Long> {
}
