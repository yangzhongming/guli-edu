package com.guli.eduservice.service;

import com.guli.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.eduservice.entity.vo.CourseInfoVo;
import com.guli.eduservice.entity.vo.CoursePublishVo;

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
}
