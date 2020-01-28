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
//		console.log(column[idx]);
//	}
	
	console.log("column" + column);
	console.log(colModel);
    $("#"+id).jqGrid({
           datatype: "local",  // datatype: "local" 로 하면 첫번째 row에 undefined 가 표현됨
           height: 250, 
           width : 888,
           colNames : column,
           colModel : colModel,
           caption: "주민등록별 인구",
           rowNum : 1000,
           loadonce : true
     });

     for(var i in array){
          $("#"+id).jqGrid('addRowData',i+1,array[i]);
     }
     // 첫번째 row 에 undefined 나오는것 삭제  ( jqgrid 5.4.0 버전일 때 )
     $("#norecs").remove();
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
        	// 2번 호출됨 
            console.log("ERROR :: [aljjabaegi]ajax status : "+x.status);
            console.log("ERROR :: "+e);
        },
    });
   $("#fileForm").submit();

}

// jqgrid 5.4.0 버전에서 사용 가능
function excelDown(){
	$("#jqgridTable").jqGrid("exportToExcel",{
		includeLabels : true,
		includeGroupHeader : true,
		includeFooter: true,
		fileName : "jqGridExport.xlsx",
		maxlength : 40 // maxlength for visible string data 
	})
}

// jqgrid 4버전대에서 자바를 사용하여 excel 다운로드 하기
function excelDown2(){	
//	var data = {
//					name :  "testName",
//					address : "testAddr"
//				}
	var data = {
			getRowData :  $("#jqgridTable").getRowData() 
	}
	$.ajax({
		  url : '/excelDownload',
		  data : JSON.stringify(data),
		  contentType: 'application/json; charset=utf-8',
		  dataType : "json",
		  method : "post",
		  success : function(d){
			  console.log(d);
		  },
		  error : function(e){
			  console.log(e);
		  }
	})
}

function init(){
	test();
}
init();




