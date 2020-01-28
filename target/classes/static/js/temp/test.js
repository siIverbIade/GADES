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
                var AnoTurnos = (function () {
                    function AnoTurnos() {
                    }
                    AnoTurnos.main = function (args) {
                        $(document).ready(function () {
                            $("#ano").change(function (JQueryEventObject) {
                                $(".check").prop("checked", false);
                                if ($("#ano").val().toString() === "") {
                                    $(".check").prop("disabled", true);
                                }
                                else {
                                	const ano_id= $("#ano").val().toString();
                                	const query = `{
                                		    obterAnoLetivo(id: $ano_id) {
                                		    	id
                                		      	nome
                                		      	turnos
                                		    }
                                		  }`;
                                	const url = "http://localhost:8080/graphql?query=";
                                	alert(url + query);
                                    $.getJSON(url + query, function (result, b, c) {
                                        $.each(result, function (i, field) {
                                            if (i === "turnos") {
                                                var turnos = field.toString().split(",");
                                                var k = 0;
                                                for (k = 0; k < 4; k++) {
                                                    {
                                                        $("#" + turnos[k]).prop("checked", true);
                                                    }
                                                    ;
                                                }
                                            }
                                            return null;
                                        });
                                        return null;
                                    });
                                    $(".check").prop("disabled", false);
                                }
                                return null;
                            });
                            return null;
                        });
                    };
                    return AnoTurnos;
                }());
                script.AnoTurnos = AnoTurnos;
                AnoTurnos["__class"] = "com.cpd.script.AnoTurnos";
            })(script = cpd.script || (cpd.script = {}));
        })(cpd = iala.cpd || (iala.cpd = {}));
    })(iala = com.iala || (com.iala = {}));
})(com || (com = {}));
com.cpd.script.AnoTurnos.main(null);
//# sourceMappingURL=bundle.js.map