
$(document).ready(function() {
	registerSearch();
});


//var plantilla = "<ul>{{#tweets}}<form> " +
//	"<div class='row'>{{fromUser}}</div>" +
//	"<div class='row'>{{unmodifiedText}}</div> " +
//	"</form>{{/tweets}}</ul>";

 var plantilla =
 "{{#tweets}}"+
	 "<div class='row panel panel-default' th:fragment='content'>" +
		 "<div class='panel-heading'>" +
		 "<b>{{fromUser}}</b>"+
			"<div class='pull-right'>" +
				//"<span class='glyphicon glyphicon-link'>{{fromUser}}</span>"+
			"</div>"+
		"</div>"+
		"<div class='panel-body' >	{{unmodifiedText}}</div>"+
	"</div>"+
"{{/tweets}}"




function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		$.get($(this).attr('action'), {q: $("#q").val()}, function(data) {
			//$("#resultsBlock").empty().append(data);

				$("#resultsBlock").empty().append(Mustache.render(plantilla,data));
		});
	})}

