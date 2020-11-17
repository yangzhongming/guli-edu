package com.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduChapter;
import com.guli.eduservice.entity.EduCourse;
import com.guli.eduservice.entity.EduCourseDescription;
import com.guli.eduservice.entity.vo.CourseInfoVo;
import com.guli.eduservice.entity.vo.CoursePublishVo;
import com.guli.eduservice.mapper.EduCourseMapper;
import com.guli.eduservice.service.EduChapterService;
import com.guli.eduservice.service.EduCourseDescriptionService;
import com.guli.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.eduservice.service.EduVideoService;
import com.guli.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert ==0) {
            throw new GuliException(20001,"添加课程信息失败");
        }

        String cid = eduCourse.getId();
        // 2 添加课程描述
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(cid);
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.save(courseDescription);

        return  cid;
    }
    //根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        // 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update==0){
            throw  new GuliException(20001,"修改课程信息失败");
        }
        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(courseId);
        return coursePublishVo;
    }

    @Override
    public boolean deleteCourse(String courseId) {
        int flag = baseMapper.deleteCourse(courseId);
        if(flag>0) {
            return true;
        }
        return false;
    }

}
