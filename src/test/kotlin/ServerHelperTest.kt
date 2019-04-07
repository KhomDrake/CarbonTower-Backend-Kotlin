import com.listadefavorecidos.application.web.ListaDeFavorecidos
import org.junit.Before
import kotlin.test.AfterTest

abstract class ServerHelperTest : BankHelperTest() {
    @AfterTest
    fun stopServer() {
        ListaDeFavorecidos.stopServer()
    }

    @Before
    fun startServer() {
        ListaDeFavorecidos.startServer()
    }
}