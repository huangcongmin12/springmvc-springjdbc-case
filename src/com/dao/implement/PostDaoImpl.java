package com.dao.implement;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import com.dao.PostDao;
import com.model.Post;

@Repository
public class PostDaoImpl implements PostDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LobHandler lobHandler;

	public void add(final Post post) {
		final String sql = "insert into tb_post(postText,postAttach) values(?,?)";
		jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
			protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
				lobCreator.setClobAsString(ps, 1, post.getPostText());
				lobCreator.setBlobAsBytes(ps, 2, post.getPostAttach());
			}
		});
	}

	public Post load(final int id) {
		final String sql = "select id,postText,postAttach from tb_post where id=?";
		List<Post> list = jdbcTemplate.query(sql, new Object[] { id }, new int[] { Types.INTEGER }, new RowMapper<Post>() {
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post = new Post();
				post.setId(rs.getInt(1));
				post.setPostText(rs.getString(2));
				byte[] attach = lobHandler.getBlobAsBytes(rs, 3);
				post.setPostAttach(attach);
				return post;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public void loadByStream(final int id, final OutputStream os) {
		final String sql = "select postAttach from tb_post where id=?";
		jdbcTemplate.query(sql, new Object[] { id }, new int[] { id }, new AbstractLobStreamingResultSetExtractor() {
			protected void handleNoRowFound() throws LobRetrievalFailureException {
				System.out.println("Not Found result!");
			}

			protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
				InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
				if (is != null) {
					FileCopyUtils.copy(is, os);
				}
			}
		});
	}
}
