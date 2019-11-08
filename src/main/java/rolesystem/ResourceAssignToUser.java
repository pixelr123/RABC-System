package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ResourceAssignToUser {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void assignResource2User(int user_id, Collection<Integer> resource) {
        for (int id:resource) {
            jdbcTemplate.update("insert into resource_users (user_id, resource_id) values (?,?)", new Object[]{user_id, id});
        }
    }

    public void deleteAssignedResource(int resource_id) {
        jdbcTemplate.update("delete from resource_users where resource_id=?", new Object[] {resource_id});
    }
}
