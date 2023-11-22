package com.lz.applet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(0)
public class PermissionFirstAdvice {


    // 定义一个切点：所有被GetMapping注解修饰的方法会织入advice
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointcut() {}

    // Before表示logAdvice将在目标方法执行前执行
    @Before("logAdvicePointcut()")
    public void logAdvice(){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("get请求的advice触发了");
    }


    //定义一个切面，定义的注解路径
    @Pointcut("@annotation(com.lz.applet.annotation.PermissionAnnotation)")
    private void permissionCheck(){
        System.out.println("Before触发了");
    }

    @Before("@annotation(com.lz.applet.annotation.PermissionAnnotation)")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("====doBefore方法进入了====");
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        System.out.println("执行的方法名："+funcName+"+包名"+declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        // 获取请求 IP
        String ip = request.getRemoteAddr();
        System.out.println("用户请求的地址为"+url+"请求IP为"+ip);
    }

//    @Around("permissionCheck()")
//    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());
//
//        //获取请求参数，详见接口类
//        Object[] objects = joinPoint.getArgs();
//        Long id = ((JSONObject) objects[0]).getLong("id");
//        String name = ((JSONObject) objects[0]).getString("name");
//        System.out.println("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
//        System.out.println("name1->>>>>>>>>>>>>>>>>>>>>>" + name);
//
//        // id小于0则抛出非法id的异常
//        if (id < 0) {
//            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
//        }
//        return joinPoint.proceed();
//    }
}
