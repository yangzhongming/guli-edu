package com.guli.ucenterservice.controller;


import com.guli.commonutils.JwtUtils;
import com.guli.commonutils.R;
import com.guli.ucenterservice.entity.UcenterMember;
import com.guli.ucenterservice.entity.vo.RegisterVo;
import com.guli.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/educenter/member")
@EnableSwagger2
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private  UcenterMemberService memberService;

    @PostMapping("login")
    public  R login(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return  R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}

