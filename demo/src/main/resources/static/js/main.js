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
                console.log("return Data : " + d.columnList);
                console.log("return Data : " + d.dataList);
                makeTable('table1', d.dataList, d.columnList);
            },
            error : function(e){
            	console.log(e);
         }
		});
}

function makeTable(id, array, column){
	var colModel = [];
	for(var i = 0; i < column.length; i++){
		colModel.push({name:column[i], align:'center'});
	}
	console.log(colModel);
    $("#"+id).jqGrid({
           datatype: "local",
           height: 250, 
           width : 630,
           colNames : column,
           colModel : colModel,
           caption: "주민등록별 인구"
     });

     for(var i in array){
          $("#"+id).jqGrid('addRowData',i+1,array[i]);
     }
}







