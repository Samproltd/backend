package com.exam.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.model.UserType;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	
	   @Override
	    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	        User existingUser = userRepository.findByUsername(user.getUsername());

	        if (existingUser != null) {
	            throw new Exception("User already exists with username: " + user.getUsername());
	        }

	        // Save roles first
	        for (UserRole ur : userRoles) {
	            roleRepository.save(ur.getRole());
	        }

	        // Set roles and save user
	        user.getUserRoles().addAll(userRoles);
	        return userRepository.save(user);
	    }
	
	
	
	
	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public byte[] getUserPhoto(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		return (user != null) ? user.getPhoto() : null;
	}

//    // ✅ Create user (Handles profile photo)
//    @Override
//    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
//        User local = this.userRepository.findByUsername(user.getUsername());
//
//        if (local != null) {
//            throw new UserFoundException();
//        } else {
//            if (user.getUserType() == null) {
//                throw new IllegalArgumentException("UserType cannot be null");
//            }
//
//            // ✅ Save roles first
//            for (UserRole ur : userRoles) {
//                roleRepository.save(ur.getRole());
//            }
//
//            user.getUserRoles().addAll(userRoles);
//
//            // ✅ Save user (including profile photo)
//            local = this.userRepository.save(user);
//        }
//
//        return local;
//    }
//
//    // ✅ Save photo as BLOB (binary data)
//    public void saveUserPhoto(Long userId, MultipartFile file) throws IOException {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setPhoto(file.getBytes()); // Convert file to byte array
//        userRepository.save(user);
//    }
//
//    // ✅ Get user photo as byte array
//    public byte[] getUserPhoto(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        return user.getPhoto();
//    }
//
//    @Override
//    public User getUser(String username) {
//        return this.userRepository.findByUsername(username);
//    }
//
//    @Override
//    public List<User> getAllUser() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        this.userRepository.deleteById(userId);
//    }
//
//    @Override
//    public List<User> getUsersByType(UserType userType) {
//        return this.userRepository.findByUserType(userType);
//    }
}

//package com.exam.service.impl;
//
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.exam.helper.UserFoundException;
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.model.UserType;
//import com.exam.repo.RoleRepository;
//import com.exam.repo.UserRepository;
//import com.exam.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;
//
//	// ✅ Create user (Handles profile photo)
//	@Override
//	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
//		User local = this.userRepository.findByUsername(user.getUsername());
//
//		if (local != null) {
//			throw new UserFoundException();
//		} else {
//			if (user.getUserType() == null) {
//				throw new IllegalArgumentException("UserType cannot be null");
//			}
//
//			// ✅ Save roles first
//			for (UserRole ur : userRoles) {
//				roleRepository.save(ur.getRole());
//			}
//
//			user.getUserRoles().addAll(userRoles);
//
//			// ✅ Save user (including base64 profile photo)
//			local = this.userRepository.save(user);
//		}
//
//		return local;
//	}
//
//	@Override
//	public User getUser(String username) {
//		return this.userRepository.findByUsername(username);
//	}
//
//	@Override
//	public List<User> getAllUser() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public void deleteUser(Long userId) {
//		this.userRepository.deleteById(userId);
//	}
//
//	@Override
//	public List<User> getUsersByType(UserType userType) {
//		return this.userRepository.findByUserType(userType);
//	}
//}
//
////package com.exam.service.impl;
////
////import java.util.List;
////import java.util.Set;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import com.exam.helper.UserFoundException;
////import com.exam.model.User;
////import com.exam.model.UserRole;
////import com.exam.model.UserType;
////import com.exam.repo.RoleRepository;
////import com.exam.repo.UserRepository;
////import com.exam.service.UserService;
////
////@Service
////public class UserServiceImpl implements UserService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private RoleRepository roleRepository;
////
////    // ✅ Create user (Handles profile photo)
////    @Override
////    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
////        User local = this.userRepository.findByUsername(user.getUsername());
////
////        if (local != null) {
////            throw new UserFoundException();
////        } else {
////            if (user.getUserType() == null) {
////                throw new IllegalArgumentException("UserType cannot be null");
////            }
////
////            // ✅ Save roles first
////            for (UserRole ur : userRoles) {
////                roleRepository.save(ur.getRole());
////            }
////
////            user.getUserRoles().addAll(userRoles);
////
////            // ✅ Save user (including base64 profile photo)
////            local = this.userRepository.save(user);
////        }
////
////        return local;
////    }
////
////    @Override
////    public User getUser(String username) {
////        return this.userRepository.findByUsername(username);
////    }
////
////    @Override
////    public List<User> getAllUser() {
////        return userRepository.findAll();
////    }
////
////    @Override
////    public void deleteUser(Long userId) {
////        this.userRepository.deleteById(userId);
////    }
////
////    @Override
////    public List<User> getUsersByType(UserType userType) {
////        return this.userRepository.findByUserType(userType);
////    }
////}
//
////package com.exam.service.impl;
////
////import com.exam.helper.UserFoundException;
////import com.exam.helper.UserNotFoundException;
////import com.exam.model.Role;
////import com.exam.model.User;
////import com.exam.model.UserRole;
////import com.exam.repo.RoleRepository;
////import com.exam.repo.UserRepository;
////import com.exam.service.UserService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////import java.util.Set;
////
////@Service
////public class UserServiceImpl implements UserService {
////
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private RoleRepository roleRepository;
////
////    //creating user
////    @Override
////    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
////
////
////        User local = this.userRepository.findByUsername(user.getUsername());
////        if (local != null) {
////            System.out.println("User is already there !!");
////            throw new UserFoundException();
////        } else {
////            //user create
////            for (UserRole ur : userRoles) {
////                roleRepository.save(ur.getRole());
////            }
////
////            user.getUserRoles().addAll(userRoles);
////            local = this.userRepository.save(user);
////
////        }
////
////        return local;
////    }
////
////    //getting user by username
////    @Override
////    public User getUser(String username) {
////        return this.userRepository.findByUsername(username);
////    }
////
////    @Override
////    public void deleteUser(Long userId) {
////        this.userRepository.deleteById(userId);
////    }
////
////	@Override
////	public List<User> getAllUser() {
////		return userRepository.findAll();
////		
////	}
////    
////    
////
////
////}
