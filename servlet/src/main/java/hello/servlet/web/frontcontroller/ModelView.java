package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelView {

    /**
     * 모든 Controller 가 request, response 정보를 사용하지 않으면서 가지고 있는 것을 해결.
     * 논리적인 View 의 이름을 저장, 경로 중복을 해결.
     */
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
