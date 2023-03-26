var password = document.getElementById("lozinka")
    , confirm_password = document.getElementById("confirm_lozinka");

function validatePassword(){
    if(password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Lozinka neispravna");
    } else {
        confirm_password.setCustomValidity('');
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;