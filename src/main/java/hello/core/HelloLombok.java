package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //Getter 메소드 생성해줌.
@Setter //Setter 메소드 생성해줌.
@ToString // toString 메소드 생성해줌.
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("nameA");
        helloLombok.setAge(31);

        System.out.println("helloLombok = " + helloLombok);
    }
}
