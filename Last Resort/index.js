const urlBase = 'Database Project Undetermined URL';
const extension = '.php';

let userId = 0;
let globalEventID = 0;
let firstName = "";
let lastName = "";
let numLoad = 15;
let numLoaded = 0;


function setFormMessage(formElement, type, message) {
    const messageElement = formElement.querySelector(".form__message");

    messageElement.textContent = message;
    messageElement.classList.remove("form__message--success", "form__message--error");
    messageElement.classList.add(`form__message--${type}`);
}

//validate email is correct form
function doValidateEmail(email) 
{
    atpos = email.indexOf("@");
    dotpos = email.lastIndexOf(".");
    finalChar = email.length;

    // Check to see if there is a character after the "."
    if ((finalChar - 1) == dotpos) 
    {
        return false;
    }

    // Check to see if there is a character before and after "@"
    if (atpos < 1 || ( dotpos - atpos < 2 )) 
    {
        return false;
    }

    return true;
}

//login button functionality
function doLogin() {
    userId = 0;
    firstName = "";
    lastName = "";

	let login = document.getElementById("loginUser").value;
    let password = document.getElementById("loginPass").value;

	// var hash = md5( password );

    document.getElementById("loginResult").innerHTML = "";

    let tmp = {login:login,password:password};

	// Converting the simple string version of the object to actual JSON.
    let jsonPayload = JSON.stringify( tmp );

	// Setting up the full link for the Login API query.
    let url = urlBase + '/Login' + extension;

	// Creating a new API request object.
    let xhr = new XMLHttpRequest();

	// Giving the API object the link to the Login API and other necessary info.
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");

    try
    {
		// We send the API query and wait for it to return.
		// The API queries are "asynchronous", which means that other
		// code can run in the background while the query is pending.
		// Once the API query returns and is noted as ready, this function triggers.
        xhr.onreadystatechange = function()
        {
			// This tells if the query has returned and everything is correct with it.
            if (this.readyState === 4 && this.status === 200)
            {
				// Parsing the returned information from the query to a JSON object.
                let jsonObject = JSON.parse( xhr.responseText );

				// Getting the user's ID from the JSON object and storing it.
                userId = jsonObject.id;

				// If the user ID is less than 1, the API query found that no user exists in the database
				// with the given username and password.
                if( userId < 1 )
                {
                    document.getElementById("loginResult").innerHTML = "User/Password combination incorrect";
					// If we did not find any user, we exit the function.
                    return;
                }

				// Getting here means we successfully found a user in the database, so we save the user's information.
                firstName = jsonObject.firstName;
                lastName = jsonObject.lastName;

				// We save a cookie so that the user that logged in will have a max of 20 minutes on the website before
				// automatically getting logged out.
                saveCookie();

                window.location.href = "index.html";
            }
        };
        xhr.send(jsonPayload);
    }
    catch(err)
    {
		// This is just catching any error that may have occurred while sending a query to the API.
        document.getElementById("loginResult").innerHTML = err.message;
    }
}

//register account functionality
function doRegister(){
    let username = document.getElementById("registerUsername").value;
    let password = document.getElementById("registerPassword").value;
    let firstName = document.getElementById("registerFirstName").value;
    let lastName = document.getElementById("registerLastName").value;

	if (username == "" || password == "" || firstName == "" || lastName == ""){
		console.log("A required field for registration is missing.");
		return;
	}

    let temp = {firstName:firstName,lastName:lastName,login:username,password:password};
    let jsonPayload = JSON.stringify( temp );

    console.log(JSON.stringify( temp ));
    let url = urlBase + '/Register' + extension;
    let xhr = new XMLHttpRequest();

    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");

    try{
        console.log("Hit the try");
        xhr.send(jsonPayload)
		console.log("json payload in the air");
        xhr.onreadystatechange = function(){
            if (this.readyState === 4 && this.status === 200){
                console.log("Status good!");
                let jsonObject = JSON.parse( xhr.responseText );
                error = jsonObject.error;

                if (error != ""){
                    console.log("Failed...returning");
                    return;
                }

                console.log("Success!");

                saveCookie();
                setTimeout(function(){document.location.href = "login.html"},200);
            }

        }
    }
    catch (err){
        document.getElementById("loginResult").innerHTML = err.message;
    }
}

function saveCookie(){
	let minutes = 20;
	let date = new Date();
	date.setTime(date.getTime()+(minutes*60*1000));	
	document.cookie = "firstName=" + firstName + ",lastName=" + lastName + ",userId=" + userId + ";expires=" + date.toGMTString();
}

function readCookie(){
	userId = -1;
	let data = document.cookie;
	let splits = data.split(",");
	for(var i = 0; i < splits.length; i++) 
	{
		let thisOne = splits[i].trim();
		let tokens = thisOne.split("=");
		if( tokens[0] == "firstName" )
		{
			firstName = tokens[1];
		}
		else if( tokens[0] == "lastName" )
		{
			lastName = tokens[1];
		}
		else if( tokens[0] == "userId" )
		{
			userId = parseInt( tokens[1].trim() );
            console.log(userId)
		}
	}
	
	if( userId < 0 )
	{
		window.location.href = "index.html";
	}
	else
	{
        doWelcome();
	}
}

//logout button functionality
function doLogout(){
	userId = 0;
	firstName = "";
	lastName = "";
	document.cookie = "firstName= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
	window.location.href = "login.html";
}

//add event to table
function doAddEvent(){
	let firstName = document.getElementById("addFirstName").value;
    let lastName = document.getElementById("addLastName").value;
    let phone = document.getElementById("addPhone").value;
    let email = document.getElementById("addEmail").value;

    let firstNameField = document.getElementById("addFirstName").value;
    let lastNameField = document.getElementById("addLastName").value;
    let phoneField = document.getElementById("addPhone").value;
    let emailField = document.getElementById("addEmail").value;

    document.getElementById("addEventResult").innerHTML = "";
    
    let temp = {firstName:firstName, lastName:lastName, email:email, phone:phone, userID:userId}
    let jsonPayload = JSON.stringify(temp);

    let link = urlBase + "/Add" + extension;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", link, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");

    try {
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200){
                document.getElementById("addEventForm").reset();
                document.getElementById("closeAddEventButton").click();
                closeAddEvent();
                doSearch(true);
            }
        };
        console.log(jsonPayload);
        xhr.send(jsonPayload);
    }
    catch(err) { 
        document.getElementById("addEventResult").innerHTML = err.message;
    }
}

//search for Event
function doSearch(reset) {//to be edited into real time search instead of on click search
	let srch = document.getElementById("searchBar").value;
	document.getElementById("searchEventResult").innerHTML = "";
	
	let EventList = [];

    if (reset)
    {
        numLoaded = 0;
    }

	let tmp = {search:srch,userID:userId,numItems:numLoad,offset:numLoaded};
	let jsonPayload = JSON.stringify( tmp );

	let url = urlBase + '/Search' + extension;
	
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
	try
	{
		xhr.onreadystatechange = function() 
		{
			if (this.readyState == 4 && this.status == 200) 
			{
				document.getElementById("searchEventResult").innerHTML = "Event(s) has been retrieved";
				let jsonObject = JSON.parse( xhr.responseText );

				EventList = jsonObject.results;
                doCreateTable(EventList, reset);
                console.log(JSON.stringify(jsonObject));
			}
		};
		xhr.send(jsonPayload);
	}
	catch(err)
	{
		document.getElementById("searchEventResult").innerHTML = err.message;
	}
	
}
// Add search results to search table
function doCreateTable(userEvents, reset) {
    let table = document.getElementById('EventTable');
    if (reset)
    {
        console.log("Clearing Table...");
        clearTable(table);
    }

    console.log("Adding Events...");
    for (let i = 0; i < userEvents.length; i++)
    {
        let Event = userEvents[i];
        let row = table.insertRow();
        let first = row.insertCell(0);
        let last = row.insertCell(1);
        let email = row.insertCell(2);
        let phone = row.insertCell(3);
        let options = row.insertCell(4);

        let EventID = Event.ID;
        let firstName = Event.firstName;
        let lastName = Event.lastName;
        let newEmail = Event.email;
        let newPhone = Event.phone;

        first.innerHTML = firstName;
        last.innerHTML = lastName;
        email.innerHTML= newEmail;
        phone.innerHTML= newPhone;
        options.innerHTML='<button type="button" id="editButton" class="editButton" onclick="openEditEvent(\''+ EventID + '\',\'' + firstName + '\', \'' + lastName + '\', \''+ newEmail + '\',\'' + newPhone + '\');">Edit</button><button type="button" id="deleteButton" class="deleteButton" onclick="doDeleteEvent('+ EventID +');">Delete</button>'
        numLoaded = numLoaded + 1;
    }

}

// Clear results in search table
function clearTable(table){

    let rowCount = table.rows.length;

    for (var i = rowCount - 1; i > 0; i--)
    {
        table.deleteRow(i);
    }
}

// If scroll to bottom, lazy load !
function lazyload()
{
    table = document.getElementById('EventList');
    if((table.scrollTop + table.offsetHeight) >=  table.scrollHeight)
    {
        console.log("lazy loading...");
        doSearch(false);
    }
}

//edit Event in table
function doEditEvent(EventID) {//Change variables according to Events.html and API endpoints
    let firstName = document.getElementById("editFirstName").value;
    let lastName = document.getElementById("editLastName").value;
    let phone = document.getElementById("editPhone").value;
    let email = document.getElementById("editEmail").value;

    let temp = {firstName:firstName,lastName:lastName,phone:phone,email:email,userID:userId,ID:EventID};
    let jsonPayload = JSON.stringify(temp);
    let url = urlBase + '/Edit' + extension;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");

    try {
        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                closeEditEvent();
                doSearch(true);
            }
        }
        xhr.send(jsonPayload);
    }
    catch (err){
        console.log(err);
    }
}

//Unhide Edit Event Form
function openEditEvent(EventID, firstName, lastName, email, phone){
    const editEventForm = document.querySelector("#editEventForm");
    const formVisibitly = document.querySelector("#accessUIDiv");
    formVisibitly.classList.remove("form--hidden");
    editEventForm.classList.remove("form--hidden");
    document.getElementById("editFirstName").value = firstName;
    document.getElementById("editLastName").value = lastName;
    document.getElementById("editEmail").value = email;
    document.getElementById("editPhone").value = phone;
    document.getElementById("editEventButton").outerHTML = '<button type="button" id="editEventButton" class="buttons" onclick="doEditEvent('+ EventID +');">Edit Event</button>'
}

//Hide Edit Event Form
function closeEditEvent(){
    const editEventForm = document.querySelector("#editEventForm");
    const formVisibitly = document.querySelector("#accessUIDiv");
    formVisibitly.classList.add("form--hidden");
    editEventForm.classList.add("form--hidden");
}

//Unhide Add Event Form
function openAddEvent(){
    const addEventForm = document.querySelector("#addEventForm");
    const formVisibitly = document.querySelector("#accessUIDiv");
    formVisibitly.classList.remove("form--hidden");
    addEventForm.classList.remove("form--hidden");
}

//Hide Add Event Form
function closeAddEvent(){
    const addEventForm = document.querySelector("#addEventForm");
    const formVisibitly = document.querySelector("#accessUIDiv");
    formVisibitly.classList.add("form--hidden");
    addEventForm.classList.add("form--hidden");
}

//delete Event from table
function doDeleteEvent(EventID){ //Change variables according to Events.html
	if (confirm("Are you sure you want to delete this Event?")){
    } else {
      return;
    }

    var jsonPayload = '{"userID" : "' + EventID + '"}';
    var url = urlBase + '/Delete' + extension;
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json; charset=UTF-8");
    try
    {
        xhr.onreadystatechange = function() 
        {
            if (this.readyState == 4 && this.status == 200) 
            {
                doSearch(true);
            }
        };
        xhr.send(jsonPayload);
    }

    catch(err)
    {
        console.log(err);
    }
}

//welcomes user after login
function doWelcome(){
    document.getElementById("banner").innerHTML = "Welcome, " + firstName + "!";
}

//removes error once textfield is changed
function clearInputError(inputElement) {
    inputElement.classList.remove("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = "";
}

//Adding error message
function setInputError(inputElement, message) {
    inputElement.classList.add("form__input--error");
    inputElement.parentElement.querySelector(".form__input-error-message").textContent = message;
}


document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login");
    const registerForm = document.querySelector("#register");
	const addEventForm = document.querySelector("#addEventForm");
	const editEventForm = document.querySelector("#editEventForm");

    //Hide login form, display register form
    document.querySelector("#linkRegisterAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form--hidden");
        registerForm.classList.remove("form--hidden");
    });

    //Hide register form, display login form
    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.remove("form--hidden");
        registerForm.classList.add("form--hidden");
    });

    //set requirements on username registration
    document.querySelectorAll(".form__input").forEach(inputElement => {
        inputElement.addEventListener("blur", e => {
            if (e.target.id === "registerUsername" && e.target.value.length > 0 && e.target.value.length < 5) {
                setInputError(inputElement, "Username must be at least 5 characters long");
            }
        });
        inputElement.addEventListener("input", e => {
            clearInputError(inputElement);
        });
    });

    //set requirements on password registration
    document.querySelectorAll(".form__input").forEach(inputElement => {
        inputElement.addEventListener("blur", e => {
            if (e.target.id === "registerPassword" && e.target.value.length > 0 && e.target.value.length < 8) {
                setInputError(inputElement, "Password must be at least 8 characters long");
            }
        });
        inputElement.addEventListener("input", e => {
            clearInputError(inputElement);
        });
    });
});