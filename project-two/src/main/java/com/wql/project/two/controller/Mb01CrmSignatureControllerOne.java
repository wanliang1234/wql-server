package com.wql.project.two.controller;

import com.wql.project.one.vo.Mb01CrmSignatureVO;
import com.wql.project.two.feign.Mb01CrmSignatureFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/two/signature")
public class Mb01CrmSignatureControllerOne {
    @Autowired
    Mb01CrmSignatureFeign stockFeignService;

    @GetMapping("/getSingle")
    public Mb01CrmSignatureVO getSingle(@RequestParam("mb01UserId") String mb01UserId) {
        return stockFeignService.getSingle(mb01UserId);
    }
}
