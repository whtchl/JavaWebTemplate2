
function getClearType(){
	$('#clearType').combobox({ 
	    url:'../../pmsdictionary/select.action?type=CLEARTYPE', 
	    valueField:'key',   
	    textField:'disValue',
    	/*onLoadSuccess:function(){
	    	$(this).combobox('setText','T+7');
	    },*/
	});
}
