package hello.hellospring.controller;

public class MemberForm {
    private String name; //createMemberForm에서 받은게 들어옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
