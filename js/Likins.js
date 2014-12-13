$(function() {
	var winH = $(window).height();
	var winW = $(window).width();
	$(".bg-can").height(winH);
	$(".submit").css({"margin-top":winH/2-177+"px","margin-left":winW/2-195+"px"})



	// change sign in and sign up
	$("#change-signup").click(function(){
	 	$("input[type=submit]").attr("value","登    陆");
	 	$("#change-signup").attr("class","hidden");
	 	$("#change-signin").attr("class","");
	});	 
	$("#change-signin").click(function(){
	 	$("input[type=submit]").attr("value","加    入");
	 	$("#change-signin").attr("class","hidden");
	 	$("#change-signup").attr("class","");
	});

	// background slide up
	$(".bg-can img").animate({
	 	top: "-100px"
	},5000)


	$("#home-main .photo-collapse>img").click(function(){
	 	$("#bg-shadow").attr("class","");
	 	$("#img-detail").attr("class","");
		$("#img-detail").css("left",winW/2-400+"px")
		photoOn();
	});
	$("#bg-shadow").click(function(){
	 	$("#bg-shadow").attr("class","hidden");
	 	$("#img-detail").attr("class","hidden");
	});

})



function photoOn () {
	var $dtimgH = $("#img-detail>.dt-img>img").height();
	if($dtimgH < 550){
		$("#img-detail>.dt-img>img").css("margin-top",(550-$dtimgH)/2);
	}
}