package com.wql.project.one.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wql.project.one.entity.Mb01CrmSignature;
import com.wql.project.one.query.Mb01CrmSignatureQuery;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Mb01CrmSignatureMapper extends BaseMapper<Mb01CrmSignature> {

    /**
     * 根据账号获取最新一条签字信息
     *
     * @param mb01UserId
     * @return
     */
    Mb01CrmSignatureVO getSingle(String mb01UserId);
}
