package me.study.springbatch;

import java.util.Objects;
import org.junit.jupiter.api.Test;

public class ConvertTest {

    private static final class UserDto {
        private String name;
        private String email;

        public UserDto(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    @Test
    void simpleTest() throws Exception {

        Objects.toString()
    }
}
