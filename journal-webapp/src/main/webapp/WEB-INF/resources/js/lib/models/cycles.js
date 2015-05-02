$(document).ready(function () {
    $('#cyclesTable').dataTable({ "pagingType": "full_numbers" });
});


function editCycle(id, name) {
    $("#cycleId").val(id);
    $("#cycleNameId").val(name);
}

function deleteCycleById(cycleId) {
    alert(cycleId);
}