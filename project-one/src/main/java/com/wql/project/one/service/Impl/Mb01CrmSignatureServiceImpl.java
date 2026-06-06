package com.wql.project.one.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wql.project.one.entity.Mb01CrmSignature;
import com.wql.project.one.mapper.Mb01CrmSignatureMapper;
import com.wql.project.one.query.Mb01CrmSignatureQuery;
import com.wql.project.one.service.Mb01CrmSignatureService;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mb01CrmSignatureServiceImpl extends ServiceImpl<Mb01CrmSignatureMapper, Mb01CrmSignature> implements Mb01CrmSignatureService {
    @Autowired
    private Mb01CrmSignatureMapper mb01CrmSignatureMapper;

    @Override
    public Mb01CrmSignatureVO getSingle(String mb01UserId) {
        return mb01CrmSignatureMapper.getSingle(mb01UserId);
    }
}
