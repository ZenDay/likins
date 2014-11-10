$(function() {
	waterfall();
});
$(window).resize(function() { 
	waterfall();
 });
$(window).scroll(function() {
	if(checkScroll()){
	}
});

function waterfall(){
	var $pcWidth = $("#home-main>.photo-collapse").width()+10;
	var $left = ($("#home-main").width() - $pcWidth*4)/2;
	var hArr = [];

	for (var i = 0; i < $("#home-main>.photo-collapse").length; i++) {
		if(i < 4){
			$("#home-main>.photo-collapse").eq(i).css("left",$left + i*$pcWidth);
			hArr[i] = $("#home-main>.photo-collapse").eq(i).height();
		}else{
			var minHeight = Math.min.apply(null,hArr);
			var minIndex = $.inArray(minHeight,hArr);
			$("#home-main>.photo-collapse").eq(i).css("left",$left + minIndex*$pcWidth);
			$("#home-main>.photo-collapse").eq(i).css("top",minHeight+10);
			hArr[minIndex] += $("#home-main>.photo-collapse").eq(i).height();
		}
	};
}

function checkScroll(){
	var $lastPC = $("#home-main>.photo-collapse").last();
	var lastdis = $lastPC.offset().top + $lastPC.height()/2 + 5;
	var scTop = $(window).scrollTop();
	var docHeight = $(document).height();
	return (lastdis<scTop + docHeight)?true:false;
}