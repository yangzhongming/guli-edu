package com.guli.eduservice.service;

import com.guli.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-14
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
