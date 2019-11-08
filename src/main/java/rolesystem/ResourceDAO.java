package rolesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ResourceDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Resource> getListOfResource() {
        Collection<Map<String, Object>> resource = jdbcTemplate.queryForList("select * from resource");
        List<Resource> resourceList = new ArrayList<>();
        resource.stream().map((row) -> {
            Resource r = new Resource();
            r.setResource_name((String)row.get("resource_name"));
            r.setId(String.valueOf(row.get("id")));
            return r;
        }).forEach((res) -> {
            resourceList.add(res);
        });
        return resourceList;
    }

    public void deleteResource(int resource_id) {
        jdbcTemplate.update("delete from resource where id=?", new Object[] {resource_id});
    }

    public void updateResource(int resource_id, Resource resource) {
        jdbcTemplate.update("update resource set resource_name=? where id=?", new Object[] {resource.getResource_name(), resource_id});
    }

    public void createResource(Resource resource) {
        jdbcTemplate.update("insert into resource(resource_name) values (?)", new Object[] {resource.getResource_name()});
    }
}
