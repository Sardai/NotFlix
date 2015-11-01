var domain = "http://localhost:8080";
var rootUrl = domain+"/NotFlix/api/";
var token = localStorage.getItem("token");
showHideLogin();
$(function(){

	//user has registered, show toast.
	if(window.location.hash == "#registered"){
		window.location.hash = "";
		showToast(".created");
	}

	//get movies from rest.
	$.ajax({
		url:rootUrl+"movies",
		type:"GET",
		dataType:"JSON"	,
		success:function(response){
			showMovies(response);
		}
	})

	//removes rating from movie.
	$(document).on("click",".remove",function(){
		var el = $(this).closest(".movie");
		var id = el.data("id");
		$.ajax({
			url:rootUrl+"ratings",
			type:"DELETE",
			data:{
				imdbId:id
			},
			headers:{
				token:token
			},success:function(){
				el.removeAttr("userRating");
				//removes the color blue from the rating stars.					
				el.find(".star").removeClass("blue");
				$(this).hide();
			}
		});
	});

	//rate the movie with the chosen rating
	$(document).on("click",".rating .star",function(){
		var rating = $(this).data("rating");
		var movie = $(this).closest(".movie");			 
		if(token == null){
			showToast(".login");
		}else{
			rate(rating,movie);		

			movie.attr("data-userRating",rating);				
			showUserRating(movie,rating);
		}		
	});

	//search for movies on chosen criteria
	$("#searchButton").click(function(e){
		e.preventDefault();
		search();
	});

	//change selected search type.
	$(".dropdown-menu a").click(function(){
		$("#selected").html($(this).html());
	});

	//removes red border from login form fields when user has typed in them.
	$("[name=nickname], [name=password]").change(function(){
		$(this).removeClass("error");
	})

	//shows the info div when user moves the cursor over the movie poster.
	$(document).on("mouseenter",".movie", function (e) {
		e.stopPropagation();
		$(this).find(".info").not(":animated").fadeIn("slow");
	});

	//hides the info div when user moves the cursor leaves the movie poster.
	$(document).on("mouseleave",".movie", function (e) {
		e.stopPropagation();
		$(this).find(".info").fadeOut("slow");
	});

	//Logs the user in if the credentials are correct otherwise show error message.
	$("[name=loginForm] button").click(function(e){
		e.preventDefault();

		var nickname = $("[name=nickname]");
		var password = $("[name=password]");

		//if nickname and/or password fields are empty make the border red.			
		if(nickname.val() == ""){
			nickname.addClass("error");				
		}
		if(password.val() == ""){
			password.addClass("error");
		}


		$.ajax({
			type:"POST",
			url:rootUrl+"gebruikers/token",
			contentType:"application/json",
			data:JSON.stringify({
				nickname:nickname.val(),
				wachtwoord:password.val()
			}),
			statusCode: {
				401:function() {
					showToast(".401");
				}
			},
			success:function(response){
				// hides loginform
				$(this).hide();
				// clears form fields.
				nickname.val("");
				password.val("");
				//sets the token and saves it in the localstorage
				token = response;
				localStorage.setItem("token",token);
				//show message
				showToast(".logged-in");
				//hides loginform and shows logout button
				showHideLogin();

				//gets the rating of the user for each movie.
				$(".movies .movie").each(function(){
					getUserRating($(this),$(this).data("id"));
				});
			}
		});

	});

	//logs the user out and removes the token from storage.
	$("#logout").click(function(e){
		e.preventDefault();
		localStorage.removeItem("token");
		token = null;
		showHideLogin();
		//hide all user ratings from the movies.
		$(".blue").removeClass("blue");
		$(".remove").hide();

		//removes the user rating from the movies.
		$("[data-userRating").removeAttr("data-userRating");
	});


});

//this function clones the movie template for each movie and adds them to the movies view.
function showMovies(response){
	//clear movies view.
	$(".movies").html("");

	//shows message when there is nothing to show.
	if(response.length == 0){
		var clone = $(".notAvailable").clone()
		clone.appendTo(".movies");
		clone.fadeIn("slow")
	}

	$.each(response,function(i,movie){
		//clone movie template
		var clone =	$( ".template .movie" ).data("id",movie.imdbId).clone();
		//this finds each element with a data-field and sets 
		//the corresponding movie data in the element
		clone.find("[data-field]").each(function(){				
			$(this).html(movie[$(this).data("field")]);
		});

		//finds all rating stars in template and makes them red if the movie is rated.
		clone.find(".rating .star").each(function(){						
			if($(this).data("rating") <= movie.averageRating){
				$(this).addClass("red");
			}
		});
		//adds the imdbid to the template and adds the template to the moveis view.
		clone.attr('data-id', movie.imdbId)	
		.appendTo( ".movies" );
		//gets the poster for this movie.
		getPoster(movie.imdbId);
		// gets the user rating of this movie if the user is logged in.
		if(token != null){
			getUserRating(clone,movie.imdbId);
		}
	});
}

// search movies with the chosen criteria.
function search(){
	var type = "";
	var search = $("#search").val();
	var selected = $("#selected").html();
	//if the selected type is a imdbid it will be added to the end of the url
	//otherwhise the selected type is made to lower case and added to the end of the url.
	if( selected != "Imdb Id"){
		type = selected.toLowerCase()+"/";
	}

	$.ajax({
		url:rootUrl+"movies/"+type+search,
		type:"GET",
		dataType:"JSON",
		success:function(response){
			var movies = [];

			//if the response is not an array then the response is added to an array.
			if($.isArray(response)){
				movies = response;
			}else if(response != null){
				movies.push(response);
			}
			showMovies(movies);	
		}
	})
}

//shows or hide different elements if the user is logged in or not.
function showHideLogin(){
	if(token == undefined || token == null || token == ""){
		$("[name=loginForm],#register").show();
		$("#logout,#users").hide();		
	}else{
		$("[name=loginForm],#register").hide();
		$("#logout,#users").show();
	}
}

//gets the user rating for the specific movie from the REST service.
function getUserRating(el,imdbId){
	$.ajax({
		url:rootUrl+"ratings/"+imdbId,
		type:"GET",
		dataType:"JSON",
		headers:  {
			token:token
		},
		success: function(response){
			showUserRating(el,response.sterren);		
		}
	});	
}

//shows the user rating 
function showUserRating(el,rating){
	el.find(".remove").show();
	el.find(".rating .star").each(function(){
		if($(this).data("rating") <= rating){
			$(this).addClass("blue");
			$(this).attr("data-userRating",rating);
		}
	})
}

//gets the poster of the movie from the omdbapi.
function getPoster(imdbId){
	$.ajax({
		url:"http://www.omdbapi.com/?i="+imdbId+"&plot=short&r=json",
		success:function(data){
			$(".movies [data-id="+imdbId+"] img").attr("src",data.Poster);
		}
	});
}

//post a rating to the rest service.
function rate(rating,el){	
	var id = el.data("id");
	//if the rating already exists use PUT instead of POST.
	var verb = "POST";	
	if(el.attr("data-userRating") != undefined){
		verb = "PUT";
	}
	$.ajax({
		url:rootUrl+"ratings",
		type:verb,
		data:{
			sterren:rating,			 
			imdbId:	id
		},
		headers:  {
			token:token
		}	 
	});	
}

//shows a message for a few seconds.
function showToast(selector){
	$(".alert"+selector).fadeIn("slow",function(){
		$(this).delay( 1200 ).fadeOut("slow");	
	});
}

