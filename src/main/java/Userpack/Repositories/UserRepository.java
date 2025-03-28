package Userpack.Repositories;
import Userpack.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity , String> 
{
	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
}
