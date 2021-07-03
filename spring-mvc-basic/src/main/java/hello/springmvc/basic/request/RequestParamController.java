package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

//        @RestController 가 아니고 @Controller 이기 때문에, "ok" 라는 논리 이름을 가지는 View 를 찾게 된다.
//        이를 response body 에 넣어서 응답하고 싶다면, 메서드 레벨에 @ResponseBody 애노테이션을 붙이면 된다.
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
        @RequestParam String username,
        @RequestParam int age) {

        // 요청 변수명과 사용할 변수명이 동일하다면 @RequestParam 괄호 안에서 명시하지 않아도 된다.
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // 요청 변수명과 동일하게 사용할 예정이라면, @RequestParam 조차 생략해도 된다.
        // 단, String, Integer, int ... 등 단순한 타입(?) 이어야 한다.
        // 대신 가독성이 조금 떨어지는 것 같다. 그냥 애노테이션 붙여주는 것이 좋을 것 같다.
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = true) String username, // default = true
        @RequestParam(required = false) Integer age) {

//       @RequestParam 에는 required=false 가 되어 있어도 항상 무언가가 들어가야 한다.
//       값이 들어가거나, 없다면 null 이 들어가야 하는데, int 타입에는 null 을 저장할 수 없다.
//       따라서 나이를 표현하는 age 변수도 Integer 타입으로 생성해야 한다.
//       또한 required=true 라고 하더라도, "" 빈 문자열인 경우는 처리하지 못한다. 예외처리가 필요하다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") String username,
        @RequestParam(required = false, defaultValue = "-1") Integer age) {
        // defaultValue 는 빈 문자열도 처리 가능하다!

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // Map 으로 한 번에 받을 수 있다.

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-multimap")
    public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        // Key 하나에 대해 Value 가 하나인 것이 보장된다면 Map 을 쓰지만, 그렇지 않다면 MultiValueMap 을 사용한다.

        List<Object> values = paramMap.get("key");
        for (Object value : values) {
            log.info("key, value={}", value);
        }

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttributeV0(@RequestParam String username, @RequestParam int age) {
        // 기존의 방식대로 사용하면 불편하다.
        // 객체를 생성하고, 값을 하나하나 set 해줘야 한다.
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        // @ModelAttribute 를 사용하면 객체 생성, 프로퍼티명으로 변수 매핑 후 대입까지 자동화해준다.
        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        // @ModelAttribute 도 생략 가능하다. 혼란을 야기할 수도 있기 때문에 조심해서 사용하자.
        log.info("helloData={}", helloData);
        return "ok";
    }
}
