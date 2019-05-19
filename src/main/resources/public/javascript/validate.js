function validate() {
    return fetch("data/validate")
        .then(res => res.json())
        .then(resultado => {
            if(resultado == false) {
                window.location.href = "login.html";
            } else {
                return resultado;
            }
        })
}

function validateIsLogado() {
    return fetch("data/validate")
        .then(res => res.json())
        .then(resultado => {
            console.log(resultado);
            if(resultado == true) {
                window.location.href = "index.html";
            } else {
                return resultado;
            }
        })
}