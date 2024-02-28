let email = document.getElementById("email")
let form = document.getElementById("request-form")
let format = ('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')
let error = document.getElementById("error")
form.addEventListener("submit", (e) => {
    let messages = []
    if (email.value === "" || email.value == null) {
        messages.push("Email is required")
    }
    if (!email.value.match(format)) {
        messages.push("Enter a valid email address")
    }
    if (messages.length > 0) {
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})

