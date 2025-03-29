package Userpack.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Userpack.Repositories.UserRepository;
import Userpack.models.UserEntity;

@Service
public class UserService {
	
	public UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public ResponseEntity<String> saveToDatabase(UserEntity user)
	{
		// HANDLING NULL FOR USER VALUES
		if ( user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getFullname().isEmpty()) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Enter valid credentitals ");
	    }
		
		//HANDLING NULL EXCEPTIONS FOR DATABASE USERDATA
		UserEntity databaseUserUsername=null;
		UserEntity databaseUserEmail=null;
		try{
			databaseUserUsername=userRepo.findByUsername(user.getUsername());
		}
		catch(Exception e)
		{
			databaseUserUsername=null;
		}
		try {
			databaseUserEmail=userRepo.findByEmail(user.getEmail());
		}
		catch(Exception e){
			databaseUserEmail=null;
		}
		
		//HANDLING & COMPARING SIMILIAR VALUES OF USERNAME AND EMAIL FROM DATABASE TO USER ENTERED VALUES
		if(databaseUserUsername!=null && databaseUserUsername.getUsername().equalsIgnoreCase( user.getUsername() ) )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Username already exits! Try Another ");
		}
		
		if(databaseUserEmail!=null && databaseUserEmail.getEmail().equalsIgnoreCase( user.getEmail() ) )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Email already registered ");
		}
		
		//REGISTERING USER IF NON FOUND SIMILIAR
		else userRepo.save(user);
		
		return ResponseEntity.ok("Registration successful");
	
	}
	
	
	public ResponseEntity<String> loginFromDatabase(UserEntity user)
	{
		//NULL HANDLING FOR USER VALUES
		if ( user.getUsername().isEmpty() || user.getPassword().isEmpty()) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Enter valid username or password ");
	    }
		
		
		//HANDLING NULL FOR DATABASE VALUES 
		UserEntity databaseUser = null;
        try 
        {	 
        databaseUser = userRepo.findByUsername(user.getUsername());
        }
        catch (Exception e) 
	    {	
	        databaseUser = null;     
	    }
        
        // VALIDTATING USERNAME BASED ON NULL VALUES 
        //(if Username is not found its returns null pointer exception which means there is no user, Hence Invalid)
		if (databaseUser == null) 
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username");
		}
		
		//VALIDATING THE PASSWORD IF USERNAME IS NOT NULL AND INVALID
		if( databaseUser.getPassword().equals( user.getPassword() ) ) 
		{
			return ResponseEntity.ok("Login successful");  
		}
		else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
	}
	
	
	public UserEntity getFromDatabase(String username)
	{
		UserEntity databaseUser= userRepo.findByUsername(username);
		return databaseUser;
	}
	
	
	public ResponseEntity<String> UpdateToDatabase(String username,UserEntity user)
	{	
		if ( user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getFullname().isEmpty() ) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("✕ Cannot be Empty");
	    }
		else {	userRepo.save(user);  }
		return ResponseEntity.ok("✓ Changes Saved");
	}
	

}