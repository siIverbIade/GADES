$(document).ready(function () {

	$("#estatisticageral").click(function() {
		$("#forms").load("/dme/dashboard");
	});
	
	$("#cadfuncionario").click(function() {
		$("#forms").load("/dme/funcionario");
	});
	
});

$(window).on('load', function() {
	$("#forms").load("/dme/dashboard")
});