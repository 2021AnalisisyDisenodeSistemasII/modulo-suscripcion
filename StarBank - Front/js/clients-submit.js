function saveClient() {
	var clientType = '';
	var identificationType = $('#tipoId').val();
	var identification = $('#nroId').val();
	var contactName = $('#nombre').val();
	var contactOccupation = $('#ocupacion').val();
	var contactNumber = $('#telefono').val();
	var address = $('#direccion').val();
	var nit = $('#nit').val();
	var companyName = $('#nombreEmpresa').val();
	var companyAddress = $('#direccionEmpresa').val();
	var companySector = $('#sectorComercial').val();
	if (!identificationType || !identification || !contactName || !contactOccupation || !contactNumber) {
		Swal.fire('Error', 'Ingrese los campos necesarios', 'error')
		return;
	}
	if (!address && (!nit || !companyName || !companyAddress || !companySector)) {
		Swal.fire('Error', 'Ingrese los campos necesarios', 'error')
		return;
	}
	if (address && (nit || companyName || companyAddress || companySector)) {
		Swal.fire('Error', 'Un cliente no puede ser natural y empresa al mismo tiempo', 'error')
		return;
	}
	var data = {
		identificationType: identificationType,
		identification: identification,
		contactName: contactName,
		contactOccupation: contactOccupation,
		contactNumber: contactNumber
	}
	if (address) {
		clientType = 'natural';
		data['address'] = address;
	} else if (nit) {
		clientType = 'company';
		data['nit'] = nit;
		data['companyName'] = companyName;
		data['companyAddress'] = companyAddress;
		data['companySector'] = companySector;
	}
	createClient(clientType, data);
}

function createClient(clientType, data) {
	$.ajax({
		type: 'POST',
		url: `http://localhost:8080/persons/${clientType}`,
		data: data,
		success: function (results) {
			if (results) {
				Swal.fire('Éxito', 'La persona ha sido agregada', 'success')
			}
			cleanFields();
		},
		dataType: 'json',
		error: function (xhr) {
			if (xhr.status == 400) {
				if ($('#nit').val() === '') {
					Swal.fire('Error', 'Ya existe un cliente registrado con el documento ' + $('#nroId').val(), 'error')
				} else {
					Swal.fire('Error', 'Ya existe un cliente registrado con el NIT ' + $('#nit').val(), 'error')
				}
			} else {
				Swal.fire('Error', 'Ha ocurrido un problema', 'error')
			}
		}
	});
}

function cleanFields() {
	$('#tipoId').val('');
	$('#nroId').val('');
	$('#nombre').val('');
	$('#ocupacion').val('');
	$('#telefono').val('');
	$('#direccion').val('');
	$('#nit').val('');
	$('#nombreEmpresa').val('');
	$('#direccionEmpresa').val('');
	$('#sectorComercial').val('');
}