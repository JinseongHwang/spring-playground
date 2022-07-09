package me.study.resttemplate.domain.user;

import java.util.ArrayList;
import java.util.List;
import me.study.resttemplate.error.CustomException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final List<User> users;

    static {
        users = new ArrayList<>(List.of(
            new User(1, "eddy.a", 20),
            new User(2, "eddy.b", 10),
            new User(3, "eddy.c", 18),
            new User(4, "eddy.d", 27),
            new User(5, "eddy.e", 5)
        ));
    }

    public User findUserById(int id) {
        return users.stream()
                    .filter(user -> user.getId() == id)
                    .findAny()
                    .orElseThrow(CustomException::new);
    }
}
