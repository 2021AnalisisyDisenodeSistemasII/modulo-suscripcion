function createAccount() {
    var naturalClientId = $('#nroId').val();
    var companyClientId = $('#nit').val();
    var type = $('#tipo').val();
    var branch = $('#sucursal').val();

    if (!naturalClientId && !companyClientId) {
        Swal.fire('Cuidado', 'Debe ingresar un documento o NIT para la cuenta', 'warning')
        return;
    }

    if (naturalClientId && companyClientId) {
        Swal.fire('Cuidado', 'Ingrese el documento O el NIT del cliente. Un cliente no puede ser Persona y Empresa a la vez.', 'warning')
        return;
    }

    if (!branch) {
        Swal.fire('Cuidado', 'Debe ingresar una sucursal', 'warning')
        return;
    }

    if (!type) {
        Swal.fire('Cuidado', 'Debe seleccionar un tipo de cuenta', 'warning')
        return;
    }

    create(naturalClientId, companyClientId, type, branch);
}

function create(naturalClientId, companyClientId, type, branch) {
    var clientId = naturalClientId ? naturalClientId : companyClientId;
    $.ajax({
        type: 'POST',
        url: `http://localhost:8080/persons/accounts/create`,
        dataType: 'json',
        data: {
            clientId: clientId,
            type: type,
            sucursalId: branch
        },
        success: function (created) {
            if (created) {
                Swal.fire('Correcto', 'La cuenta ha sido creada para el usuario ' + clientId, 'success')
                cleanFields();
            } else {
                Swal.fire('Error', 'Ha ocurrido un error', 'error')
            }
        },
        error: function (_) {
            Swal.fire('Error', 'Ha ocurrido un problema', 'error')
        }
    });
}

function cleanFields() {
    $('#nroId').val('');
    $('#nit').val('');
    $('#sucursal').val('');
    $('#tipo').val('');
}