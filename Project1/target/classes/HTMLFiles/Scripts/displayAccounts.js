

function addRow(Account, Customer){
    let table = document.getElementById("account_table");
    let tableRow = document.createElement("tr");
    let accountIdCol = document.createElement("td");
    let accountTypeCol = document.createElement("td");
    let accountBalanceCol = document.createElement("td");
    let customerIdCol = document.createElement("td");
    let customerFirstCol = document.createElement("td");
    let customerLastCol = document.createElement("td");
    let customerUsernameCol = document.createElement("td");

    tableRow.appendChild(customerIdCol);
    tableRow.appendChild(customerFirstCol);
    tableRow.appendChild(customerLastCol);
    tableRow.appendChild(customerUsernameCol);
    tableRow.appendChild(accountIdCol);
    tableRow.appendChild(accountTypeCol);
    tableRow.appendChild(accountBalanceCol);
    table.appendChild(tableRow);

    customerIdCol.innerHTML = Customer.customer_id;
    customerFirstCol.innerHTML = Customer.customer_first_name;
    customerLastCol.innerHTML = Customer.customer_last_name;
    customerUsernameCol.innerHTML = Customer.customer_username;

    accountIdCol.innerHTML = Account.account_number;
    accountTypeCol.innerHTML = Account.account_type;
    accountBalanceCol.innerHTML = Account.account_balance;

}