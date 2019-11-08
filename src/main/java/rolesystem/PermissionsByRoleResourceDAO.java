package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class PermissionsByRoleResourceDAO {

	@Autowired
    JdbcTemplate jdbcTemplate;

	public List<String> getViewPermissionsByRole(int role_id) {
		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList(
				"select p.permission_name from permission p "
						+ "inner join role_permission role_p on p.id=role_p.permission_id " + "where role_p.role_id=?",
				new Object[] { role_id });
		List<String> permissionsList = new ArrayList<>();
		rows3.stream().map((row) -> (String) row.get("permission_name")).forEach((ss3) -> {
			permissionsList.add(ss3);
		});
		return permissionsList;
	}

	public void assignPermissions2Role(int role_id, List<Integer> permissionsList) {
		jdbcTemplate.update("delete from role_permission where role_id=?", new Object[] {role_id});
		for(int id:permissionsList) {
			jdbcTemplate.update("insert into role_permission (role_id, permission_id) values (?,?)", new Object[]{role_id,id});
		}
	}

	public void deletePermissionAssignedRole(int role_id) {
		jdbcTemplate.update("delete from role_permission where role_id=?", new Object[] {role_id});
	}
}