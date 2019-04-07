package integrated

import org.junit.Test
//import com.github.kittinunf.fuel.Fuel
import org.assertj.core.api.Assertions

class ServerTest {

    /*

    login tudo funcionando
    login senha incorreta e cpf/cnpj válido
    login cpf incorreto e senha correta
    login cnpj incorreto e senha correta

    inserir Pessoa Física
    inserir Pessoa Júrida


    */

    @Test
    fun `start server test`() {
//        Fuel.get("https://httpbin.org/get")
//            .response { request, response, result ->
//                println(request)
//                println(response)
//                val (bytes, error) = result
//                if (bytes != null) {
//                    println("[response bytes] ${String(bytes)}")
//                }
//            }

        Assertions.assertThat(true).isTrue()
    }

    @Test
    fun `start server test 2`() {

    }

}