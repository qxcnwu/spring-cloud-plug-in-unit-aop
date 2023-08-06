package com.qxc.controller;

import com.qxc.configurration.LogConfiguration;
import com.qxc.interfaces.Log;
import com.qxc.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 14:53
 * @Version 1.0
 * @PACKAGE com.qxc.controller
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource(type = LogConfiguration.class)
    private LogConfiguration logConfiguration;

    @GetMapping("/getDetails")
    @Log(name = "getResult", info = "start doing", requireTime = true)
    public Result getResult() {
        return new Result().setFinish(true).setDate(logConfiguration);
    }
}
