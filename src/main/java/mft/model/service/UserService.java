package mft.model.service;

import mft.model.entity.User;
import mft.model.repository.UserRepository;

import java.util.List;

public class UserService {
    private static UserService service = new UserService();

    private UserService() {
    }

    public static UserService getService() {
        return service;
    }

    public User save(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                return userRepository.save(user);
            } else {
                throw new Exception();
            }
        }
    }

    public User edit(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.edit(user);
        }
    }

    public User remove(int id) throws Exception {
        try (UserRepository userDa = new UserRepository()) {
            User user= userDa.findById(id);
            if (user != null){
                userDa.remove(id);
                return user;
            }
            else {
                return null;
            }
        }
    }

    public List<User> findAll() throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findAll();
        }
    }

    public List<User> findByAll(String searchText) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByAll(searchText);
        }
    }

    public User findById(int id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findById(id);
        }
    }

    public User findByUsername(String username) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByUsername(username);
        }
    }

    public User findByUsernameAndPassword(String username,String password) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByUsername(username);
        }
    }
}

