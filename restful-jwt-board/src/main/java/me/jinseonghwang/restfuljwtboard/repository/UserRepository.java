package me.jinseonghwang.restfuljwtboard.repository;

import java.util.ArrayList;
import java.util.List;
import me.jinseonghwang.restfuljwtboard.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    static public ArrayList<UserDto> users;

    static {
        users = new ArrayList<>();
        users.add(new UserDto("userA", "eddy1", "1234"));
        users.add(new UserDto("userB", "eddy2", "1234"));
        users.add(new UserDto("userC", "eddy3", "1234"));
    }

    public UserDto insertUser(UserDto user) {
        users.add(user);
        return user;
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public UserDto getUserByUserId(String userId) {
        return users.stream()
            .filter(userDto -> userDto.getUserId().equals(userId))
            .findAny()
            .orElse(new UserDto("", "", ""));
    }

    public void updateUserPw(String userId, UserDto user) {
        users.stream()
            .filter(userDto -> userDto.getUserId().equals(userId))
            .findAny()
            .orElse(new UserDto("", "", ""))
            .setUserPw(user.getUserPw());
    }

    public void deleteUser(String userId) {
        users.removeIf(userDto -> userDto.getUserId().equals(userId));
    }
}
