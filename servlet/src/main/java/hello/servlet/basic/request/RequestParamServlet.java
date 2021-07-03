package hello.servlet.basic.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. Parameter 전송 기능
 * http://localhost:8080/request-param?username=hwang&age=24
 *
 * 2. 동일한 Parameter Key 전송 기능
 * http://localhost:8080/request-param?username=hwang&age=24&username=hwang1&username=hwang2
 *
 * 이 방식으로 GET 메서드의 URL 쿼리 파라미터 방식과,
 * HTML Form 을 통한 POST 메서드 방식 모두 처리 가능하다.
 * 왜냐하면 전송 형식이 "key1=value1&key2=value2&..." 로 동일하기 때문이다.
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        printAllParam(request);
        printOneParam(request);
        printDuplicateKeyParam(request);

        response.getWriter().write("ok");
    }

    private void printAllParam(HttpServletRequest request) {
        System.out.println("[전체 파라미터 조회] - start");

        request
            .getParameterNames()
            .asIterator()
            .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
    }

    private void printOneParam(HttpServletRequest request) {
        System.out.println("[단일 파라미터 조회] - start");

        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();
    }

    private void printDuplicateKeyParam(HttpServletRequest request) {
        System.out.println("[Key 값에 중복이 있는 파라미터 조회] - start");

        String[] usernames = request.getParameterValues("username");
        if (usernames != null) {
            for (String username : usernames) {
                System.out.println("username = " + username);
            }
        }

        System.out.println("[Key 값에 중복이 있는 파라미터 조회] - end");
        System.out.println();
    }
}
