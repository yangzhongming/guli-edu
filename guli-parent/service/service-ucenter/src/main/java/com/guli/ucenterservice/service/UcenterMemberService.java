package com.guli.ucenterservice.service;

import com.guli.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    // 登录校验
    String login(UcenterMember member);

    void register(RegisterVo registerVo);
}
