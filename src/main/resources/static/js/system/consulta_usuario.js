$(document).ready(function () {
	$('#dataTable').DataTable();
	
	$(".del_usu_form").click(function() {

		var form = $(this);
		
		if (confirm("Tem certeza que deseja excluir o usuário " + form.val() + "?")) {
			$.ajax({
		      url: form.attr('action'),
		      data: form.serialize(),
		      type: "post",
		      success: function(result) {
		    	  $("#layout2").load("/usuario/consultar");
		      }, error: function(error) {
		    	  $("#layout2").html(error);
		      }
			})
		}
	});
	$(".edit_usu_form").click(function() {

		var form = $(this);
		
		$.ajax({
			url: form.attr('action'),
		    data: form.serialize(),
		    type: "get",
		    success: function(result) {
		    	  console.log(result);
		    	  $("#layout2").html(result);
		    }, error: function(error) {
		    	$("#layout").html(error);
		    }
		})
	});
});