package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // GET 방식으로 /hello로 요청이 들어오면 아래의 메서드를 실행한다.
    @GetMapping("hello")
    public String hello(Model model) {
        // model의 attribute는 "key: value" 형태이다.
        model.addAttribute("data", "Hello, Jinseong");
        // "viewResolver"가 resources/templates/{hello}.html를 찾아서 렌더링해서 보여줘라는 의미.
        return "hello";
    }

    // GET 방식으로 QueryString에 데이터를 담아서 전달한다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /** @RequestBody 를 사용하면,
     * HTTP의 BODY에 문자 내용을 직접 반환한다.
     * viewResolver 대신에 HttpMessageConverter 가 동작한다.
     * 문자는 StringHttpMessageConverter 가 처리한다.
     * 객체는 MappingJackson2HttpMessageConverter 가 처리한다.
     * byte 또는 기타 등등을 처리하는 HttpMessageConverter 가 기본으로 등록되어 있다.
     * [참고] Default 설정 값들을 임의로 수정 가능하지만, 실무에서도 대부분 그대로 사용한다.
     */

    @GetMapping("hello-string")
    @ResponseBody // viewResolver를 거치지 않고 response의 body에 반환값을 직접 넣어준다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // @ReponseBody를 쓰고 객체를 반환하면 JSON 형태로 반환된다.
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
