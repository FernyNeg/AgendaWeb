package itsh.isic.conexion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectDao {

	@Autowired
	private NamedParameterJdbcTemplate namedjdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getNamedjdbcTemplate() {
		return namedjdbcTemplate;
	}

	public void setNamedjdbcTemplate(NamedParameterJdbcTemplate namedjdbcTemplate) {
		this.namedjdbcTemplate = namedjdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
