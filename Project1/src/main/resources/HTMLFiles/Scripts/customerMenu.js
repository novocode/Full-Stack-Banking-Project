window.onload = function(){
    let viewAllButton = document.getElementById("viewAllButton");
    let withDrawButton = document.getElementById("withDrawButton");
    let depositButton = document.getElementById("depositButton");
    let transferButton = document.getElementById("transferButton");
    let applicationButton = document.getElementById("applicationButton");
    let logoutButton = document.getElementById("logoutButton");

    viewAllButton.addEventListener('click', viewAllAccounts);
    withDrawButton.addEventListener('click', withdrawAmount);
    depositButton.addEventListener('click', depositAmount);
    transferButton.addEventListener('click', transferAmount);
    applicationButton.addEventListener('click', accountApp);
    logoutButton.addEventListener('click', logoutFunction);
}

function viewAllAccounts(){
    let xhr = new XMLHttpRequest();
    let url = "customerMenu";

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
                if(xhr.status == 200){
                    console.log(xhr.responseText);
                    let accountList = JSON.parse(xhr.responseText);

                    accountList.forEach(element => {
                        displayAccounts(element);                        
                    });
                }
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function depositAmount(){
    let xhr = new XMLHttpRequest();
    let url = "customerMenu";

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
                if(xhr.status == 200){
                    console.log(xhr.responseText);

                    viewAllAccounts();
                    // let accountList = JSON.parse(xhr.responseText);
                    // console.log(accountList);

                    // accountList.forEach(element => {
                    //     addRow(element);                        
                    // });
                }
        }
    }
    xhr.open("PUT", url);
    xhr.send();
}

function withdrawAmount(){
    let xhr = new XMLHttpRequest();
    let url = "customerMenu";

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
                if(xhr.status == 200){
                    console.log(xhr.responseText);

                    viewAllAccounts();
                    // let accountList = JSON.parse(xhr.responseText);
                    // console.log(accountList);

                    // accountList.forEach(element => {
                    //     addRow(element);                        
                    // });
                }
        }
    }
    xhr.open("PUT", url);
    xhr.send();
}

function transferAmount(){
    let xhr = new XMLHttpRequest();
    let url = "customerMenu";

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
                if(xhr.status == 200){
                    console.log(xhr.responseText);

                    viewAllAccounts();
                    // let accountList = JSON.parse(xhr.responseText);
                    // console.log(accountList);

                    // accountList.forEach(element => {
                    //     addRow(element);                        
                    // });
                }
        }
    }
    xhr.open("PUT", url);
    xhr.send();
}

function accountApp(){
    let xhr = new XMLHttpRequest();
    let url = "customerMenu";
    let amount = document.getElementsByName("amount")[0].value;
    let accountType = document.getElementsByName("accountType")[0].value;

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
                if(xhr.status == 200){
                    var successes = document.getElementById('messageDisplay');
                    if(successes.childNodes.length > 1){
                        successes.removeChild(successes.lastElementChild);                            
                    }
                    var success = document.createElement("p");
                    success.textContent = JSON.parse(xhr.responseText);
                    successes.appendChild(success);
                }
                else if(xhr.status == 401){
                    var failures = document.getElementById("messageDisplay");
                    if(failures.childNodes.length > 1){
                        failures.removeChild(successes.lastElementChild);
                    }
                    var failure = document.createElement("p");
                    failure.textContent = JSON.parse(xhr.responseText);
                    failures.appendChild(failure);
                }
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.amount = amount;
    newClient.accountType = accountType;
    xhr.send(
        JSON.stringify(newClient)
    );
}

function logoutFunction(){
    let xhr = new XMLHttpRequest();
    let url = "customerLogin";

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
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayAccounts(accountValues){
    let table = document.getElementById("customerAccounts");
    let tableRow = document.createElement("tr");
    let accNumCol = document.createElement("td");
    let accTypeCol = document.createElement("td");
    let accBalanceCol = document.createElement("td");

    tableRow.appendChild(accNumCol);
    tableRow.appendChild(accTypeCol);
    tableRow.appendChild(accBalanceCol);

    table.appendChild(tableRow);
    accNumCol.innerHTML = accountValues.accountNum;
    accTypeCol.innerHTML = accountValues.accountType;
    accBalanceCol.innerHTML = accountValues.balance;
}