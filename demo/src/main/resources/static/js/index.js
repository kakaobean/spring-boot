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
	$("#jqgridTable").empty();
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
//        contentType : false,
        contentType: 'application/json; charset=utf-8',
        processData: false,
        enctype: "multipart/form-data",
        method : "POST",
//        dataType : 'json',
//        beforeSubmit: function(data, form, option) {
//            console.log('beforeSubmit');
//            console.log(data);
//            console.log(form);
//            console.log(option);
//        },
        success: function(d) {
        	console.log(d);
        	makeTable('excelTable', d.dataList, d.columnList);
        },
        error: function(x,e){
        	// 2번 호출됨 
            console.log("ERROR :: [aljjabaegi]ajax status : "+x.status);
            console.log("ERROR :: "+e);
        },
    });
   $("#fileForm").submit();

}

// onclick="fileUploadAjaxTest()" 이벤트 발생시켜서 ajax로 file 을 넘긴다 .. 테스트 완료
function fileUploadAjaxTest() {
//  var formData = $("#testFile").serialize();
  var form = $("form")[0];        
  var formData = new FormData(form);
  $.ajax({
	  contentType: false,      //  ajax로 파일 업로드 할 땐  contentType 과 processData 를 false로 해야함.  
	  processData: false,
//      cache : false,
      url : "/uploadTest", // 요기에
      type : 'POST',    // method 나 type나 상관없다 
      data : formData, 
      success : function(d) {
        console.log(d);
      }, // success 
      error : function(xhr, status) {
          alert(xhr + " : " + status);
      }
  }); // $.ajax */
}

// jqgrid 5.4.0 버전에서 사용 가능    / jsp에서 jqgrid 버전 바꿔주고 실행하면 됨 
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
// 아래 코드는 사용 못함.  poi를 사용해서 excel 다운로드시 form태그로 submit 해야한다.  / ajax 통신으로 하면 에러남
//function excelDownload(){	
//	var data = {
//			getRowData :  $("#jqgridTable").getRowData() 
//	}
//	$.ajax({
//		  url : '/excelDownload',
//		  data : JSON.stringify(data),
//		  contentType: 'application/json; charset=utf-8',
////		  dataType : "json",   // parsererror  에러남.  dataType은 json을 하고  결과값은 json으로 받지 못해서
//		  method : "post",
//		  success : function(d){
//			  console.log(d);
//		  },
//		  error : function(e){
//			  console.log(e);
//		  }
//	})
//}

function init(){
	test();
}
init();




