package com.wql.project.one.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wql.project.one.entity.Mb01CrmSignature;
import com.wql.project.one.mapper.Mb01CrmSignatureMapper;
import com.wql.project.one.mq.ExchangesMq;
import com.wql.project.one.mq.QueueKeyMq;
import com.wql.project.one.query.Mb01CrmSignatureQuery;
import com.wql.project.one.service.Mb01CrmSignatureService;
import com.wql.project.one.vo.Mb01CrmSignatureVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mb01CrmSignatureServiceImpl extends ServiceImpl<Mb01CrmSignatureMapper, Mb01CrmSignature> implements Mb01CrmSignatureService {
    @Autowired
    private Mb01CrmSignatureMapper mb01CrmSignatureMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Mb01CrmSignatureVO getSingle(String mb01UserId) {
        return mb01CrmSignatureMapper.getSingle(mb01UserId);
    }

    @Override
    public Boolean setOneMq(String mb01UserId) {
        Mb01CrmSignatureQuery mb01CrmSignatureQuery = new Mb01CrmSignatureQuery();
        mb01CrmSignatureQuery.setMb01UserId(mb01UserId);
        mb01CrmSignatureQuery.setMb01SignatureBt("mb01SignatureBt");
        mb01CrmSignatureQuery.setUpdateId("2131232312");
        mb01CrmSignatureQuery.setCreateId(mb01UserId);
        rabbitTemplate.convertAndSend(ExchangesMq.COM_WQL_ONE_EXCHANGES, QueueKeyMq.COM_WQL_ONE_QUEUES_KEY, JSON.toJSONString(mb01CrmSignatureQuery));
        return true;
    }
}
