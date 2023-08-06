package com.qxc.controller;

import com.qxc.interfaces.Log;
import com.qxc.interfaces.Require;
import com.qxc.interfaces.RequireInterface;
import com.qxc.pojo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 16:27
 * @Version 1.0
 * @PACKAGE com.qxc.controller
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

    @RequestMapping("/in")
    @Require(intercept = AuthorTest.class, staticMethod = "authorTest")
    @Log(name = "get", requireTime = true)
    public Result get(@RequestParam("name") String name, @RequestParam("password") String password) {
        return new Result().setMessage("success").setDate("duibqorvbqrewrubfoqbwfuqwi;bfuidsbuoivcqwvfoqewbrfoqwbfoqebfoqewf");
    }
}

@Component
class AuthorTest implements RequireInterface {

    /**
     * 判断是否可以访问
     *
     * @param objects
     * @return
     */
    @Override
    public boolean canVisit(Object[] objects) {
        return true;
    }
}