package uk.ac.leedsbeckett.hmm.student_portal.services;

import uk.ac.leedsbeckett.hmm.student_portal.entities.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    String checkRole(Long id);
    void updateRole(Long id);
}
