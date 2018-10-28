package com.imooc.user.controller;

import com.imooc.user.VO.ResultVO;
import com.imooc.user.constant.CookieConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.service.UserService;
import com.imooc.user.utils.CookieUtil;
import com.imooc.user.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        UserInfo userInfo = userService.findByOpenid(openid);
        if(null == userInfo){
          return  ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if(userInfo.getRole() != RoleEnum.BUYER.getCode()){
          return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(null != cookie){
            return  ResultVOUtil.success();
        }
        UserInfo userInfo = userService.findByOpenid(openid);
        if(null == userInfo){
            return  ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if(userInfo.getRole() != RoleEnum.SELLER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        String token = UUID.randomUUID().toString();
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
