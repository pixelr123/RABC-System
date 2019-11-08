package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsersByRoleResource {

	@Autowired
	UsersByRoleResourceDAO usersByRoleResourceDAO;

	@PreAuthorize("hasAnyRole('view_users_by_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.GET)
	public ResponseEntity<Object> viewUsersByRole(@PathVariable("id") int role_id) {
		return new ResponseEntity<>(usersByRoleResourceDAO.viewUsersByRole(role_id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('assign_users_to_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignUsers2Role(@PathVariable("id") int role_id, @RequestBody Collection<Integer> usersList) {
		usersByRoleResourceDAO.assignUsers2Role(role_id, usersList);
		return new ResponseEntity<>("Users are assigned to role successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/users", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAssignedRole(@RequestBody @PathVariable("id") int role_id) {
		usersByRoleResourceDAO.deleteAssignedRole(role_id);
		return new ResponseEntity<>("User assigned role deleted successfully", HttpStatus.OK);
	}
}
