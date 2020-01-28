/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var com;
(function (com) {
    var iala;
    (function (iala) {
        var cpd;
        (function (cpd) {
            var script;
            (function (script) {
                /**
                 * This class is used within the webapp/index.html file.
                 * @class
                 */
                var SelTurnos = (function () {
                    function SelTurnos() {
                    }
                    SelTurnos.main = function (args) {
                        $(document).ready(function () {
                            $(".check").change(function (JQueryEventObject) {
                            	var tnome=0;
                            	var values = [];
                            	$(".check:checked").each(function() {
                            		values.push('"'+ $(this).prop("id")+'"');
                            	})
                            	values="["+values+"]";
                            	$.getJSON("/anoletivo/" + $("#ano").val().toString(), function(json) {
                            		//alert(json.nome);
                            		//tnome = json.nome;

                            	});
                            	$.ajax({url: "http://localhost:8080/graphql",
                                    contentType: "application/json",type:'PUT',
                                    headers: {"X-HTTP-Method-Override": "PUT"},
                                    data: JSON.stringify({ 
                                    	query:{
                                    	sayHello(name:"${name}")
                                    	}
                                    }),
                                 });
                                return null;
                            });
                            return null;
                        });
                    };
                    return SelTurnos;
                }());
                script.SelTurnos = SelTurnos;
                SelTurnos["__class"] = "com.cpd.script.SelTurnos";
            })(script = cpd.script || (cpd.script = {}));
        })(cpd = iala.cpd || (iala.cpd = {}));
    })(iala = com.iala || (com.iala = {}));
})(com || (com = {}));
com.cpd.script.SelTurnos.main(null);
//# sourceMappingURL=bundle.js.map