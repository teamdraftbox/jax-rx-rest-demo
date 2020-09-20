package demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import demo.dao.ContactDao;
import demo.dao.DaoException;
import demo.dao.DaoFactory;
import demo.entity.Contact;

@Path("/contacts")
public class ContactReource {

	private ContactDao dao;

	public ContactReource() throws DaoException {
		dao = DaoFactory.getContcatDao();
	}

	@GET
	@Produces("application/json")
	public Response getAll() throws DaoException {

		return Response.ok(dao.finadAll()).build();
	}

	@Path("/{contact_id}")
	@GET
	@Produces("application/json")
	public Response getContactById(@PathParam("contact_id") int id) throws DaoException {
		return Response.ok(dao.findbyId(id)).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response CreateContact(Contact contact) throws DaoException {
		Contact con = dao.addContact(contact);
		return Response.ok(con).build();

	}

	@Path("/{contcat_id}")
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response UpdateContact(@PathParam("contact_id") Integer id, Contact contact) throws DaoException {
		Contact con = dao.updateContact(contact, id);
		return Response.ok(con).build();

	}

	@Path("/{contact_id}")
	@DELETE
	@Produces("application/json")
	public Response DeleteContactByid(@PathParam("contact_id") Integer id) throws DaoException {

		return Response.ok().build();
	}

}
