package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerV2 {

    // MyView 를 반환하는 방식으로 변경됨
    MyView process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}
