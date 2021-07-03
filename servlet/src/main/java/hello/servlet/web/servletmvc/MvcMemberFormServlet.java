package hello.servlet.web.servletmvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 렌더링 할 JSP 경로
        // WEB-INF 하위에 있는 resource 들은 직접 호출로는 접근할 수 없고, Controller 를 거쳐야만 접근 가능하다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // 다른 Servlet 이나 JSP 로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
        // Controller 에서 View 로 제어권을 옮겨주는 역할을 수행
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 실질적으로 Servlet 에서 JSP 호출
    }
}
