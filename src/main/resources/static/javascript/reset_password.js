let email = document.getElementById("email")
let otp = document.getElementById("OTP")
let password = document.getElementById("password")
let form = document.getElementById("otp-form")
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
    if (password.value.length < 8 || password.value.length > 12) {
        messages.push("Password must be length of 8-12")
    }
    if (messages.length > 0) {
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})

