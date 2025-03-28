// package com.exam;

//   import java.util.HashSet;
//   import java.util.Set;

//   import org.springframework.beans.factory.annotation.Autowired;
//   import org.springframework.boot.CommandLineRunner;
//   import org.springframework.boot.SpringApplication;
//   import org.springframework.boot.autoconfigure.SpringBootApplication;
//  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//   import com.exam.helper.UserFoundException;
//   import com.exam.model.Role;
//   import com.exam.model.User;
//   import com.exam.model.UserRole;
//   import com.exam.model.UserType;
//   import com.exam.repo.QuizRepository;
//   import com.exam.service.UserService;

//   @SpringBootApplication
//   public class ExamserverApplication
//   	 implements CommandLineRunner{ 

//       @Autowired
//       private UserService userService;

//       @Autowired
//       private BCryptPasswordEncoder bCryptPasswordEncoder;

//       @Autowired
//       public QuizRepository quizRepository;

//       public static void main(String[] args) {


//           SpringApplication.run(ExamserverApplication.class, args);


//       }

//       @Override
//       public void run(String... args) throws Exception {
//           try {


//               System.out.println("starting code");
   
//               User user = new User();

//               user.setFirstName("Bhushan");
//               user.setLastName("Galphade");
//               user.setUsername("bhushan@gmail.com");
//               user.setPassword(this.bCryptPasswordEncoder.encode("bhushan123"));
//               user.setEmail("bhushan@gmail.com");
//               // user.setProfile("default.png");
//               user.setPhone("9359339299");
//              user.setUserType(UserType.GENERAL);
//               Role role1 = new Role();
//               role1.setRoleId(44L);
//               role1.setRoleName("ADMIN");

//               Set<UserRole> userRoleSet = new HashSet<>();
//               UserRole userRole = new UserRole();

//               userRole.setRole(role1);

//               userRole.setUser(user);

//               userRoleSet.add(userRole);

//               User user1 = this.userService.createUser(user, userRoleSet);
//               System.out.println(user1.getUsername());


//           } catch (UserFoundException e) {
//               e.printStackTrace();


//           }


//       }


//  }

package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.model.UserType;
import com.exam.repo.QuizRepository;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuizRepository quizRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Starting application...");

            User user = new User();
            user.setFirstName("Bhushan");
            user.setLastName("Galphade");
            user.setUsername("bhushan@gmail.com");
            user.setPassword(this.bCryptPasswordEncoder.encode("bhushan123"));
            user.setEmail("bhushan@gmail.com");
            user.setPhone("9359339299");
            user.setUserType(UserType.GENERAL);

            // ✅ Setting photo to null explicitly (although it's already null by default)
            user.setPhoto(null);

            Role role1 = new Role();
            role1.setRoleId(44L);
            role1.setRoleName("ADMIN");

            Set<UserRole> userRoleSet = new HashSet<>();
            UserRole userRole = new UserRole();

            userRole.setRole(role1);
            userRole.setUser(user);
            userRoleSet.add(userRole);

            User createdUser = this.userService.createUser(user, userRoleSet);
            System.out.println("User created: " + createdUser.getUsername());

        } catch (UserFoundException e) {
            e.printStackTrace();
        }
    }
}
