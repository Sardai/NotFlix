var rootUrl = "/NotFlix/api/";

$(function(){
    
    $("#search-user").click(function(e){
        e.preventDefault();
        cleartable();
        getuser();
    });
    
    $("#get-all").click(function(e){
        e.preventDefault();
        console.log("onclick");
        cleartable();
        getall();
    });
});

function getuser(){
    
    console.log("function call");
    
    var nickname = $("#nickname").val();
    
    $.ajax({
    type: "GET",
    url: rootUrl+"gebruikers/"+nickname,
    dataType: "JSON",
	statusCode: {
		401: function() {
			showToast(".notfound");
		}
	},
	success: function(response){
            $("#user-table tbody").append("<tr><td>"+response.nickname+"</td><td>"+response.voornaam+"</td><td>"+response.tussenvoegsel+"</td><td>"+response.achternaam+"</td></tr>");
            console.log(response);
        ;
	}
    });
    
}

function getall(){
    console.log("function call");
    $.ajax({
    type: "GET",
    url: rootUrl+"gebruikers",
    dataType: "JSON",
	statusCode: {
		401: function() {
			showToast(".401");
		}
	},
	success: function(response){
        $.each(response, function(i, val){
            $("#user-table tbody").append("<tr><td>"+val.nickname+"</td><td>"+val.voornaam+"</td><td>"+val.tussenvoegsel+"</td><td>"+val.achternaam+"</td></tr>");
            console.log(val);
        });
	}
    });

}

function cleartable(){
    console.log("clear table");
    $("#user-tbody").empty();
}

//shows a message for a few seconds.
function showToast(selector){
	$(".alert"+selector).fadeIn("slow",function(){
		$(this).delay( 1200 ).fadeOut("slow");	
	});
}