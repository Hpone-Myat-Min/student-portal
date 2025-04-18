package uk.ac.leedsbeckett.hmm.student_portal.services;

import uk.ac.leedsbeckett.hmm.student_portal.entities.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    String checkRole(Long id);
    void updateRole(Long id);
    List<User> getUsers();
    void deleteUserById(Long id);
}
