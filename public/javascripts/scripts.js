jQuery.ajaxSetup({cache:false});
$("document").ready(function(){
	retrieveContactList();
	$("#frmContacts").change(function(){
		retriveContact();
	});
});
var saveContact = function(){
	clearNotices();
	var strId = $("#frmContacts").find(":selected").val();
	var strName = $("#frmName").val();
	if((strName == "")||(strName.search(/\S/) == -1)){
		notice("A name is required to save a new contact.");
		return;
	}
	var strAddress = $("#frmAddress").val();
	var strPhone = $("#frmPhone").val();
	var strEmail = $("#frmEmail").val();
	if(strId == "-1"){//new
		var url = "/contacts";
		$.post(url, 			
			{ 'contact[name]': strName, 'contact[address]': strAddress,
			'contact[phone]': strPhone, 'contact[email]': strEmail },
			function(data){
				retrieveContactList();
			});
	}else{//update
		var url = "/contacts/"+strId;
		$.post(url, 
			{ 'contact[name]': strName, 'contact[address]': strAddress,
			'contact[phone]': strPhone, 'contact[email]': strEmail },
			function(data){
				retrieveContactList();
			});
	}	
}

var deleteContact = function(){
	clearNotices();
	var strId = $("#frmContacts").find(":selected").val();
	if(strId == '-1'){
		notice("This is not an entry and cannot be deleted");
		return;
	}
	var url = "/contacts/"+strId+"/delete";
		$.post(url, 
			function(data){
				retrieveContactList();
		});
}

var retrieveContactList = function(){
	var url = "/contacts";
	$.get(url, function(data, textStatus, jqXHR){
			data = $.parseJSON(data);
			// console.log(data);
			var out = "<option value='-1' selected></option>";
			for(var i=0;i<data.length;i++){
				console.log(data[i])
				out += "<option value='"+data[i]['_id']['$oid']+"' >"+data[i].name+"</option>";
			}
			$("#frmContacts").html(out);
			retriveContact();
		});
}

var retriveContact = function(){
	clearNotices();
	var strId = $("#frmContacts").find(":selected").val();
	if(strId == "-1"){
		$("#frmName").val("");
		$("#frmAddress").val("");
		$("#frmPhone").val("");
		$("#frmEmail").val("");
	}else{
		var url = "/contacts/"+strId;
		$.get(url, function(data){
			data = $.parseJSON(data);
			console.log(data);
			$("#frmName").val(data['name']);
			$("#frmAddress").val(data['address']);
			$("#frmPhone").val(data['phone']);
			$("#frmEmail").val(data['email']);
		});
	}
}

var notice = function(msg){
	$("#alerts").append("<li>"+msg+"</li>");
}

var clearNotices = function(){
	$("#alerts").html("");
}