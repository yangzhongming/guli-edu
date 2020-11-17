package com.guli.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.excel.SubjectData;
import com.guli.eduservice.service.EduSubjectService;
import com.guli.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData ==null) {
            throw  new GuliException(20001,"文件数据为空");
        }

        EduSubject existOneSubject = this.existOneSubject(subjectService,subjectData.getOneSubjectName());
        if (existOneSubject==null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);

        }

        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /*
     判断一级分类不能重复添加
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    /*
    判断二级分类不能重复添加
   */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
}
