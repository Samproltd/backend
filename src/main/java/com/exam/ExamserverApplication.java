package com.exam;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.exam.helper.UserFoundException;
// import com.exam.model.Role;
// import com.exam.model.User;
// import com.exam.model.UserRole;
// import com.exam.model.UserType;
// import com.exam.repo.QuizRepository;
// import com.exam.service.UserService;

// @SpringBootApplication
// public class ExamserverApplication
// 	 implements CommandLineRunner{ 

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private BCryptPasswordEncoder bCryptPasswordEncoder;

//     @Autowired
//     public QuizRepository quizRepository;

//     public static void main(String[] args) {


//         SpringApplication.run(ExamserverApplication.class, args);


//     }

//     @Override
//     public void run(String... args) throws Exception {
//         try {


//             System.out.println("starting code");
// //
//             User user = new User();

//             user.setFirstName("Bhushan");
//             user.setLastName("Galphade");
//             user.setUsername("bhushan@gmail.com");
//             user.setPassword(this.bCryptPasswordEncoder.encode("bhushan123"));
//             user.setEmail("bhushan@gmail.com");
// //            user.setProfile("default.png");
//             user.setPhone("9359339299");
//            user.setUserType(UserType.GENERAL);
//             Role role1 = new Role();
//             role1.setRoleId(44L);
//             role1.setRoleName("ADMIN");

//             Set<UserRole> userRoleSet = new HashSet<>();
//             UserRole userRole = new UserRole();

//             userRole.setRole(role1);

//             userRole.setUser(user);

//             userRoleSet.add(userRole);

//             User user1 = this.userService.createUser(user, userRoleSet);
//             System.out.println(user1.getUsername());


//         } catch (UserFoundException e) {
//             e.printStackTrace();


//         }


//     }


// }



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bCrypt.BCryptPasswordEncoder;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.model.UserType;
// Removed unused import for QuizRepository
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Removed unused QuizRepository
    // @Autowired
    // public QuizRepository quizRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int maxRetries = 3; // Maximum number of retry attempts
        int retryDelaySeconds = 10; // Delay between retries in seconds
        boolean success = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                System.out.println("Starting code (Attempt " + attempt + " of " + maxRetries + ")");

                User user = new User();
                user.setFirstName("Bhushan");
                user.setLastName("Galphade");
                user.setUsername("bhushan@gmail.com");
                user.setPassword(this.bCryptPasswordEncoder.encode("bhushan123"));
                user.setEmail("bhushan@gmail.com");
                user.setPhone("9359339299");
                user.setUserType(UserType.GENERAL);

                Role role1 = new Role();
                role1.setRoleId(44L);
                role1.setRoleName("ADMIN");

                Set<UserRole> userRoleSet = new HashSet<>();
                UserRole userRole = new UserRole();
                userRole.setRole(role1);
                userRole.setUser(user);
                userRoleSet.add(userRole);

                User user1 = this.userService.createUser(user, userRoleSet);
                System.out.println("Admin user created: " + user1.getUsername());
                success = true;
                break; // Exit the retry loop on success

            } catch (UserFoundException e) {
                System.err.println("User already exists: " + e.getMessage());
                e.printStackTrace();
                success = true; // User already exists, so we can consider this a success
                break;
            } catch (Exception e) {
                System.err.println("Failed to create admin user (Attempt " + attempt + "): " + e.getMessage());
                e.printStackTrace();
                if (attempt == maxRetries) {
                    System.err.println("Max retries reached. Could not create admin user.");
                    break;
                }
                System.out.println("Retrying in " + retryDelaySeconds + " seconds...");
                Thread.sleep(retryDelaySeconds * 1000); // Wait before retrying
            }
        }

        if (!success) {
            System.err.println("Admin user creation failed after all retries. Application will continue running.");
        }
    }
}


