package com.second.project.other.login;

import com.second.common.utils.bean.ApplicationContextHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试登录 dto
 * {@code @author}  chouchou
 * {@code @date}    2023/4/12 15:59
 */
@RestController
@RequestMapping("/login")
public class TestLoginInfo {

    @GetMapping
    public void commonLogin() {
        LoginInfoDTO loginInfoDTO = ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        loginInfoDTO.setUserCode("1");
        loginInfoDTO.setUserCode("222");

        System.out.println(loginInfoDTO);
    }
}
