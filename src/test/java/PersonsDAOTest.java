import com.github.prokopenya.crud.DbConnection;
import com.github.prokopenya.dao.PersonsDAO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class PersonsDAOTest {
    private PersonsDAO personsDao;

    @BeforeClass
    void setup() {
        personsDao = new PersonsDAO(DbConnection.getInstance().getConnection());
    }

    @Test
    public void createDataBaseTest() throws SQLException {
        personsDao.createTable();
    }
}
