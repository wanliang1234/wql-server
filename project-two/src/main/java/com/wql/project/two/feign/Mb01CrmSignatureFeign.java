package com.wql.project.two.feign;

import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "project-one", path = "/one/signature")
public interface Mb01CrmSignatureFeign {
    @GetMapping("/getSingle")
    Mb01CrmSignatureVO getSingle(@RequestParam("mb01UserId") String mb01UserId);
}
