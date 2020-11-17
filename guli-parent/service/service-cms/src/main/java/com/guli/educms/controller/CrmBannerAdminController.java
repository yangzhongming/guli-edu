package com.guli.educms.controller;


import com.guli.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/educms/crmbanner")
@CrossOrigin
public class CrmBannerAdminController {

    @GetMapping("getAllBanner")
    public R getAllBanner(){
        return R.ok();
    }
}

