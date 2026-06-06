package com.wql.project.one;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wql.project.one.service.Mb01CrmSignatureService;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testOne {
    @Autowired
    private Mb01CrmSignatureService mb01CrmSignatureService;

    @Test
    public void one() throws JsonProcessingException {
        Mb01CrmSignatureVO single = mb01CrmSignatureService.getSingle("cqs_admin");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(single));
    }
}
