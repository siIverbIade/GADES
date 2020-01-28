$(document).ready(function () {
	$("#inputsenha2").focusin(function() {
		$("#inputsenha2").prop("placeholder", "")
	});
	
	$("#inputsenha2").focusout(function() {
		$("#inputsenha2").prop("placeholder", "••••••")
	});
	
	if ($('#selfuncionario2').val() === '') {
		$("#inputlogin2").prop("disabled", true);
		$("#inputsenha2").prop("disabled", true);
		$("#editarUsuario").prop("disabled", true);
		$("#perfis2").prop("hidden", true);
		$(".form-check-label2").prop("disabled", true);
	}
	$('#editarUsuario').click(function() {
		var form = $('#edit_usu_form');
		$.ajax({
		      url: form.attr('action'),
		      data: form.serialize(),
		      type: "post",
		      success: function(result) {
		    	  $("#layout2").html(result);
		      }, error: function(error) {
		    	  $("#layout2").html(error);
		      }
		})
	});
	
	$('#selfuncionario2').change(function(data) {

		$('.erro-msg').html('');
		
		if (data.target.value === '') {
			$("#inputlogin2").prop("disabled", true);
			$("#inputsenha2").prop("disabled", true);
			$("#editarUsuario").prop("disabled", true);
			$("#perfis2").prop("hidden", true);
			$(".form-check-label2").prop("disabled", true);
		}else {
			$("#inputlogin2").prop("disabled", false);
			$("#inputsenha2").prop("disabled", false);
			$("#editarUsuario").prop("disabled", false);
			$("#perfis2").prop("hidden", false);
			$(".form-check-label2").prop("disabled", false);
			
		}
	})
	
	$('#cancelarUsuario').click(function() {
		$("#layout2").load("/usuario/consultar");
	})

});