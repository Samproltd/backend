package com.exam.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.model.UserType;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// ✅ Create User API (With Image Upload)
	@PostMapping("/")
	public ResponseEntity<?> createUser(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("userType") String userType,
			@RequestParam(value = "photo", required = false) MultipartFile file
			) throws Exception {
		
		// ✅ Validate userType
		try {
			UserType.valueOf(userType); // Validate Enum
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest()
					.body("Invalid UserType! Allowed values: GENERAL, CRASH_COURSE, EXTERNAL.");
		}
		
		// ✅ Create new User object
		User user = new User();
		user.setUsername(username);
		user.setPassword(this.bCryptPasswordEncoder.encode(password));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setUserType(UserType.valueOf(userType));
		
		// ✅ Save Image as BLOB (binary data)
		if (file != null && !file.isEmpty()) {
			user.setPhoto(file.getBytes()); // Store as byte array
		}
		
		// ✅ Assigning default role (NORMAL)
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		
		User savedUser = this.userService.createUser(user, roles);
		return ResponseEntity.ok(savedUser);
	}
	
	// ✅ Get User by Username
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		User user = this.userService.getUser(username);
		return ResponseEntity.ok(user);
	}
	
	// ✅ Get All Users
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	// ✅ Get User by ID
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	// ✅ Get User Photo by ID
	@GetMapping("/{userId}/photo")
	public ResponseEntity<byte[]> getUserPhoto(@PathVariable Long userId) {
		byte[] photo = userService.getUserPhoto(userId);
		if (photo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"user_photo.jpg\"")
				.contentType(MediaType.IMAGE_JPEG)
				.body(photo);
	}
	 
	// ✅ Upload or Update User Photo
	@PostMapping("/{userId}/upload")
	public ResponseEntity<?> uploadPhoto(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
		User user = userService.getUserById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		// Save uploaded image
		user.setPhoto(file.getBytes());
		userService.updateUser(user);
		
		return ResponseEntity.ok("File uploaded successfully");
	}
	
	// ✅ Delete User by ID
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully");
	}
}
//package com.exam.controller;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.exam.helper.UserFoundException;
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.model.UserType;
//import com.exam.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    // ✅ Create User API (With Image Upload)
//    @PostMapping("/")
//    public ResponseEntity<?> createUser(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("email") String email,
//            @RequestParam("phone") String phone,
//            @RequestParam("userType") String userType,
//            @RequestParam(value = "photo", required = false) MultipartFile file
//    ) throws Exception {
//
//        // ✅ Validate userType
//        try {
//            UserType.valueOf(userType); // Validate Enum
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest()
//                    .body("Invalid UserType! Allowed values: GENERAL, CRASH_COURSE, EXTERNAL.");
//        }
//
//        // ✅ Create new User object
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(this.bCryptPasswordEncoder.encode(password));
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setUserType(UserType.valueOf(userType));
//
//        // ✅ Save Image as BLOB (binary data)
//        if (file != null && !file.isEmpty()) {
//            user.setPhoto(file.getBytes()); // Store as byte array
//        }
//
//        // ✅ Assigning default role (NORMAL)
//        Set<UserRole> roles = new HashSet<>();
//        Role role = new Role();
//        role.setRoleId(45L);
//        role.setRoleName("NORMAL");
//
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        roles.add(userRole);
//
//        User savedUser = this.userService.createUser(user, roles);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    // ✅ Get User by Username
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUser(@PathVariable String username) {
//        User user = this.userService.getUser(username);
//        return ResponseEntity.ok(user);
//    }
//
//    // ✅ Get User Photo by ID
//    @GetMapping("/{userId}/photo")
//    public ResponseEntity<byte[]> getUserPhoto(@PathVariable Long userId) {
//        byte[] photo = userService.getUserPhoto(userId);
//        if (photo == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"user_photo.jpg\"")
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(photo);
//    }
//
//    // ✅ Get All Users
//    @GetMapping()
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> list = userService.getAllUser();
//        return ResponseEntity.ok(list);
//    }
//
//    // ✅ Delete User by ID
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        this.userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully!");
//    }
//
//    // ✅ Exception Handling for Duplicate Users
//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//}


//package com.exam.controller;
//
//import java.util.Base64;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.exam.helper.UserFoundException;
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.model.UserType;
//import com.exam.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    // ✅ Create User API (With Image Upload)
//    @PostMapping("/")
//    public ResponseEntity<?> createUser(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("email") String email,
//            @RequestParam("phone") String phone,
//            @RequestParam("userType") String userType,
//            @RequestParam(value = "photo", required = false) MultipartFile file
//    ) throws Exception {
//
//        // ✅ Validate userType
//        try {
//            UserType.valueOf(userType); // Validate Enum
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest()
//                    .body("Invalid UserType! Allowed values: GENERAL, CRASH_COURSE, EXTERNAL.");
//        }
//
//        // ✅ Create new User object
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(this.bCryptPasswordEncoder.encode(password));
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPhone(phone);
//        user.setUserType(UserType.valueOf(userType));
//
//        // ✅ Convert Image to Base64 if provided
//        if (file != null && !file.isEmpty()) {
//            user.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
//        }
//
//        // ✅ Assigning default role (NORMAL)
//        Set<UserRole> roles = new HashSet<>();
//        Role role = new Role();
//        role.setRoleId(45L);
//        role.setRoleName("NORMAL");
//
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        roles.add(userRole);
//
//        User savedUser = this.userService.createUser(user, roles);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    // ✅ Get User by Username
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUser(@PathVariable String username) {
//        User user = this.userService.getUser(username);
//        return ResponseEntity.ok(user);
//    }
//
//    // ✅ Get All Users
//    @GetMapping()
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> list = userService.getAllUser();
//        return ResponseEntity.ok(list);
//    }
//
//    // ✅ Delete User by ID
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        this.userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully!");
//    }
//
//    // ✅ Exception Handling for Duplicate Users
//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//}


//package com.exam.controller;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.exam.helper.UserFoundException;
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.model.UserType;
//import com.exam.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    // ✅ Create User API (Supports base64 image)
//    @PostMapping("/")
//    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
//
//        if (user.getUserType() == null) {
//            return ResponseEntity.badRequest().body("UserType is required!");
//        }
//
//        try {
//            UserType.valueOf(user.getUserType().toString()); // Validate Enum
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest()
//                    .body("Invalid UserType! Allowed values: GENERAL, CRASH_COURSE, EXTERNAL.");
//        }
//
//        // ✅ Encrypt password before saving
//        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//
//        // Assigning default role (NORMAL)
//        Set<UserRole> roles = new HashSet<>();
//        Role role = new Role();
//        role.setRoleId(45L);
//        role.setRoleName("NORMAL");
//
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//
//        roles.add(userRole);
//
//        User savedUser = this.userService.createUser(user, roles);
//        return ResponseEntity.ok(savedUser);
//    }

//    // ✅ Get User by Username
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUser(@PathVariable String username) {
//        User user = this.userService.getUser(username);
//        return ResponseEntity.ok(user);
//    }
//
//    // ✅ Get All Users
//    @GetMapping()
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> list = userService.getAllUser();
//        return ResponseEntity.ok(list);
//    }
//
//    // ✅ Delete User by ID
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        this.userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully!");
//    }
//
//    // ✅ Exception Handling for Duplicate Users
//    @ExceptionHandler(UserFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//}


//package com.exam.controller;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.exam.helper.UserFoundException;
//import com.exam.model.Role;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin("*")
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	// creating user
//	@PostMapping("/")
//	public User createUser(@RequestBody User user) throws Exception {
//
//		user.setProfile("default.png");
//		// encoding password with bcryptpasswordencoder
//
//		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//
//		Set<UserRole> roles = new HashSet<>();
//
//		Role role = new Role();
//		role.setRoleId(45L);
//		role.setRoleName("NORMAL");
//
//		UserRole userRole = new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role);
//
//		roles.add(userRole);
//
//		return this.userService.createUser(user, roles);
//
//	}
//
//	@GetMapping("/{username}")
//	public User getUser(@PathVariable String username) {
//		return this.userService.getUser(username);
//	}
//
//	// delete the user by id
//	@DeleteMapping("/{userId}")
//	public void deleteUser(@PathVariable Long userId) {
//		this.userService.deleteUser(userId);
//	}
//
//	// get all user.
//	@GetMapping()
//	public ResponseEntity<List<User>> getAllUser() {
//		List<User> list = userService.getAllUser();
//		return ResponseEntity.ok(list);
//	}
//
//	// update api
//
//	@ExceptionHandler(UserFoundException.class)
//	public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
//		return ResponseEntity.ok(ex.getMessage());
//	}
//
//}
