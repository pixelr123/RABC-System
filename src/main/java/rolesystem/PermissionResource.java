package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionResource {

	@Autowired
	PermissionResourceDAO permissionResourceDAO;
	
	@PreAuthorize("hasAnyRole('view_permission', 'SUPERADMIN')")
	@RequestMapping(value="/permissions", method= RequestMethod.GET)
	public ResponseEntity<Object> getListOfPermissions() {
		return new ResponseEntity<Object>(permissionResourceDAO.getListOfPermissions(), HttpStatus.OK);
	}
}
