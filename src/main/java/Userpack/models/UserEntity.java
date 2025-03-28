package Userpack.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userdatatable")
public class UserEntity 
{

		@Id
		@Column(name="username") 
		private String username;
		
		@Column(name="password")
		private String password;
		
		@Column(name="email")
		private String email;
		
		@Column(name="fullname")
		private String   fullname;
		
		@Column(name="location")
		private String location;
		
		@Column(name="bio")
		private String bio;
		
		public UserEntity() {}


		public UserEntity(String username, String password, String email, String fullname, String location, String bio) 
		{
			this.username = username;
			this.password = password;
			this.email = email;
			this.fullname = fullname;
			this.location = location;
			this.bio = bio;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}


		public String getLocation() {
			return location;
		}


		public void setLocation(String location) {
			this.location = location;
		}


		public String getBio() {
			return bio;
		}


		public void setBio(String bio) {
			this.bio = bio;
		}

		@Override
		public String toString() {
			return "UserEntity [username=" + username + ", password=" + password + ", email=" + email + ", fullname="
					+ fullname + ", location=" + location + ", bio=" + bio + "]";
		}
		
}
