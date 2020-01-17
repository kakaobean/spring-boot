function test(){
	$("#test").append('<div>dd</div>');
	
	  $.ajax(
		{
            url : '/getTest',
            data : '',
            contentType: 'application/json; charset=utf-8',
            dataType : 'json',               /*html, text, json, xml, script*/
            method : 'post',
            success : function(d){
                console.log("sdsafsfa");
            },
            error : function(e){
            	console.log(e);
         }
		});
}

