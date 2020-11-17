package com.guli.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduCourse;
import com.guli.eduservice.entity.EduTeacher;
import com.guli.eduservice.entity.vo.CourseInfoVo;
import com.guli.eduservice.entity.vo.CoursePublishVo;
import com.guli.eduservice.entity.vo.CourseQuery;
import com.guli.eduservice.entity.vo.TeacherQuery;
import com.guli.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    // 添加课程信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);
        return  R.ok().data("courseId",id);
    }

    // 根据课程ID查询课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return  R.ok().data("courseInfoVo",courseInfoVo);
    }

    // 修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return  R.ok();
    }

    // 根据ID查询课程信息
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(courseId);
        return  R.ok().data("publihCourse",coursePublishVo);
    }

    // 查询课程列表
    @GetMapping("getListCourse/")
    public R getListCourse(){
        List<EduCourse> listCourse = courseService.list(null);
        return  R.ok().data("listCourse",listCourse);
    }

    /**
     * 分页查询附带分页
     * @param current
     * @param limit
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) CourseQuery courseQuery){
        // 创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        // 构建查询条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = courseQuery.getTitle();
        Integer status = courseQuery.getStatus();

        if(!StringUtils.isEmpty(title)){
            wrapper.like("name",title);
        }

        if(!StringUtils.isEmpty(status)){
            wrapper.eq("level",status);
        }

        // 排序
        wrapper.orderByDesc("gmt_create");
        // 调用方法实现条件分页查询
        courseService.page(pageCourse,wrapper);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    // 发布课程
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }

    // 删除课程
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId){
       boolean flag =  courseService.deleteCourse(courseId);
       if(flag){
           return R.ok();
       } else {
           return R.error();
       }
    }
}

