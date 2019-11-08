package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    ResourceDAO resourceDAO;

    @Autowired
    ResourceAssignToUser resourceAssignToUser;

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public ResponseEntity<Object> getListOfResource() {
        return new ResponseEntity<>(resourceDAO.getListOfResource(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('delete_resource', 'SUPERADMIN')")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteResource(@PathVariable("id") int resource_id) {
        resourceDAO.deleteResource(resource_id);
        return new ResponseEntity<>("Resource deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('update_resource', 'SUPERADMIN')")
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateResource(@PathVariable("id") int resource_id, @RequestBody Resource resource) {
        resourceDAO.updateResource(resource_id, resource);
        return new ResponseEntity<>("Resource updated successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('create_resource', 'SUPERADMIN')")
    @RequestMapping(value = "/resources", method = RequestMethod.POST)
    public ResponseEntity<Object> createResource(@RequestBody Resource resource) {
        resourceDAO.createResource(resource);
        return new ResponseEntity<>("Resource created successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('assign_resource_to_user', 'SUPERADMIN')")
    @PutMapping(value = "/resources/{id}/users")
    public ResponseEntity<Object> assignResource2User(@PathVariable("id") int user_id, @RequestBody ArrayList<Integer> resource){
        resourceAssignToUser.assignResource2User(user_id,resource);
        return new ResponseEntity<>("Resource assigned to user successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('delete_resource_from_user', 'SUPERADMIN')")
    @DeleteMapping(value = "resources/{id}/permissions")
    public ResponseEntity<Object> deleteAssignedResource(@RequestBody @PathVariable("id") int resource_id) {
        resourceAssignToUser.deleteAssignedResource(resource_id);
        return new ResponseEntity<>("Assigned resource deleted successfully", HttpStatus.OK);
    }
}
