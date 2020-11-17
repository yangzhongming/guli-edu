package com.guli.educms.controller;




import com.guli.commonutils.R;
import com.guli.educms.entity.CrmBanner;
import com.guli.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    // 查看所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list",list);
    }

}

