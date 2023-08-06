package com.qxc.controller;

import com.qxc.configurration.CatchConfiguration;
import com.qxc.interfaces.CatchThrowable;
import com.qxc.interfaces.Log;
import com.qxc.interfaces.Require;
import com.qxc.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 16:49
 * @Version 1.0
 * @PACKAGE com.qxc.controller
 */
@RestController
@RequestMapping("/error")
public class ErrorController {
    @Resource(type = CatchConfiguration.class)
    private CatchConfiguration catchConfiguration;

    @GetMapping("/getDetails")
    @Require(intercept = AuthorTest.class, staticMethod = "authorTest")
    @Log(name = "getResult", info = "start doing")
    @CatchThrowable()
    public Result getResult(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("file") String file) throws IOException {
        File f = new File(file);
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        StringBuilder sb = new StringBuilder();
        String n;
        while ((n = input.readLine()) != null) {
            sb.append(n);
        }
        input.close();
        return new Result().setFinish(true).setDate(sb.toString());
    }
}
