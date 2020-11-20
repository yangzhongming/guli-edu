package com.guli.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.eduservice.entity.frontvo.CourseFrontVo;
import com.guli.eduservice.entity.frontvo.CourseWebVo;
import com.guli.eduservice.entity.vo.CourseInfoVo;
import com.guli.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String courseId);

    boolean deleteCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
