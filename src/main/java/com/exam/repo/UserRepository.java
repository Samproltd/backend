package com.exam.repo;

import com.exam.model.User;
import com.exam.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> { // ✅ Fixed "Lon.g" typo

	// ✅ Find user by username
	User findByUsername(String username);

	// ✅ New method: Find users by userType
	List<User> findByUserType(UserType userType);
//	User findByUsername(String username);
}
//package com.exam.repo;
//
//import com.exam.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    public User findByUsername(String username);
//}
