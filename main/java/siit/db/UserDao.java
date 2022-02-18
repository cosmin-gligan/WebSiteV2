package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.User;

import javax.naming.AuthenticationException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserByNameAndPassword(String name, String password) throws AuthenticationException {
        String sql = "SELECT users.* FROM users WHERE TRIM(users.name) = TRIM(?) AND pass = crypt(TRIM(?), pass)";
        try {
            return jdbcTemplate.query(sql, this::mapDbUser, name, password);
        } catch (DataAccessException e) {
            throw new AuthenticationException("User / parola incorecte!");
        }
    }

    private User mapDbUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getBoolean("isactive"));
    }

}
