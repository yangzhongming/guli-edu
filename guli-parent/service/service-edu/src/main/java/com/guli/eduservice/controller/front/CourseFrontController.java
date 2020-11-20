package com.guli.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduChapter;
import com.guli.eduservice.entity.EduCourse;
import com.guli.eduservice.entity.chapter.ChapterVo;
import com.guli.eduservice.entity.frontvo.CourseFrontVo;
import com.guli.eduservice.entity.frontvo.CourseWebVo;
import com.guli.eduservice.service.EduChapterService;
import com.guli.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    // 1.条件查询带分页课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page,@PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        return R.ok().data(map);
    }

    // 2.查询课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        // 根据课程id,查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}
