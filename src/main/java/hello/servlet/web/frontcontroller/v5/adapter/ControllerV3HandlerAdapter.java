package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // ControllerV3 의 구현체라면 true 를 반환한다.
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws ServletException, IOException {

        // supports 메서드 이후 참 결과를 얻은 handler 만 이 메서드에 접근하기 때문에,
        // 바로 casting 해도 문제되지 않는다.
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);

        return controller.process(paramMap); // ModelView 객체 반환
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request
            .getParameterNames()
            .asIterator()
            .forEachRemaining(
                paramName -> paramMap.put(paramName, request.getParameter(paramName))
            );
        return paramMap;
    }
}
