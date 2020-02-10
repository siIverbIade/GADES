$(document).ready(function () {
	
	$("#estatisticageral").click(function() {
		$("#forms").load("/dln/dashboard");
	});
	
	$("#unidadeescolar").click(function() {
		$("#forms").load("/dln/escola");
	});
	
	$("#calendarioescolar").click(function() {
		$("#forms").load("/dln/calendarioescolar");
	});
	
});

$(window).on('load', function() {
	$("#forms").load("/dln/dashboard")
});