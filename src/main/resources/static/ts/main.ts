//import * as $ from 'jquery'
//import {Con} from './funcs'
import { request  } from 'graphql-request'

$(document).ready(() => {

    const uri = 'http://localhost:8080/graphql'
    const query = '{obterAnosLetivos {id nome turnos semanas segmentos {id nome} }}'
    let dropdown = $('#ano');
    dropdown.empty();
    dropdown.append('<option selected="true"> Selecione o Ano Letivo </option>');
    dropdown.prop('selectedIndex', 0);

    //Populate dropdown with list of provinces
    request(uri, query).then(data => {
      $.each(data.obterAnosLetivos, (i, anoLetivo) => {
          console.log(anoLetivo.nome)
          console.log(anoLetivo.id)
          dropdown.append($('<option></option>').val(anoLetivo.id).html(anoLetivo.nome));
      })
    });

    dropdown.change(function (event) { 
        if (event.target.nodeValue === "Selecione o Ano Letivo") {
            $(".checksemanas").prop("disabled", true);
            $(".checkturnos").prop("disabled", true);
            $(".checksegmentos").prop("disabled", true);
            $(".checksemanas").prop("checked", false);
            $(".checkturnos").prop("checked", false);
            $(".checksegmentos").prop("checked", false);
        }else{
            $(".checksemanas").prop("disabled", false);
            $(".checkturnos").prop("disabled", false);
            $(".checksegmentos").prop("disabled", false);
        };
    });
})