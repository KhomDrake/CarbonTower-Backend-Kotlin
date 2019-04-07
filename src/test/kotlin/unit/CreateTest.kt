package unit

import com.listadefavorecidos.domain.entities.*
import com.listadefavorecidos.resources.database.Connection
import com.listadefavorecidos.resources.database.Create
import com.listadefavorecidos.resources.database.exceptions.*
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import BankHelperTest

class CreateTest : BankHelperTest() {

    private val stringNull: String = ""
    private val belowZero = -10

    // BEGIN TEST insertClient

    private val cpfValido = "46099583840"
    private val cpfInvalido = "46049583840"
    private val cnpjValido = "29114513000105"
    private val cnpjInvalido = "29114523000105"
    private val nome = "vinicius"
    private val endereco = "Celso Garcia"
    private val senha = "123456"
    private val capital = 3000.0
    private val valorNaConta = 1000.0

    @Test
    fun `invalid CPF in fun insertClient`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `valid CPF in fun insertClient`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfInvalido, capital, endereco, senha, valorNaConta)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid CNPJ in fun insertClient`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `invalid CNPJ in fun insertClient`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cnpjInvalido, capital, endereco, senha, valorNaConta)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `account value less than zero in fun insertClient`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, belowZero.toDouble())
            }
        }.isInstanceOf(NotAcceptableValues::class.java)
    }

    @Test
    fun `account value above zero in fun insertClient`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `name is string null in fun insertClient`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(stringNull, cpfValido, capital, endereco, senha, valorNaConta)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `valid name in fun insertClient`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `password is string null in fun insertClient`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, stringNull, valorNaConta)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `valid password in fun insertClient`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `adress is string null in fun insertClient`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, stringNull, senha, valorNaConta)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `valid adress in fun insertClient`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    @Test
    fun `duplicate client insert in fun insertClient`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `insert client in fun insertClient`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
        }
    }

    // END TEST insertClient

    // BEGIN TEST insertTed

    private val agencia = "188"
    private val conta = "248793"
    private val banco = "341"
    private val valorAcima = valorNaConta * 2
    private val numBancoInvalido = "3232"
    private val cpfValido2 = "47884329867"
    private val cpfInvalido2 = "47884322867"

    @Test
    fun `invalid CPF in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfInvalido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid CPF in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `invalid CNPJ in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cnpjInvalido, nome, banco, agencia, conta, valorNaConta, -1), 1)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid CNPJ in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cnpjValido, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `name is string null in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, stringNull, banco, agencia, conta, valorNaConta, -1), 1)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `agency is string null in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, nome, banco, stringNull, conta, valorNaConta, -1), 1)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `bank is string null in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, stringNull, banco, agencia, conta, valorNaConta, -1), 1)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `cont is string null in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, stringNull, valorNaConta, -1), 1)
            }
        }.isInstanceOf(StringNull::class.java)
    }

    @Test
    fun `name is string not null in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `agency is string not null in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `bank is string not null in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `cont is string not null in fun insertTed`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `transfer amout above what you own in your account in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorAcima, -1), 1)
            }
        }.isInstanceOf(InvalidTransactionValue::class.java)
    }

    @Test
    fun `transfer amout less than what you own in your account in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta / 2, -1), 1)
        }
    }

    @Test
    fun `transfer amout equal what you own in your account in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta, -1), 1)
        }
    }

    @Test
    fun `invalid bank number in fun insertTed`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.ted(DataTransaction(cpfValido2, nome, numBancoInvalido, agencia, conta, valorNaConta / 2, -1), 1)
            }
        }.isInstanceOf(InvalidBank::class.java)
    }

    @Test
    fun `valid bank number in fun insertTed`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.ted(DataTransaction(cpfValido2, nome, banco, agencia, conta, valorNaConta / 2, -1), 1)
        }
    }

    // END TEST insertTed

    private val transactionValueBelow = valorNaConta * .9
    private val contValidC6 = "2"
    private val firstCont = "1"
    private val contFavNotImportante = -1;


    // BEGIN TEST insertTef

    @Test
    fun `transaction to client that do not exist in fun insertTef`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.updateAccountBalanceClient(1, valorNaConta);
                Create.tef(DataTransaction(cnpjValido, nome, banco, agencia, contValidC6, transactionValueBelow, contFavNotImportante), firstCont.toInt())
            }
        }.isInstanceOf(InvalidTransaction::class.java)
    }

    @Test
    fun `transaction to client that exist in fun insertTef`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.updateAccountBalanceClient(1, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.tef(DataTransaction(cnpjValido, nome, banco, agencia, contValidC6, transactionValueBelow, contFavNotImportante), firstCont.toInt())
        }
    }

    @Test
    fun `transaction to same client in fun insertTef`() {
        Connection.connectWithH2()

        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.updateAccountBalanceClient(1, valorNaConta);
                Create.tef(DataTransaction(cnpjValido, nome, banco, agencia, firstCont, transactionValueBelow, contFavNotImportante), firstCont.toInt())
            }
        }.isInstanceOf(InvalidTransactionSameCont::class.java)
    }

    @Test
    fun `transaction to diferent client in fun insertTef`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.updateAccountBalanceClient(1, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.tef(DataTransaction(cnpjValido, nome, banco, agencia, contValidC6, transactionValueBelow, contFavNotImportante), firstCont.toInt())
        }
    }

    // END TEST insertTef

    // BEGIN TEST insertFavorite

    @Test
    fun `invalid CPF in fun insertFavorite`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cpfInvalido, nome, banco, agencia, conta), 1)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid CPF in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cpfValido, nome, banco, agencia, conta), 1)
        }
    }

    @Test
    fun `invalid CNPJ in fun insertFavorite`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cnpjInvalido, nome, banco, agencia, conta), 1)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid CNPJ in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cnpjValido, nome, banco, agencia, conta), 1)
        }
    }

    @Test
    fun `invalid agency in fun insertFavorite`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cnpjValido, nome, banco, belowZero.toString(), conta), 1)
            }
        }.isInstanceOf(InvalidAgency::class.java)
    }

    @Test
    fun `invalid bank in fun insertFavorite`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cnpjValido, nome, belowZero.toString(), agencia, conta), 1)
            }
        }.isInstanceOf(InvalidBank::class.java)
    }

    @Test
    fun `invalid contNumber in fun insertFavorite`() {
        Connection.connectWithH2()
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cnpjValido, nome, banco, agencia, belowZero.toString()), 1)
            }
        }.isInstanceOf(InvalidValues::class.java)
    }

    @Test
    fun `valid agency in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cpfValido, nome, banco, agencia, conta), 1)
        }
    }

    @Test
    fun `valid bank in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cpfValido, nome, banco, agencia, conta), 1)
        }
    }

    @Test
    fun `valid contNumber in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cpfValido, nome, banco, agencia, conta), 1)
        }
    }

    @Test
    fun `duplicate favorite insertion in fun insertFavorite`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavorite(DataFavorite(cnpjValido, nome, banco, agencia, conta), 1)
                Create.insertFavorite(DataFavorite(cnpjValido, nome, banco, agencia, conta), 1)
            }
        }.isInstanceOf(InvalidFavoriteDuplicate::class.java)
    }

    @Test
    fun `insert favorite in fun insertFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavorite(DataFavorite(cpfValido, nome, banco, agencia, conta), 1)
        }
    }

    // END TEST insertFavorite

    // BEGIN TEST insertFavoriteC6

    @Test
    fun `cont don't exist in fun insertFavoriteC6`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
            }
        }.isInstanceOf(InvalidFavorite::class.java)
    }

    @Test
    fun `cont exist in fun insertFavoriteC6`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
        }
    }

    @Test
    fun `register same cont as favorite in fun insertFavoriteC6`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertFavoriteC6(DataFavorite(cpfValido, nome, banco, agencia, firstCont), 1)
            }
        }.isInstanceOf(InvalidFavoriteSameCont::class.java)
    }

    @Test
    fun `register different cont as favorite in fun insertFavoriteC6`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
        }
    }

    @Test
    fun `insert favorite duplicate in fun insertFavoriteC6`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
                Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
                Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
            }
        }.isInstanceOf(InvalidFavoriteDuplicate::class.java)

    }

    // END TEST insertFavoriteC6

    val idContTransaction = 2
    val invalidIdContTransaction = 3

    // BEGIN TEST insertTransactionFavorite

    @Test
    fun `transaction value above account balance in fun insertTransactionFavorite`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
                Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
                Create.insertTransactionFavorite(DataTransactionFavorite(valorAcima, idContTransaction), 1)
            }
        }.isInstanceOf(InvalidTransactionValue::class.java)
    }

    @Test
    fun `transaction value equal to account balance in fun insertTransactionFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
            Create.insertTransactionFavorite(DataTransactionFavorite(valorNaConta, idContTransaction), 1)
        }
    }

    @Test
    fun `transaction value below to account balance in fun insertTransactionFavorite`() {
        Connection.connectWithH2()
        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
            Create.insertTransactionFavorite(DataTransactionFavorite(transactionValueBelow, idContTransaction), 1)
        }
    }

    @Test
    fun `favorite don't exist in fun insertTransactionFavorite`() {
        Connection.connectWithH2()
        // erro
        Assertions.assertThatThrownBy {
            transaction {
                Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
                Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
                Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
                Create.insertTransactionFavorite(DataTransactionFavorite(valorAcima, invalidIdContTransaction), 1)
            }
        }.isInstanceOf(InvalidTransactionValue::class.java)
    }

    @Test
    fun `favorite exist in fun insertTransactionFavorite`() {
        Connection.connectWithH2()

        transaction {
            Create.insertClient(nome, cpfValido, capital, endereco, senha, valorNaConta)
            Create.insertClient(nome, cnpjValido, capital, endereco, senha, valorNaConta)
            Create.insertFavoriteC6(DataFavorite(cnpjValido, nome, banco, agencia, contValidC6), 1)
            Create.insertTransactionFavorite(DataTransactionFavorite(valorNaConta, idContTransaction), 1)
        }
    }

    // END TEST insertTransactionFavorite
}