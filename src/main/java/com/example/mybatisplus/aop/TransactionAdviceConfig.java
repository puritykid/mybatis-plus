package com.example.mybatisplus.aop;

import com.google.common.collect.Maps;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @author haha
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    /**
     * 设置事务失效时间，如果超过5秒，则回滚事务
     */
    private static final int TX_METHOD_TIMEOUT = 5;

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.example.mybatisplus.service.*.*(..))";

    @Resource
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        // 定义事物传播规则，只读事物
        RuleBasedTransactionAttribute TXATTR_REQUIRED = new RuleBasedTransactionAttribute();
        TXATTR_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TXATTR_REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        TXATTR_REQUIRED.setTimeout(TX_METHOD_TIMEOUT);

        // 定义事物传播规则，以非事物方式运行
        RuleBasedTransactionAttribute TXATTR_READONLY_SUPPORTED = new RuleBasedTransactionAttribute();
        TXATTR_READONLY_SUPPORTED.setReadOnly(true);
        TXATTR_READONLY_SUPPORTED.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        // 事务管理规则，申明具备事物管理的方法名
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        Map<String, TransactionAttribute> sourceMap = Maps.newHashMap();
        sourceMap.put("add*", TXATTR_REQUIRED);
        sourceMap.put("save*", TXATTR_REQUIRED);
        sourceMap.put("delete*", TXATTR_REQUIRED);
        sourceMap.put("update*", TXATTR_REQUIRED);
        sourceMap.put("execute*", TXATTR_REQUIRED);
        sourceMap.put("get*", TXATTR_READONLY_SUPPORTED);
        sourceMap.put("query*", TXATTR_READONLY_SUPPORTED);
        sourceMap.put("find*", TXATTR_READONLY_SUPPORTED);
        sourceMap.put("list*", TXATTR_READONLY_SUPPORTED);
        sourceMap.put("count*", TXATTR_READONLY_SUPPORTED);
        sourceMap.put("is*", TXATTR_READONLY_SUPPORTED);
        source.setNameMap(sourceMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}

