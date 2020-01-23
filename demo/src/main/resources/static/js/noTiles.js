function test(){
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
                makeTable('jqgridTable', d.dataList, d.columnList);
            },
            error : function(e){
            	console.log(e);
         }
		});
}

function makeTable(id, array, column){
	var colModel = [];
	$.each(column, function(idx, obj){
		colModel.push({name:obj, align:'center'});
	})
//	column.forEach(function(a,b){
//		console.log(a+' '+b)
//	})
//	for(var idx in column){
//		console.log(column[id]);
//	}
	
	console.log("column" + column);
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

function init(){
	test();
}
init();




