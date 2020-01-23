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
           width : 888,
           colNames : column,
           colModel : colModel,
           caption: "주민등록별 인구"
     });

     for(var i in array){
          $("#"+id).jqGrid('addRowData',i+1,array[i]);
     }
}

function fileUpload() {

//	$('#fileForm').ajaxForm({
//		url: "/uploadTest",
//		enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지정해주어야 하는 부분이며 multipart로 지정해주지 않으면 controller로 파일을 보낼 수 없음
//		success: function(result){
//			console.log(result);
//		}
//	});
	
   $('#fileForm').ajaxForm({
        contentType : false,
        processData: false,
        enctype: "multipart/form-data",
        dataType : "POST",
        dataType : 'json',
        beforeSubmit: function(data, form, option) {
            console.log('beforeSubmit');
            console.log(data);
            console.log(form);
            console.log(option);
        },
        success: function(returnData) {
            func(returnData);
        },
        error: function(x,e){
            console.log("[aljjabaegi]ajax status : "+x.status);
            console.log(e);
        },
    });
   $("#fileForm").submit();

}

function init(){
	test();
}
init();




