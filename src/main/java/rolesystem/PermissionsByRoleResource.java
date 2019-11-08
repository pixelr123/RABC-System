package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PermissionsByRoleResource {

	@Autowired
	PermissionsByRoleResourceDAO permissionsByRoleResourceDAO;

	@PreAuthorize("hasAnyRole('view_permissions_by_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.GET)
	public ResponseEntity<Object> viewPermissionsByRole(@PathVariable("id") int role_id) {
		return new ResponseEntity<>(permissionsByRoleResourceDAO.getViewPermissionsByRole(role_id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('assign_permissions_to_role', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.PUT)
	public ResponseEntity<Object> assignPermissions2Role(@PathVariable("id") int role_id, @RequestBody ArrayList<Integer> permissionsList) {
		permissionsByRoleResourceDAO.assignPermissions2Role(role_id, permissionsList);
		return new ResponseEntity<>("Permissions are assigned to role successfully", HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('delete_permission', 'SUPERADMIN')")
	@RequestMapping(value = "/roles/{id}/permissions", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePermissionAssignedRole(@RequestBody @PathVariable("id") int role_id) {
		permissionsByRoleResourceDAO.deletePermissionAssignedRole(role_id);
		return new ResponseEntity<>("Permissions assigned to role deleted successfully", HttpStatus.OK);
	}
}