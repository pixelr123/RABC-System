package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class RolesDAO {

	@Autowired
    JdbcTemplate jdbcTemplate;

	public List<UserRole> getListOfRoles() {

		Collection<Map<String, Object>> rows3 = jdbcTemplate.queryForList("select * from role order by id asc");
		List<UserRole> rolesList = new ArrayList<>();
		rows3.stream().map((row) -> {
			UserRole role = new UserRole();
			role.setId(String.valueOf(row.get("id")));
			role.setRole_name((String) row.get("role_name"));
			return role;
		}).forEach((ss3) -> {
			rolesList.add(ss3);
		});
		return rolesList;

	}

	public void deleteRole(int role_id) {
		jdbcTemplate.update("delete from role where id=?", new Object[] { role_id });
	}

	public void updateRole(int role_id, UserRole role) {
		jdbcTemplate.update("update role set role_name=? where id=?", new Object[] { role.getRole_name(), role_id });
	}

	public void createRole(UserRole role) {
		jdbcTemplate.update("insert into role (role_name) values (?)", new Object[] { role.getRole_name() });
	}

}