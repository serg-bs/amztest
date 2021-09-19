package com.example.demo.controller;

import com.example.demo.ipcache.IpContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
    @Autowired
    IpContextHolder ipContextHolder;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity sendViaResponseEntity(HttpServletRequest request) {
        ipContextHolder.checkIp(request.getRemoteAddr());
        return new ResponseEntity(HttpStatus.OK);
    }
}
