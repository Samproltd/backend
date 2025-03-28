package com.exam.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled = true;

    // ✅ User Type field
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    // ✅ Storing profile photo as LONGBLOB (Binary Data)
    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB", nullable = true) // Ensure nullable
    private byte[] photo = null; // ✅ Set default value to null

    // ✅ User roles (many-to-one relationship)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    // ✅ Default constructor
    public User() {
        this.photo = null; // ✅ Ensuring photo is null by default
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    // ✅ Constructor with all fields
    public User(Long id, String username, String password, String firstName, String lastName, String email,
            String phone, boolean enabled, UserType userType, byte[] photo, Set<UserRole> userRoles) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.userType = userType;
        this.photo = photo;
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });
        return set;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled
                + ", userType=" + userType + ", photo=" + Arrays.toString(photo) + ", userRoles=" + userRoles + "]";
    }
}


// package com.exam.model;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.HashSet;
// import java.util.Set;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Lob;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// @Entity
// @Table(name = "users")
// //@Getter
// //@Setter
// //@NoArgsConstructor
// //@AllArgsConstructor
// //@ToString
// public class User implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String username;
//     private String password;
//     private String firstName;
//     private String lastName;
//     private String email;
//     private String phone;
//     private boolean enabled = true;

//     // ✅ Adding userType field
//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private UserType userType;

//     // ✅ Storing profile photo as LONGBLOB (Binary Data)
//     @Lob
//     @Column(name = "photo", columnDefinition = "LONGBLOB")
//     private byte[] photo;// Stores image as binary data

//     // ✅ User roles (many-to-one relationship)
//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//     @JsonIgnore
//     private Set<UserRole> userRoles = new HashSet<>();

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         Set<Authority> set = new HashSet<>();
//         this.userRoles.forEach(userRole -> {
//             set.add(new Authority(userRole.getRole().getRoleName()));
//         });
//         return set;
//     }

//     @Override
//     public boolean isAccountNonExpired() { return true; }

//     @Override
//     public boolean isAccountNonLocked() { return true; }

//     @Override
//     public boolean isCredentialsNonExpired() { return true; }

// 	public Long getId() {
// 		return id;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public String getUsername() {
// 		return username;
// 	}

// 	public void setUsername(String username) {
// 		this.username = username;
// 	}

// 	public String getPassword() {
// 		return password;
// 	}

// 	public void setPassword(String password) {
// 		this.password = password;
// 	}

// 	public String getFirstName() {
// 		return firstName;
// 	}

// 	public void setFirstName(String firstName) {
// 		this.firstName = firstName;
// 	}

// 	public String getLastName() {
// 		return lastName;
// 	}

// 	public void setLastName(String lastName) {
// 		this.lastName = lastName;
// 	}

// 	public String getEmail() {
// 		return email;
// 	}

// 	public void setEmail(String email) {
// 		this.email = email;
// 	}

// 	public String getPhone() {
// 		return phone;
// 	}

// 	public void setPhone(String phone) {
// 		this.phone = phone;
// 	}

// 	public boolean isEnabled() {
// 		return enabled;
// 	}

// 	public void setEnabled(boolean enabled) {
// 		this.enabled = enabled;
// 	}

// 	public UserType getUserType() {
// 		return userType;
// 	}

// 	public void setUserType(UserType userType) {
// 		this.userType = userType;
// 	}

// 	public byte[] getPhoto() {
// 		return photo;
// 	}

// 	public void setPhoto(byte[] photo) {
// 		this.photo = photo;
// 	}

// 	public Set<UserRole> getUserRoles() {
// 		return userRoles;
// 	}

// 	public void setUserRoles(Set<UserRole> userRoles) {
// 		this.userRoles = userRoles;
// 	}

// 	public User(Long id, String username, String password, String firstName, String lastName, String email,
// 			String phone, boolean enabled, UserType userType, byte[] photo, Set<UserRole> userRoles) {
// 		super();
// 		this.id = id;
// 		this.username = username;
// 		this.password = password;
// 		this.firstName = firstName;
// 		this.lastName = lastName;
// 		this.email = email;
// 		this.phone = phone;
// 		this.enabled = enabled;
// 		this.userType = userType;
// 		this.photo = photo;
// 		this.userRoles = userRoles;
// 	}

// 	public User() {
// 		super();
// 		// TODO Auto-generated constructor stub
// 	}

// 	@Override
// 	public String toString() {
// 		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
// 				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled
// 				+ ", userType=" + userType + ", photo=" + Arrays.toString(photo) + ", userRoles=" + userRoles + "]";
// 	}
    
    
    
    
// }


//package com.exam.model;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class User implements UserDetails {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	private String username;
//	private String password;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String phone;
//	private boolean enabled = true;
//	private String profile;
//
//	// Adding userType field
////	@Enumerated(EnumType.STRING) // Store as a string in DB
////	@Column(nullable = false)
////	private UserType userType;
//
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private UserType userType;
//	
//	// User roles (many-to-one relationship)
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//	@JsonIgnore
//	private Set<UserRole> userRoles = new HashSet<>();
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Set<Authority> set = new HashSet<>();
//		this.userRoles.forEach(userRole -> {
//			set.add(new Authority(userRole.getRole().getRoleName()));
//		});
//		return set;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	
//}

//package com.exam.model;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class User implements UserDetails {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	private String username;
//	private String password;
//	private String firstName;
//	private String lastName;
//	private String email;
//	private String phone;
//	private boolean enabled = true;
//	private String profile;
//
//	// user many roles
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
//	@JsonIgnore
//	private Set<UserRole> userRoles = new HashSet<>();
//
////	public User() {
////
////	}
//
//	public Set<UserRole> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(Set<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}
//
////	public User(Long id, String username, String password, String firstName, String lastName, String email,
////			String phone, boolean enabled, String profile) {
////		this.id = id;
////		this.username = username;
////		this.password = password;
////		this.firstName = firstName;
////		this.lastName = lastName;
////		this.email = email;
////		this.phone = phone;
////		this.enabled = enabled;
////		this.profile = profile;
////	}
//
////	public String getProfile() {
////		return profile;
////	}
////
////	public void setProfile(String profile) {
////		this.profile = profile;
////	}
////
////	public Long getId() {
////		return id;
////	}
////
////	public void setId(Long id) {
////		this.id = id;
////	}
////
////	public String getUsername() {
////		return username;
////	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
////	public void setUsername(String username) {
////		this.username = username;
////	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//
//		Set<Authority> set = new HashSet<>();
//		this.userRoles.forEach(userRole -> {
//			set.add(new Authority(userRole.getRole().getRoleName()));
//		});
//
//		return set;
//	}
//
////	public String getPassword() {
////		return password;
////	}
////
////	public void setPassword(String password) {
////		this.password = password;
////	}
////
////	public String getFirstName() {
////		return firstName;
////	}
////
////	public void setFirstName(String firstName) {
////		this.firstName = firstName;
////	}
////
////	public String getLastName() {
////		return lastName;
////	}
////
////	public void setLastName(String lastName) {
////		this.lastName = lastName;
////	}
////
////	public String getEmail() {
////		return email;
////	}
////
////	public void setEmail(String email) {
////		this.email = email;
////	}
////
////	public String getPhone() {
////		return phone;
////	}
////
////	public void setPhone(String phone) {
////		this.phone = phone;
////	}
////
////	public boolean isEnabled() {
////		return enabled;
////	}
////
////	public void setEnabled(boolean enabled) {
////		this.enabled = enabled;
////	}
//}
