package icpc.njust.test.controller;

import com.alibaba.fastjson.JSON;
import icpc.njust.test.service.CheckService;
import icpc.njust.test.service.ClassService;
import icpc.njust.test.service.MessageQueueService;
import icpc.njust.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProxyOperator {
    private final CheckService checkService;
    private final ClassService classService;
    private final UserInfoService userinfoService;
    private final MessageQueueService messageQueueService;
    private Map<String,Class> proxyMapping;

    public Map<String, Class> getProxyMapping() {
        return proxyMapping;
    }

    @Autowired
    public ProxyOperator(CheckService checkService, ClassService classService, UserInfoService userinfoService, MessageQueueService messageQueueService) {
        this.checkService = checkService;
        this.classService = classService;
        this.userinfoService = userinfoService;
        this.messageQueueService = messageQueueService;
        Method[] checkMethods=checkService.getClass().getMethods();
        Method[] classMethods=classService.getClass().getMethods();
        Method[] userinfoMethods=userinfoService.getClass().getMethods();
        proxyMapping=new HashMap<>();
        for (Method entry:checkMethods
             ) {
            proxyMapping.put(entry.getName(),checkService.getClass());
        }
        for (Method entry:classMethods
        ) {
            proxyMapping.put(entry.getName(),classService.getClass());
        }
        for (Method entry:userinfoMethods
        ) {
            proxyMapping.put(entry.getName(),userinfoService.getClass());
        }
    }
    public String operator(String methodName,Object[] arguments){
        if(proxyMapping.containsKey(methodName)){
            Class invokeClass=proxyMapping.get(methodName);
            List<String> result=new ArrayList<>();
            try {
                Method invokeMethod=invokeClass.getMethod(methodName,Object[].class);
                if(invokeClass.getCanonicalName().equals(checkService.getClass().getCanonicalName())){
                    return JSON.toJSONString(invokeMethod.invoke(checkService,arguments));
                }
                if(invokeClass.getCanonicalName().equals(classService.getClass().getCanonicalName())){
                    return JSON.toJSONString(invokeMethod.invoke(classService,arguments));
                }
                if(invokeClass.getCanonicalName().equals(userinfoService.getClass().getCanonicalName())){
                    return JSON.toJSONString(invokeMethod.invoke(userinfoService,arguments));
                }
                result.add("Failed");
                return JSON.toJSONString(result);
            }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                result.add("Failed");
                e.printStackTrace();
            }
            return JSON.toJSONString(result);
        }
        List<String> result=new ArrayList<>();
        result.add("Failed");
        return JSON.toJSONString(result);
    }
}
