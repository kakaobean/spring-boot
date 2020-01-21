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
                makeTable('table1', d);
            },
            error : function(e){
            	console.log(e);
         }
		});
}

function makeTable(id, array){
    $("#"+id).jqGrid({
           datatype: "local",
           height: 250, 
           width : 630,
           colNames:['기준일','자치구', '내국인(남)', '내국인(여)','외국인(남)','외국인(여)','세대당 인구','65세 이상'],
           colModel:[
               {name:'BASEDATE', align:'right'},
               {name:'SGGNM', align:'right'},
               {name:'NATIVEM', align:'right'},
               {name:'NATIVEF', align:'right'},
               {name:'FOREIGNM', align:'right'},
               {name:'FOREIGNF', align:'right'},
               {name:'HOUSEPOPUL', align:'right'},
               {name:'SENIORCITIZ', align:'right'}
           ],
           caption: "주민등록별 인구"
     });

     for(var i in array){
          $("#"+id).jqGrid('addRowData',i+1,array[i]);
     }
}







