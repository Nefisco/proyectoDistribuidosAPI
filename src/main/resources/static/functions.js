
$(document).ready(function() {
	registerSearch();
});

;

 var plantilla =
 "{{#libros}}"+
	 "<div class='row panel panel-default' th:fragment='content'>" +
		 "<div class='panel-heading'>" +
		 "<b>{{tittle}}</b>"+
			"<div class='pull-right'>" +
			"</div>"+
		"</div>"+
		"<div class='panel-body' >	{{personajes}}</div>"+
		"<div class='panel-body' >	{{emociones}}</div>"+
	"</div>"+
"{{/books}}"




function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		$.get($(this).attr('action'), {q: $("#q").val()}, function(data) {
				$("#resultsBlock").empty().append(Mustache.render(plantilla,data));
		});
	})}

