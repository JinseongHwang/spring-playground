package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        writer.write("<html>\n");
        writer.write("<head>\n");
        writer.write("  <meta charset=\"UTF-8\">\n");
        writer.write("  <title>Title</title>\n");
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("<a href=\"/index.html\">메인</a>\n");
        writer.write("<table>\n");
        writer.write("  <thead>\n");
        writer.write("  <th>id</th>\n");
        writer.write("  <th>username</th>\n");
        writer.write("  <th>age</th>\n");
        writer.write("  </thead>\n");
        writer.write("  <tbody>\n");
        writer.write("  <tr>\n");

        for (Member member : members) {
            writer.write("  <tr>\n");
            writer.write("  <td>" + member.getId() + "</td>\n");
            writer.write("  <td>" + member.getUsername() + "</td>\n");
            writer.write("  <td>" + member.getAge() + "</td>\n");
            writer.write("  </tr>\n");
        }
        writer.write("  </tbody>\n");
        writer.write("</table>\n");
        writer.write("</body>\n");
        writer.write("</html>\n");
    }
}
