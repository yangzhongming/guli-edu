package com.guli.eduservice.controller;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonutils.R;
import com.guli.eduservice.entity.EduTeacher;
import com.guli.eduservice.entity.vo.TeacherQuery;
import com.guli.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

/*    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }*/
    @ApiOperation(value = "讲师列表")
    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("item",list);
    }

/*    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }*/

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public  R removeById(@PathVariable String id){
        teacherService.removeById(id);
        return R.ok();
    }

    /**
     * 分页查询讲师
     * @param current
     * @param limit
     * @return
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 分页查询附带分页
     * @param current
     * @param limit
     * @return
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        // 构建查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }

        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        // 排序
        wrapper.orderByDesc("gmt_create");
        // 调用方法实现条件分页查询
        teacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    // 添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    /**
     * 根据ID进行查询
     */
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    /**
     * 根据ID进行更新
     * @param eduTeacher
     * @return
     */
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return R.ok();
        }  else {
            return R.error();
        }
    }
}

