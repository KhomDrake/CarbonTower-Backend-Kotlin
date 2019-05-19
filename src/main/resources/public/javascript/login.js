validateIsLogado()

let formLogin = document.getElementById("login");

formLogin.addEventListener('submit', (event) => {
    event.preventDefault();

    let formData = new FormData(formLogin)
    let data = {
        persondata : formData.get('persondata'),
        password: formData.get('password')
    }
    fetch('/login', {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(resultado => {
        console.log(resultado);
        formLogin.reset();
    })
});