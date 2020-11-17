package com.guli.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.commonutils.JwtUtils;
import com.guli.commonutils.MD5;
import com.guli.servicebase.exceptionhandler.GuliException;
import com.guli.ucenterservice.entity.UcenterMember;
import com.guli.ucenterservice.entity.vo.RegisterVo;
import com.guli.ucenterservice.mapper.UcenterMemberMapper;
import com.guli.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public String login(UcenterMember member) {
        // 获取登录手机号码和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        // 非空判断
        if(StringUtils.isEmpty(mobile)|| StringUtils.isEmpty(password)){
            throw new GuliException(20001,"用户名或密码为空");
        }

        // 判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember == null){
            throw new GuliException(20001,"手机号不存在");
        }

        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new  GuliException(20001,"登录密码校验错误");
        }

        if(mobileMember.getIsDisabled()){
            throw new  GuliException(20001,"登录失败");
        }

        String token = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        // 获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
           throw new GuliException(20001,"手机号号码或密码为空");
        }

        // 判断手机号是否已经注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0) {
            throw new GuliException(20001,"手机号码已注册");
        }

        // 注册账号
        UcenterMember member  = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }
}
