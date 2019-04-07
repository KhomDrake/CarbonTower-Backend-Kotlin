import com.listadefavorecidos.resources.database.Connection
import com.listadefavorecidos.resources.database.Create
import org.junit.Before
import kotlin.test.AfterTest

abstract class BankHelperTest {

    @AfterTest
    fun end() {
        Create.dropTables()
    }

    @Before
    fun setup() {
        Connection.connectWithH2()
        Create.createBancoH2()
    }
}