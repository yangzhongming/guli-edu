package com.guli.eduservice.controller;


import com.guli.commonutils.R;
import com.guli.eduservice.entity.subject.OneSubject;
import com.guli.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return  R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        //上传过来excel文件
        List<OneSubject> list = subjectService.getAllSubject();
        return  R.ok().data("list",list);
    }

}

