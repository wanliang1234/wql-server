package com.wql.project.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wql.project.one.entity.Mb01CrmSignature;
import com.wql.project.one.query.Mb01CrmSignatureQuery;
import com.wql.project.one.vo.Mb01CrmSignatureVO;

public interface Mb01CrmSignatureService extends IService<Mb01CrmSignature> {

    /**
     * 根据账号获取最新一条签字信息
     *
     * @param mb01UserId
     * @return
     */
    Mb01CrmSignatureVO getSingle(String mb01UserId);
}
