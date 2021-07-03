package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 스프링이 자동으로 스프링 빈으로 등록한다.(@Controller 내부에 @Component 가 있기 때문이다.)
 * 스프링 MVC 에서 애노테이션 기반 컨트롤러로 인식한다.
 */
@Controller
public class SpringMemberFormControllerV1 {

    /**
     * 해당 URI 로 들어오는 요청 정보를 매핑한다.
     */
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
