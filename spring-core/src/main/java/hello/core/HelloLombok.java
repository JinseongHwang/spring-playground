package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Lombok 라이브러리에서 자동으로 Getter와 Setter 를 만들어준다.
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("springLombok");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
