validateIsLogado()

let formSignup = document.getElementById("signup");

formSignup.addEventListener('submit', (event) => {
    event.preventDefault();

    let formData = new FormData(formSignup)
    let data = {
        persondata : formData.get('persondata'),
        password: formData.get('password'),
        username: formData.get('username')
    }
    fetch('/signup', {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(resultado => {
        console.log(resultado);
        formSignup.reset();
    })

});