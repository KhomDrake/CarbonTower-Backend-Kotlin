package unit

import com.listadefavorecidos.resources.DataValidation
import org.assertj.core.api.Assertions
import org.junit.Test

class DataValidationTest {
    //CPF

    @Test
    fun `when CPF Is 46099583840 then Answer Is True`(){ // CPF válido
        Assertions.assertThat(DataValidation.cpfValid("46099583840")).isTrue()
    }

    @Test
    fun `when CPF Is 46099asbdfg then Answer Is True`(){ // CPF com qualquer value
        Assertions.assertThat(DataValidation.cpfValid("46099asbdfg")).isTrue()
    }

    @Test
    fun `when CPF Is 4788432968 then Answer Is False`(){ // CPF inválido
        Assertions.assertThat(DataValidation.cpfValid("4788432968")).isFalse()
    }

    @Test
    fun `when CPF Is 47884329 then Answer Is False`(){ // CPF inválido com menos numeros
        Assertions.assertThat(DataValidation.cpfValid("47884329")).isFalse()
    }

    @Test
    fun `when CPF Is 478843296879 then Answer Is False`(){ // CPF inválido com mais numeros
        Assertions.assertThat(DataValidation.cpfValid("478843296879")).isFalse()
    }

    @Test
    fun `when CPF Is Empty then Answer Is False`(){ // CPF vazio
        Assertions.assertThat(DataValidation.cpfValid("")).isFalse()
    }

    @Test
    fun `when CPF Is Null then Answer Is False`(){ // CPF null
        Assertions.assertThat(DataValidation.cpfValid(null.toString())).isFalse()
    }

    @Test
    fun `when CPF Is Abcdefghijk then Answer Is False`(){ // CPF em texto com 11 caracteres
        Assertions.assertThat(DataValidation.cpfValid("abcdefghijk")).isFalse()
    }

    @Test
    fun `when CPF Is abcdefgdjhkagskjhdg then Answer Is False`(){ // CPF em texto com qualquer value de caracter
        Assertions.assertThat(DataValidation.cpfValid("abcdefgdjhkagskjhdg")).isFalse()
    }

    //CNPJ

    @Test
    fun `when CNPJ Is 29114513000105 then Answer Is True`(){ // CNPJ válido
        Assertions.assertThat(DataValidation.cnpjValid("29114513000105")).isTrue()
    }

    @Test
    fun `when CNPJ Is 123Abc then Answer Is True`(){ // CNPJ com qualquer value
        Assertions.assertThat(DataValidation.cnpjValid("29114513abcdfg")).isTrue()
    }

    @Test
    fun `when CNPJ Is 29114513000189 the Answer Is False`(){ // CNPJ inválido
        Assertions.assertThat(DataValidation.cnpjValid("29114513000189")).isFalse()
    }

    @Test
    fun `when CNPJ Is 291145130001 then Answer Is False`(){ // CNPJ inválido com menos numeros
        Assertions.assertThat(DataValidation.cnpjValid("291145130001")).isFalse()
    }

    @Test
    fun `when CNPJ Is 2911451300010514 then Answer Is False`(){ // CNPJ inválido com mais numeros
        Assertions.assertThat(DataValidation.cnpjValid("2911451300010514")).isFalse()
    }

    @Test
    fun `when CNPJ Is Empty then Answer Is False`(){ // CNPJ vazio
        Assertions.assertThat(DataValidation.cnpjValid("")).isFalse()
    }

    @Test
    fun `when CNPJ Is Null then Answer Is False`(){ // CNPJ vazio
        Assertions.assertThat(DataValidation.cnpjValid(null.toString())).isFalse()
    }

    @Test
    fun `when CNPJ Is Abcdefghijklmn then Answer Is False`(){ // CNPJ em texto com 14 caracteres
        Assertions.assertThat(DataValidation.cnpjValid("abcdefghijklmn")).isFalse()
    }

    @Test
    fun `when CNPJ Is Abc then Answer Is False`(){ // CNPJ em texto com qualquer value de caracter
        Assertions.assertThat(DataValidation.cnpjValid("abcdefghijklmnasad")).isFalse()
    }
}