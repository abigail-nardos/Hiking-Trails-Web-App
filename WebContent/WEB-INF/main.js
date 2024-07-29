//TO DO: document searchTrails method
function searchTrails(table) {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("trailFilter");
	filter = input.value.toUpperCase();
	table = document.getElementById("trailTable");
	tr = table.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		
		if (td) {
			txtValue = td.textContent || td.innerText;
			
		    if (txtValue.toUpperCase().indexOf(filter) > -1) {
		    	tr[i].style.display = "";
			} else {
			      tr[i].style.display = "none";
			}
		}       
	}
}

//TO DO: document searchRecords method
function searchRecords() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("recordFilter");
	filter = input.value.toUpperCase();
	table = document.getElementById("userRecordTable");
	tr = table.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		
		if (td) {
			txtValue = td.textContent || td.innerText;
			
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
			  tr[i].style.display = "";
			} else {
			  tr[i].style.display = "none";
			}
		}       
	}
}
	
//TO DO: document searchUsers method
function searchUsers() {
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("usersFilter");
	filter = input.value.toUpperCase();
	table = document.getElementById("userTable");
	tr = table.getElementsByTagName("tr");
	
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		
		if (td) {
			txtValue = td.textContent || td.innerText;
			
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
			  tr[i].style.display = "";
			} else {
			  tr[i].style.display = "none";
			}
		}       
	}
}
	
//TO DO: document openRecordForm method
function openRecordForm() {
	document.getElementById("recordForm").style.display = "block";
}

//TO DO: document closeRecordForm method
function closeRecordForm() {
	document.getElementById("recordForm").style.display = "none";
}

//TO DO: document openUserForm method
function openUserForm() {
	document.getElementById("userForm").style.display = "block";
}

//TO DO: document closeUserForm method
function closeUserForm() {
	document.getElementById("userForm").style.display = "none";
}