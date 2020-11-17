package com.guli.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduChapter;
import com.guli.eduservice.entity.EduVideo;
import com.guli.eduservice.entity.chapter.ChapterVo;
import com.guli.eduservice.entity.chapter.VideoVo;
import com.guli.eduservice.mapper.EduChapterMapper;
import com.guli.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.eduservice.service.EduVideoService;
import com.guli.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        List<ChapterVo> finalList = new ArrayList<>();

        for (int i = 0; i <eduChapterList.size() ; i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);
            // 创建集合，用于封装章节的小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j <eduVideoList.size() ; j++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(j);
                // 判断小节里面的chapterid和章节里面的id是否一致
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count>0){
            throw  new GuliException(20001,"不能删除");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
