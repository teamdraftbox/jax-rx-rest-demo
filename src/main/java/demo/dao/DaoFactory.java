package demo.dao;

public final class DaoFactory {

	private static final String IMPLI_TYPE = "jdbc";

	private DaoFactory() {
	}

	public static ContactDao getContcatDao() throws DaoException {

		switch (IMPLI_TYPE) {
		case "jdbc":
			return new jdbcContactsDao();

		default:
			throw new DaoException("no suitable implimentaion");
		}
	}
}
