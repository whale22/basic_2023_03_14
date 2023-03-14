package com.ll.basic1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

// @Controller 의 의미
// 개발자가 스프링부트에게 말한다.
// 아래 있는 HomeController 는 컨트롤러이다.
@Controller
public class HomeController {
    private List<Person> people = new ArrayList<Person>();
    int cnt=0;
    // @GetMapping("/home/main") 의 의미
    // 개발자가 스프링부트에게 말한다.
    // 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행해줘
    @GetMapping("/home/main")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }
    @GetMapping("/home/increase")
    @ResponseBody
    public int increase() {
        return cnt++;
    }
    @GetMapping("/home/plus")
    @ResponseBody
    public int plus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") int age) {
        Person p = new Person(name, age);
        people.add(p);
        cnt++;
        return cnt+"번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {
        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String showPeople(int id) {
        //person-> person.getID() == id
        //위 함수가 참인 엘리먼트(요소) 경우가 존재하면, 해당 요소를 삭제한다.
        //removed 에는 삭제수행여부가 저장된다.
        //조건에 맞는걸 찾았고 삭제까지 되었다면 true, 아니라면 false
        boolean removed = people.removeIf(person -> person.getId() == id);
        if(!removed){
            return id+"번 사람이 존재하지 않습니다.";
        }
        return id+"번 사람이 삭제되었습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modify(int id, String name, int age) {
        //person-> person.getID() == id
        //위 함수가 참인 엘리먼트(요소) 경우가 존재하면, 해당 요소를 삭제한다.
        //removed 에는 삭제수행여부가 저장된다.
        //조건에 맞는걸 찾았고 삭제까지 되었다면 true, 아니라면 false
        people.set(id-1,new Person(id, name, age));
        return id+"번 사람이 수정되었습니다.";
    }
}

//@AllArgsConstructor
class Person {
    private static int lastId=0;
    private final int id;
    private String name;
    private int age;
    //LinkedList<Integer> relatedID;
    Person(){
        this.id=-1;
        this.name="이름없음";
        this.age=0;
    }
    Person(int id, String name, int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }
    Person(String name, int age){
        this(++lastId, name, age);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

//    public LinkedList<Integer> getRelatedID() {
//        return relatedID;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
