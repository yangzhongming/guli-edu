package com.guli.eduservice.controller;


import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduChapter;
import com.guli.eduservice.entity.chapter.ChapterVo;
import com.guli.eduservice.service.EduChapterService;
import com.guli.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVo = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",chapterVo);
    }
    // 根据id查询章节
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    // 修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    // 根据ID删除章节
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId ){
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag){
            return  R.ok();
        } else {
            return R.error();
        }
    }
}

