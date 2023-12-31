package mft.controller;

import mft.model.entity.User;
import mft.model.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    private static UserController controller=new UserController();

    private UserController() {
    }

    public static UserController getController() {
        return controller;
    }

    public User save(String username, String password , Boolean active) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username) &&
                (Pattern.matches("^[\\w\\S]{3,30}$" , password))) {
            User user =
                    User
                            .builder()
                            .username(username)
                            .password(password)
                            .status(active)
                            .build();
            UserService.getService().save(user);
            return user;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public User edit(Integer id, String username, String password, Boolean status) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username) &&
                (Pattern.matches("^[\\w\\S]{3,30}$" , password))) {
            User user =
                    User
                            .builder()
                            .id(id)
                            .username(username)
                            .password(password)
                            .status(status)
                            .build();
            UserService.getService().edit(user);
            return user;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public User remove(Integer id) throws Exception {
        User user=UserService.getService().findById(id);
        UserService.getService().remove(id);
        return user;
    }

    public List<User> findAll() throws Exception {
        return UserService.getService().findAll();
    }

    public List<User> findByAll(String searchText) throws Exception {
        return UserService.getService().findByAll(searchText);
    }

    public User findById(Integer id) throws Exception {
        return UserService.getService().findById(id);
    }

    public User findByUsername(String username) throws Exception {
        return UserService.getService().findByUsername(username);

    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        User user = UserService.getService().findByUsernameAndPassword(username, password);
        if (user != null) {
            return user;
        }
        throw new Exception("Invalid Username/Password");
    }
}
