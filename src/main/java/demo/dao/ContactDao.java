package demo.dao;

import java.util.List;

import demo.entity.Contact;

public interface ContactDao {

	// CRUD OPERATIONS

	public Contact addContact(Contact contact) throws DaoException;

	public Contact findbyId(Integer id) throws DaoException;

	public Contact updateContact(Contact contact, Integer id) throws DaoException;

	public void deleteContact(Integer id) throws DaoException;

	// queries

	public List<Contact> finadAll() throws DaoException;

	public List<Contact> findByCity(String city) throws DaoException;

	public List<Contact> findByCountry(String country) throws DaoException;

}
