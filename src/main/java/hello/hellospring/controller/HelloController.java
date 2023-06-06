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
        model.addAttribute("data", "hello!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //controller에서 name= spring 으로 바뀌게 된다.
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) // don't use Model
    {
        return " hello " + name;
    }

//    지금부터 when data needs
    //API
    //json 방식으로 들어옴
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();// make instance
        hello.setName(name); // 객체로 주는 경우 기본적으로 jsonConverter가 움직이게 된다.
        return hello;
    }

    static class Hello {
        private String name; //private를 쓰기 떄문에 getter, setter 를 사용해줘서 꺼내야함

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name; // why use this in here?
        }
    }


}
