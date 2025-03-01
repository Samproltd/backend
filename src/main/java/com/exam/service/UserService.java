package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	 User createUser(User user, Set<UserRole> userRoles) throws Exception; 
	User getUser(String username);
	User getUserById(Long userId);
	List<User> getAllUsers();
	User updateUser(User user);
	void deleteUser(Long userId);
	byte[] getUserPhoto(Long userId);
	
}


//
//package com.exam.service;
//
//import com.exam.model.User;
//import com.exam.model.UserRole;
//import com.exam.model.UserType;
//
//import java.util.List;
//import java.util.Set;
//
//public interface UserService {
//
//	// ✅ Creating user (Supports userType)
//	User createUser(User user, Set<UserRole> userRoles) throws Exception;
//
//	// ✅ Get user by username
//	User getUser(String username);
//
//	// ✅ Delete user by id
//	void deleteUser(Long userId);
//
//	// ✅ Get all users
//	List<User> getAllUser();
//
//	// ✅ Get users by UserType (New Method)
//	List<User> getUsersByType(UserType userType);
//}
//
////package com.exam.service;
////
////import com.exam.model.User;
////import com.exam.model.UserRole;
////
////import java.util.List;
////import java.util.Set;
////
////public interface UserService {
////
////    //creating user
////    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
////
////    //get user by username
////    public User getUser(String username);
////
////    //delete user by id
////    public void deleteUser(Long userId);
////
////    // get all user.
////    public List<User> getAllUser();
////
////}
////
////
