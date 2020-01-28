$(document).ready(function () {
	if ($('#selfuncionario').val() !== '') {
		$("#inputlogin").prop("disabled", false);
		$("#inputsenha").prop("disabled", false);
		$("#salvarUsuario").prop("disabled", false);
		$("#perfis").prop("hidden", false);
		$(".form-check-label").prop("disabled", false);
	}
	$('#salvarUsuario').click(function() {
		var form = $('#cad_usu_form');
		$.ajax({
		      url: form.attr('action'),
		      data: form.serialize(),
		      type: "post",
		      success: function(result) {
		    	  $("#layout2").load("/usuario/consultar");
		    	  $("#layout").html(result);
		      }, error: function(error) {
		    	  $("#layout").html(error);
		    	  // alert("Erro ao salvar");
		      }
		})
	});
	$('#selfuncionario').change(function(data) {

		$('.erro-msg').html('');
		
		if (data.target.value === '') {
			$("#inputlogin").prop("disabled", true);
			$("#inputsenha").prop("disabled", true);
			$("#salvarUsuario").prop("disabled", true);
			$("#perfis").prop("hidden", true);
			$(".form-check-label").prop("disabled", true);
		}else {
			$("#inputlogin").prop("disabled", false);
			$("#inputsenha").prop("disabled", false);
			$("#salvarUsuario").prop("disabled", false);
			$("#perfis").prop("hidden", false);
			$(".form-check-label").prop("disabled", false);
			
		}
	})
});