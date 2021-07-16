window.onload = function(){
    let newCust = document.getElementById("newCust");
    newCust.addEventListener('click', newCustomer);
}

function newCustomer(){
    let xhr = new XMLHttpRequest();
    let url = "customerInitial";
    let username = document.getElementsByName("username")[0].value;
    let password = document.getElementsByName("password")[0].value;
    let firstname = document.getElementsByName("firstname")[0].value;
    let lastname = document.getElementsByName("lastname")[0].value;
    let ssn = document.getElementsByName("ssn")[0].value;
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
                console.log(xhr.responseText);
                if(xhr.status == 200){
                    var successes = document.getElementById('messageDisplay');
                    if(successes.childNodes.length > 1){
                        successes.removeChild(successes.lastElementChild);                            
                    }
                    var success = document.createElement("p");
                    success.textContent = "Customer account #" + xhr.responseText + " added successfully!";
                    successes.appendChild(success);
                }
                else if(xhr.status == 401){
                    var failures = document.getElementById("messageDisplay");
                    if(failures.childNodes.length > 1){
                        failures.removeChild(successes.lastElementChild);
                    }
                    var failure = document.createElement("p");
                    failure.textContent = "Customer wasn't added!";
                    failures.appendChild(failure);
                }
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.username = username;
    newClient.password = password;
    newClient.firstname = firstname;
    newClient.lastname = lastname;
    newClient.ssn = ssn;
    xhr.send(
        JSON.stringify(newClient)
    );
}