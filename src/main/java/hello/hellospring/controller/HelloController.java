package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // key : "data", data : "hello!!"
        return "hello"; // hello.html 템플릿 실행
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // @ResponseBody를 사용하면 viewResolver를 사용하지 않고 HTTP의 BODY에 return 값을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // @ResponseBody는 주로 객체를 return 하는 경우 사용
    @GetMapping("hello-api")
    @ResponseBody // viewResolver가 아닌 HttpMessageConverter를 사용해서 문자일 경우 StringConverter, 객체일 경우 JsonConverter로 처리
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
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
