$.ajax({
    type: "GET",
    url: "http://localhost:8080/NotFlix/api/gebruikers",
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

