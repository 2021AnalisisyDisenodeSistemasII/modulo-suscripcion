function validateAccount() {
    var clientId = $('#nroId').val();
    var accountId = $('#cuentaId').val();
    if (!clientId) {
        Swal.fire('Cuidado', 'Debe ingresar un documento o NIT para validar', 'warning')
        return;
    }
    if (!accountId) {
        Swal.fire('Cuidado', 'Debe ingresar un ID de cuenta para validar', 'warning')
        return;
    }
    validate(clientId, accountId);
}

function validate(clientId, accountId) {
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/persons/accounts/check?client_id=${clientId}&account_id=${accountId}`,
        dataType: 'json',
        success: function (belongs) {
            if (belongs) {
                Swal.fire('Correcto', 'La cuenta pertenece al documento ingresado', 'success')
            } else {
                Swal.fire('Error', 'La cuenta pertenece no al documento ingresado', 'error')
            }
            cleanFields();
        },
        error: function (xhr) {
            Swal.fire('Error', 'Ha ocurrido un problema', 'error')
        }
    });
}

function cleanFields() {
    $('#nroId').val('');
    $('#cuentaId').val('');
}