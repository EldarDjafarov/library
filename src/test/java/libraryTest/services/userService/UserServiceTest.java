package libraryTest.services.userService;

import library.dao.userDAO.IUserRepository;
import library.models.security.Role;
import library.models.security.User;

import library.services.userService.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @Test
    public void addUser() {
        User user= new User();
        boolean isCreated=userService.addUser(user);
        Assert.assertTrue(isCreated);
        Assert.assertNotNull(user.isActive());
        Assert.assertTrue(CoreMatchers.is(user.isActive()).matches(true));

        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}