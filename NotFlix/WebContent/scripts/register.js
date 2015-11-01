var domain = "http://localhost:8080";
var rootUrl = domain+"/NotFlix/api/";

$(function(){
    
    $("#create-user").click(function(e){
        e.preventDefault();
        register();
    });
});

function register(){
        
    var achternaam = $("#lastname");
    var tussenvoegsel = $("#prefix");
    var voornaam = $("#firstname");
    var nickname = $("#nickname");
    var wachtwoord = $("#password");
	
	$.ajax({
		url:rootUrl+"gebruikers",
		type:"POST",
		data:{
            achternaam: achternaam.val(),
            tussenvoegsel: tussenvoegsel.val(),
            voornaam: voornaam.val(),
            nickname: nickname.val(),
            wachtwoord: wachtwoord.val()
		}, 
        
        statusCode: {
            401:function() {
                showToast(".401");
            }
        },
			success:function(response){
				// clears form fields.
                achternaam.val("");
                tussenvoegsel.val("");
                voornaam.val("");
                nickname.val("");
                wachtwoord.val("");
			 
                window.location.replace("index.html#registered");
			}
	});	
}

//shows a message for a few seconds.
function showToast(selector){
	$(".alert"+selector).fadeIn("slow",function(){
		$(this).delay( 1200 ).fadeOut("slow");	
	});
}