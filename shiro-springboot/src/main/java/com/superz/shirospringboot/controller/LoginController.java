package com.superz.shirospringboot.controller;

import com.superz.shirospringboot.common.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author zhang.chao
 * @date 2021/1/3
 */
@RestController
@Slf4j
public class LoginController {

    @PostMapping("/login")
    public ResponseResult<?> login(String username, String password) {
        ResponseResult<Object> result = new ResponseResult<>();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            result.setCode(0);
            result.setMessage("success");
            result.setData(Collections.singletonMap("token", session.getId()));
        } catch (IncorrectCredentialsException e) {
            result.setCode(0);
            result.setMessage("密码错误");
        } catch (LockedAccountException e) {
            result.setCode(0);
            result.setMessage("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            result.setCode(0);
            result.setMessage("该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/logout")
    public ResponseResult<?> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    @GetMapping("/unauth")
    public ResponseResult<?> unAuth() {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(401);
        result.setMessage("请登录");
        return result;
    }
}
