package com.guli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.excel.SubjectData;
import com.guli.eduservice.entity.subject.OneSubject;
import com.guli.eduservice.entity.subject.TwoSubject;
import com.guli.eduservice.listener.SubjectExcelListener;
import com.guli.eduservice.mapper.EduSubjectMapper;
import com.guli.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try  {
            // 文件流输入
            InputStream in = file.getInputStream();
            // 读取文件
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        // 查询一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 查询二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        List<OneSubject> eduSubjectList = new ArrayList<>();

        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            eduSubjectList.add(oneSubject);

            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j <twoSubjectList.size() ; j++) {
                EduSubject twoEduSubject = twoSubjectList.get(j);
                if (twoEduSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }

            }
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return eduSubjectList;
    }
}
