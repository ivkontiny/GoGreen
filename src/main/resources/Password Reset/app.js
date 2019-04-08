function check(form){
    "use strict"
    if(form.pass.value.length >= 6){
        if(form.pass.value === form.passconfirm.value){
            alert("Password Changed Successfully");       
        }
        else{
            alert("Passwords do not match!");
        }
    }
    else{
        alert("Password should contain at least 6 characters");
    }

};
$(document).ready(main);