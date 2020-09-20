package demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo.entity.Contact;
import demo.utils.dbUtil;

public class jdbcContactsDao implements ContactDao {

	public Contact addContact(Contact contact) throws DaoException {
		String query = "insert into contacts(name,gender,email,phone,city,country) values(?,?,?,?,?,?)";
		try (Connection conn = dbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCountry());
			stmt.setString(6, contact.getCity());

			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			keys.getInt(1);
			contact.setId(keys.getInt(1));

			return contact;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Contact findbyId(Integer id) throws DaoException {
		String query = "select * from contacts where  id = ?";

		try (Connection conn = dbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query);) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Contact con = generateContactObj(rs);
				return con;
			}

			rs.close();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return null;
	}

	private Contact generateContactObj(ResultSet rs) throws SQLException {
		Contact con = new Contact();
		con.setId(rs.getInt("id"));
		con.setName(rs.getString("name"));
		con.setCity(rs.getString("city"));
		con.setCountry(rs.getString("country"));
		con.setPhone(rs.getString("phone"));
		con.setEmail(rs.getString("email"));
		con.setGender(rs.getString("gender"));
		return con;
	}

	public Contact updateContact(Contact contact, Integer id) throws DaoException {
		String query = "update contacts set name=?,gender=?,email=?,phone=?,city=?,country=? where id=?";
		try (

				Connection conn = dbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);

		) {

			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCountry());
			stmt.setString(6, contact.getCity());
			stmt.setInt(7, contact.getId());

			int count = stmt.executeUpdate();

			if (count == 0) {
				throw new DaoException("No contact for id" + contact.getId());
			}
			return contact;

		} catch (Exception e) {
			throw new DaoException();
		}
	}

	public void deleteContact(Integer id) throws DaoException {
		String query = "delete contacts where id = ?";

		try (

				Connection conn = dbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new DaoException("No contact for id" + id);
			}

		} catch (Exception e) {
			throw new DaoException();
		}
	}

	// find all contact in the db
	public List<Contact> finadAll() throws DaoException {
		String query = "select * from contacts";
		List<Contact> list = new ArrayList<>();

		try (Connection conn = dbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {

			while (rs.next()) {
				Contact con = generateContactObj(rs);
				list.add(con);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	// find Contact by city
	public List<Contact> findByCity(String city) throws DaoException {
		String query = "select * from contacts where city = ?";
		List<Contact> list = new ArrayList<>();

		try (Connection conn = dbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contact con = generateContactObj(rs);
				list.add(con);
			}
			rs.close();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	// find all contact by country
	public List<Contact> findByCountry(String country) throws DaoException {
		String query = "select * from contacts where country = ?";
		List<Contact> list = new ArrayList<>();

		try (Connection conn = dbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setString(1, country);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contact con = generateContactObj(rs);
				list.add(con);
			}
			rs.close();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

}
