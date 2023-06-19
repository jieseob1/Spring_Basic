package hello.hellospring.domain;

//Member instance
public class Member {
    //private선언 ,간접적으로 가지고옴
    private Long id; // system save id
    private String name; // member name

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; // this.name
    }
}
