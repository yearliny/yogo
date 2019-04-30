package ga.yuan.yogo.aop;

import ga.yuan.yogo.consts.YogoConst;
import ga.yuan.yogo.service.OptionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * 使用 AOP 保持数据库 yg_options 表的内容始终与 {@link YogoConst#OPTIONS} 一致
 */
@Slf4j
@Aspect
@Configuration
public class ConstSyncAspect {

    private final OptionService optionService;

    public ConstSyncAspect(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * 定义切点，这里定义对 OptionDO 的增、删、改操作
     */
    @Pointcut("execution(* ga.yuan.yogo.service.OptionService.save*(..)))")
    public void optionSaveExecution() {
    }

    @Pointcut("execution(* ga.yuan.yogo.service.OptionService.delete*(..)))")
    public void optionDeleteExecution() {
    }

    /**
     * 对 OptionService 增删改操作后，我们更新 YogoConst.OPTIONS 常量
     */
    @After("optionSaveExecution() || optionDeleteExecution()")
    public void syncYogoConst(JoinPoint joinPoint) {
        YogoConst.OPTIONS = optionService.findAll();
        log.info("Update YogoConst.OPTIONS success. At joinPoint {}", joinPoint.getSignature().getDeclaringTypeName());
    }
}
