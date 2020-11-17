package com.guli.eduservice.controller;


import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduChapter;
import com.guli.eduservice.entity.EduVideo;
import com.guli.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    // 添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        videoService.save(video);
        return R.ok();
    }

    // 删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return  R.ok();
    }

    // 根据ID查看小节
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        EduVideo video = videoService.getById(videoId);
        return R.ok().data("video",video);
    }

    // 修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }
}

