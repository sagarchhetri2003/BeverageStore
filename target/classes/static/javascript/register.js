let email = document.getElementById("email")
let n = document.getElementById("name")
let number = document.getElementById("number")
let address = document.getElementById("address")
let create = document.getElementById("create-password")
let confirm = document.getElementById("confirm-password")
let form = document.getElementById("register_form")
let format = ('[a-z0-9]+@[a-z]+\.[a-z]{2,3}')
let error = document.getElementById("error")
form.addEventListener("submit", (e) => {
    let messages = []
    if (n.value === "" || n.value == null) {
        messages.push("Name is required")
    }
    if (email.value === "" || email.value == null) {
        messages.push("Email is required")
    }
    if (!email.value.match(format)) {
        messages.push("Enter a valid email address")
    }
    if (number.value === "" || number.value == null) {
        messages.push("Number is required")
    }
    if (number.value.length < 10 || number.value.length > 10) {
        messages.push("Number must be of 10 digit")
    }
    if (address.value === "" || address.value == null) {
        messages.push("Address is required")
    }
    if (create.value.length < 8 || create.value.length > 12) {
        messages.push("Password must be length of 8-12")
    }
    if (!create.value.match(confirm.value)) {
        messages.push("Make sure to confirm your password before we create your account")
    }
    if (messages.length > 0) {
        e.preventDefault()
        error.innerText = messages.join(', ')
    }
})

