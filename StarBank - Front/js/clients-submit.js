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
	/*if (address && nit) {
		alert('Error, ingrese solo la información necesaria. Un cliente no puede ser natural y empresa al mismo tiempo');
		return;
	}*/
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
				alert('La persona ha sido agregada');
			}
			cleanFields();
		},
		dataType: 'json',
		error: function (xhr) {
			console.log(xhr);
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