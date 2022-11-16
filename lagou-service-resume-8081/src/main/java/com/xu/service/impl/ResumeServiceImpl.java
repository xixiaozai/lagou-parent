package com.xu.service.impl;

import com.xu.dao.ResumeDao;
import com.xu.pojo.Resume;
import com.xu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @Author xushiwei
 * @Date 2022/11/8 15:38
 * @Version 1.0
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;
    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setIsDefault(1);
        Example<Resume> of = Example.of(resume);
        return resumeDao.findOne(of).get();

    }
}
