$(document).ready(function () {
	
	$("#estatisticageral").click(function() {
		$("#forms").load("/dln/dashboard");
	});
	
	$("#unidadeescolar").click(function() {
		$("#forms").load("/dln/escola");
	});
	
	$("#anoletivo").click(function() {
		$("#forms").load("/dln/anoletivo");
	});
	
});

$(window).on('load', function() {
	$("#forms").load("/dln/dashboard")
});