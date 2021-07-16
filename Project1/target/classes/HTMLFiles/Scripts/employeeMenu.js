window.onload = function(){
    let viewAllAppButton = document.getElementById("viewAllApplications");
    let viewAllCustButton = document.getElementById("viewAllCustomers");
    let viewAllTransButton = document.getElementById("viewAllTransactions");
    let logoutButton = document.getElementById("logoutButton");

    viewAllAppButton.addEventListener('click', viewAllApps);
    viewAllCustButton.addEventListener('click', viewAllCustomers);
    viewAllTransButton.addEventListener('click', viewAllTrans);
    logoutButton.addEventListener('click', logoutFunction);
}

function logoutFunction(){
    let xhr = new XMLHttpRequest();
    let url = "employeeLogin";

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

function viewAllApps(){
    let xhr = new XMLHttpRequest();
    let url = "employeeMenu";
    let allApps = "allApplications";

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
                let oldTableHead = document.getElementById("oldTableHead");
                let oldTableBody = document.getElementById("oldTableBody");
                let newTableHead = document.createElement("thead");
                let newTableBody = document.createElement("tbody");
                newTableHead.id = "oldTableHead";
                newTableBody.id = "oldTableBody";
                setApplicationHeaders(newTableHead);
                oldTableHead.parentNode.replaceChild(newTableHead, oldTableHead);
                let appList = JSON.parse(xhr.responseText);
                console.log(appList);
                appList.forEach(element => {
                    displayApplications(element, newTableBody);                        
                });
                oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.manageType = allApps;
    xhr.send(
        JSON.stringify(newClient)
    );

}

function viewAllTrans(){
    let xhr = new XMLHttpRequest();
    let url = "employeeMenu";
    let allTrans = "allTransactions";

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
                let oldTableHead = document.getElementById("oldTableHead");
                let oldTableBody = document.getElementById("oldTableBody");
                let newTableHead = document.createElement("thead");
                let newTableBody = document.createElement("tbody");
                newTableHead.id = "oldTableHead";
                newTableBody.id = "oldTableBody";
                setCustomerHeaders(newTableHead);
                oldTableHead.parentNode.replaceChild(newTableHead, oldTableHead);
                let custList = JSON.parse(xhr.responseText);
                
                custList.forEach(element => {
                    displayCustomers(element, newTableBody);                        
                });
                oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.manageType = allTrans;
    xhr.send(
        JSON.stringify(newClient)
    );

}

function viewAllCustomers(){
    let xhr = new XMLHttpRequest();
    let url = "employeeMenu";
    let allCust = "allCustomers";

    xhr.onreadystatechange = function(){
        switch(xhr.readyState){
            case 0:
                console.log("nothing, not initialized yet!");
                console.log("Value:")
                console.log(xhr);
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
            case 4:{
                console.log("FINISHED!!!!");   
                let oldTableHead = document.getElementById("oldTableHead");
                let oldTableBody = document.getElementById("oldTableBody");
                let newTableHead = document.createElement("thead");
                let newTableBody = document.createElement("tbody");
                newTableHead.id = "oldTableHead";
                newTableBody.id = "oldTableBody";
                setCustomerHeaders(newTableHead);
                oldTableHead.parentNode.replaceChild(newTableHead, oldTableHead);
                let custList = JSON.parse(xhr.responseText);
                
                custList.forEach(element => {
                    displayCustomers(element, newTableBody);                        
                });
                oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
            }
        }
    }
    xhr.open("POST", url);
    let newClient = {};
    newClient.manageType = allCust;
    xhr.send(
        JSON.stringify(newClient)
    );
}

function setApplicationHeaders(tableHead){
    let tableRow = document.createElement("tr");
    let radioInputCol = document.createElement("th");
    let custIdCol = document.createElement("th");
    let accountTypeCol = document.createElement("th");
    let accountBalanceCol = document.createElement("th");
    let approveOrRejectButtonCol = document.createElement("th");

    tableRow.appendChild(radioInputCol);
    tableRow.appendChild(custIdCol);
    tableRow.appendChild(accountTypeCol);
    tableRow.appendChild(accountBalanceCol);
    tableRow.appendChild(approveOrRejectButtonCol);

    tableHead.appendChild(tableRow);

    custIdCol.innerHTML = "Cust. ID";
    accountTypeCol.innerHTML = "Account Type";
    accountBalanceCol.innerHTML = "Starting Balance";
}

function setCustomerHeaders(tableHead){
    let tableRow = document.createElement("tr");
    let radioInputCol = document.createElement("th");
    let custIdCol = document.createElement("th");
    let custUsernameCol = document.createElement("th");
    let custFirstCol = document.createElement("th");
    let custLastCol = document.createElement("th");
    let accInfoButtonCol = document.createElement("th");

    tableRow.appendChild(radioInputCol);
    tableRow.appendChild(custIdCol);
    tableRow.appendChild(custUsernameCol);
    tableRow.appendChild(custFirstCol);
    tableRow.appendChild(custLastCol);
    tableRow.appendChild(accInfoButtonCol);

    tableHead.appendChild(tableRow);

    custIdCol.innerHTML = "Cust. ID";
    custUsernameCol.innerHTML = "Cust. Username";
    custFirstCol.innerHTML = "Cust. First Name";
    custLastCol.innerHTML = "Cust. Last Name";
}

function displayApplications(appValues, tableBody){ 
    let tableRow = document.createElement("tr");
    let radioInputCol = document.createElement("td");
    let custIdCol = document.createElement("td");
    let accountTypeCol = document.createElement("td");
    let accountBalanceCol = document.createElement("td");
    let approveOrRejectButtonCol = document.createElement("td");

    tableRow.appendChild(radioInputCol);
    tableRow.appendChild(custIdCol);
    tableRow.appendChild(accountTypeCol);
    tableRow.appendChild(accountBalanceCol);
    tableRow.appendChild(approveOrRejectButtonCol);

    tableBody.appendChild(tableRow);
    custIdCol.innerHTML = appValues.accountID;
    accountTypeCol.innerHTML = appValues.type;
    accountBalanceCol.innerHTML = appValues.balance;
}

function displayCustomers(customerValues, tableBody){ 
    let tableRow = document.createElement("tr");
    let radioInputCol = document.createElement("td");
    let custIdCol = document.createElement("td");
    let custUsernameCol = document.createElement("td");
    let custFirstCol = document.createElement("td");
    let custLastCol = document.createElement("td");
    let accInfoButtonCol = document.createElement("td");

    tableRow.appendChild(radioInputCol);
    tableRow.appendChild(custIdCol);
    tableRow.appendChild(custUsernameCol);
    tableRow.appendChild(custFirstCol);
    tableRow.appendChild(custLastCol);
    tableRow.appendChild(accInfoButtonCol);

    tableBody.appendChild(tableRow);
    custIdCol.innerHTML = customerValues.bank_membership_number;
    custUsernameCol.innerHTML = customerValues.username;
    custFirstCol.innerHTML = customerValues.first_name;
    custLastCol.innerHTML = customerValues.last_name;
}

function displayAccounts(accountValues){
    let table = document.getElementById("accountTable");
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