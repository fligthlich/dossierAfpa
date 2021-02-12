let btnEnvoyer = document.getElementById('envoyer');
let form = document.querySelector('#form');

// Vérification aprés changement de l'input
form.nom.addEventListener('change',function(event){
    validNomContact(this);
});
// Vérification aprés changement de l'input
form.email.addEventListener('change',function(event){
    validEmail(this);
});
// Vérification aprés changement de l'input
form.message.addEventListener('change',function(event){
    validMessage(this);
})
// On vérifi au changement de l'input si il est rempli ou non
function validNomContact(input){
    if (input.value == ""){
        input.style.border = "1px solid red";
        input.style.boxShadow = "0 0 10px red";
        return false;
    }
     else{
        input.style.border = "1px solid green";
        input.style.boxShadow = "0 0 10px green";
        return true;
    }
}
// On vérifi au changement de l'input email si l'émail est remplit et si il est correct 
function validEmail(input){
    let regex = new RegExp('^[a-z.-_]+[@]{1}[a-z]+[.]{1}[a-z]{2,8}$','g');
    if (input.value == "" || regex.test(input.value) == false){
        input.style.border = "1px solid red";
        input.style.boxShadow = "0 0 10px red";
        return false;
    }
     else{
        input.style.border = "1px solid green";
        input.style.boxShadow = "0 0 10px green";
        return true;
    }
}
// On vérifi au changement de l'input message est remplit 
function validMessage(input){
    if (input.value == ""){
        input.style.border = "1px solid red";
        input.style.boxShadow = "0 0 10px red";
        return false;
    }
     else{
        input.style.border = "1px solid green";
        input.style.boxShadow = "0 0 10px green";
        return true;
    }
}
// Quand on clique sur le bouton Envoyer on vérifi si les champs sont renseigner
form.addEventListener('submit',function(event){
    event.preventDefault();
    if(validNomContact(this.nom) && validEmail(this.email) && validMessage(this.message)){
        form.submit();
    }
    else{
        console.log('champ non renseigner');
    }
});





