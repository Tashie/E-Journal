$(document).ready(function () {
        $('#positionsTable').dataTable({ "pagingType": "full_numbers" });
});


function editPosition(id, name) {
    $("#posId").val(id);
    $("#posNameId").val(name);
}