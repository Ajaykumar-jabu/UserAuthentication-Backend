package Userpack.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Userpack.Service.UserService;
import Userpack.models.UserEntity;

@CrossOrigin(origins = "*")
@RestController
public class UserRestController {
	
	public UserService userServe;

	public UserRestController(UserService userServe) {
		this.userServe = userServe;
	}


	@PostMapping("/register")
	public ResponseEntity<String> registerUserRequestHandler(@RequestBody UserEntity user)
	{   
		ResponseEntity<String> returnedResponse= userServe.saveToDatabase(user);
		return returnedResponse;
	}
	

	@PostMapping("/login")
	public ResponseEntity<String> loginUserRequestHandler(@RequestBody UserEntity user)
	{  
		ResponseEntity<String> returnedResponse= userServe.loginFromDatabase(user);
		return returnedResponse;
			
	}
	
	
	@GetMapping("/dashboard/{username}")
	public UserEntity getUserRequestHandler(@PathVariable String username )
	{
		UserEntity databaseUser=userServe.getFromDatabase(username);
		return databaseUser;
	}
	
	
	@PutMapping("/dashboard/update/{username}")
	public ResponseEntity<String> UpdateUserRequestHandler(@PathVariable String username,@RequestBody UserEntity user) 
	{
		ResponseEntity<String> returnedStr= userServe.UpdateToDatabase(username, user);
		return returnedStr;
	}
}