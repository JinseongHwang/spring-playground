package me.study.javatest;

import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static long THRESHOLD = 1000L;

    public FindSlowTestExtension(long threshold) {
        THRESHOLD = threshold;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        final Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        // reflection으로 메서드 정보를 가져옴
        final Method method = context.getRequiredTestMethod();
        final SlowTest slowTestAnnotation = method.getAnnotation(SlowTest.class);

        final Store store = getStore(context);
        final Long start_time = store.remove("START_TIME", long.class);
        final long duration = System.currentTimeMillis() - start_time;
        if (THRESHOLD < duration && slowTestAnnotation == null) { // 1초 넘게 걸리고 && SlowTest 어노테이션이 없는 경우
            System.out.printf("Please consider mark method [%s] with @SlowTest.%n", context.getRequiredTestMethod().getName());
        }
    }

    private Store getStore(ExtensionContext context) {
        final String testClassName = context.getRequiredTestClass().getName();
        final String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(Namespace.create(testClassName, testMethodName));
    }
}
