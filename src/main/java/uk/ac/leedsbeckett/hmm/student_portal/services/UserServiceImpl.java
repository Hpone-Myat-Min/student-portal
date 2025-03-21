package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Service;
import uk.ac.leedsbeckett.hmm.student_portal.entities.User;
import uk.ac.leedsbeckett.hmm.student_portal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository ){
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }

    @Override
    public String checkRole(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user.getRole();
    }

    @Override
    public void updateRole(Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.setRole("student");
    }
}
