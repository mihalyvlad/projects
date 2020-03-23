$(document).ready(function() {

    $('.table.editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr("href")
        $.get(href, function (nationality, status) {
            $('#idEdit').val(nationality.Id)
            $('#nameEdit').val(nationality.name);
            $('#capitalEdit').val(nationality.capital);
            $('#updatedByEdit').val(nationality.updatedBy);
            $('#updatedOnEdit').val(nationality.updatedOn);


        })


        $('#editModal').modal();
    });


$('.table .deleteButton').on('click',function(event){
    event.preventDefault();
    var href=$(this).attr('href');
    $('#deleteModal #deleteId').attr('href',href)
    $('#deleteModal').modal();
});

});