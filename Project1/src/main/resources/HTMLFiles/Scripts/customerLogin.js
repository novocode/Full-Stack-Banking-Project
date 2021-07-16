window.onload = function(){
    let returnCustomer = document.getElementById("loginCust");
    returnCustomer.addEventListener('click', returningCustomer);
}

function returningCustomer(){
    let xhr = new XMLHttpRequest();
    let url = "customerLogin";
    let username = document.getElementsByName("username")[0].value;
    let password = document.getElementsByName("password")[0].value;
    // console.log(username);
    // console.log(password);
    // console.log(firstname);
    // console.log(lastname);
    // console.log(ssn);

    xhr.onreadystatechange = function(){
        switch(xhr.readyState){
            case 0:
                console.log("nothing, not initialized yet!");
                break;
            case 1:
                console.log("connection established");
                break;
            case 2:
                console.log("request sent");
                break;
            case 3:
                console.log("awaiting request");
                break;
            case 4:
                console.log("FINISHED!!!!");
                window.location = JSON.parse(xhr.responseText);
                if(xhr.status == 200){
                    console.log(xhr.responseText);
                }
                else if(xhr.status == 401){
                    var failures = document.getElementById("messageDisplay");
                    if(failures.childNodes.length > 1){
                        failures.removeChild(successes.lastElementChild);
                    }
                    var failure = document.createElement("p");
                    failure.textContent = "Incorrect Login!";
                    failures.appendChild(failure);
                }
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.username = username;
    newClient.password = password;
    xhr.send(
        JSON.stringify(newClient)
    );
}