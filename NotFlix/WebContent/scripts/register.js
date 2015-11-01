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
	});	
}