var domain = "http://localhost:8080";
var rootUrl = domain+"/NotFlix/api/";

$(function(){
    
    $("#create-user").click(function(e){
        e.preventDefault();
        register();
    });
});

function register(){
        
    var achternaam = $("#achternaam").val();
    var tussenvoegsel = $("#tussenvoegsel").val();
    var voornaam = $("#voornaam").val();
    var nickname = $("#nickname").val();
    var wachtwoord = $("#wachtwoord").val();
	
	$.ajax({
		url:rootUrl+"gebruikers",
		type:"POST",
		data:{
            achternaam: achternaam,
            tussenvoegsel: tussenvoegsel,
            voornaam: voornaam,
            nickname: nickname,
            wachtwoord: wachtwoord,

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
				
				//show message
				showToast(".created");
			}
	});	
}

//shows a message for a few seconds.
function showToast(selector){
	$(".alert"+selector).fadeIn("slow",function(){
		$(this).delay( 1200 ).fadeOut("slow");	
	});
}