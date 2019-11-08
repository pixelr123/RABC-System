package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {

	@Autowired
	UserResourceDAO userResourceDAO;

	@PreAuthorize("hasAnyRole('view_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getListOfUsers() {
		return new ResponseEntity<>(userResourceDAO.getListOfUsers(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int user_id) {
		userResourceDAO.deleteUser(user_id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('edit_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@PathVariable("id") int user_id, @RequestBody UserModel userModel) {
		userResourceDAO.updateUser(user_id, userModel);
		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('create_users', 'SUPERADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
		userResourceDAO.createUser(userModel);
		return new ResponseEntity<>("User created successfully", HttpStatus.OK);
	}
}
