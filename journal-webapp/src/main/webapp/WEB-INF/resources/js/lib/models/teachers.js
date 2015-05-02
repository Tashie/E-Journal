$(document).ready(function () {
    $('#teachersTable').dataTable({ "pagingType": "full_numbers" });
});


function editTeachers(id, lastname, firstname, middlename, birthdate, address) {
    $("#posId").val(id);
    $("#posLastnameId").val(lastname);
    $("#posFirstnameId").val(firstname);
    $("#posMiddlenameId").val(middlename);
    $("#posBirthdateId").val(birthdate);
    $("#posAddressId").val(address);
}
