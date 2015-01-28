var jcrop_api;


function showImage (fileInputID,oldImgId,newimgId) {
	var $fileInput = $("#" + fileInputID);
	$fileInput.change(function (e) {
		var file = $fileInput[0].files[0];
		var reader = new FileReader();

		if(typeof FileReader == "undefined") alert("你的浏览器太落伍啦~是时候更新啦~");
		else if (typeof file == "undefined") alert("选择一个文件嘛~");
		else{

			reader.readAsDataURL(file);
			reader.onload = function(evt) {
				$("#" + oldImgId).hide();
				$("#up-can").hide();
				$("#" + newimgId).attr("src",this.result);
				$("#" + newimgId).show();
				$("#submitImg").show();

				$("#" + newimgId).Jcrop({
					aspectRatio: 1,
					onChange: updatePara,
					onSelect: updatePara,
					boxWidth: 500
				},function () {
					jcrop_api = this;
				});
			}
		};
	});
}

function updatePara (c) {
	var ratio = jcrop_api.getScaleFactor();
    $('#x').val(c.x * ratio[0]);
    $('#y').val(c.y * ratio[0]);
    $('#w').val(c.w * ratio[0]);
    $('#h').val(c.h * ratio[0]);
}