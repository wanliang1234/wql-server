package com.wql.project.one.controller;

import com.wql.project.one.query.Mb01CrmSignatureQuery;
import com.wql.project.one.service.Mb01CrmSignatureService;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/one/signature")
public class Mb01CrmSignatureController {
    @Autowired
    private Mb01CrmSignatureService mb01CrmSignatureService;

    @GetMapping("/getSingle")
    public Mb01CrmSignatureVO getSingle(@RequestParam("mb01UserId") String mb01UserId) {
        return mb01CrmSignatureService.getSingle(mb01UserId);
    }

    @PostMapping("/setOneMq")
    public Boolean setOneMq(@RequestParam("mb01UserId") String mb01UserId) {
        return mb01CrmSignatureService.setOneMq(mb01UserId);
    }
}
