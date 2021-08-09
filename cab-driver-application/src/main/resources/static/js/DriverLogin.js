window.sessionStorage;
 
 //load cookies if exist
window.onload = loadCookies;
var xhr = new XMLHttpRequest();
var jwt;
var cabDetail;


function loadCookies() {
	
	//reduce the cookies from array to name and values
	var cookies = document.cookie
							.split(';')
							.map(cookie => cookie.split('='))
							.reduce((total, [key, value]) => ({...total, [key.trim()]: value}), {});
	
	//if cookies not present
	if(cookies.email  == undefined || cookies.pass == undefined) {
	    return false;
    }

	document.getElementById("driverLoginId").value = cookies.email;
									//password stored in cookie is base64 encoded
    document.getElementById("Password").value = window.atob(cookies.pass);    
	
	document.getElementById('rememberCheck').checked = true;
}
 
 
 
 //**FieldEmptyCheck--Starts**//
 function validation(){
 				
 
 var letters = /^[0-9A-Z]+$/;
 if(document.getElementById('driverLoginId').value == undefined ||
 	 document.getElementById('driverLoginId').value == ''||
 	 document.getElementById("driverLoginId").value.length <12||
 	 document.getElementById("driverLoginId").value.length >13){
          
          document.getElementById("invalidEmailId").innerHTML="<P class = 'InvalidLoginid'> ⓘ Enter Valid Login ID</p>";
    
           return;
       }
       var pass = /^[0-9]+$/;
       if(document.getElementById("Password").value==undefined || 
       	document.getElementById("Password").value=="" || 
       	document.getElementById("Password").value.length <10||
       	!document.getElementById('Password').value.match(pass)){
           
           document.getElementById("invalidPassword").innerHTML="<P class = 'InvalidLoginid'> ⓘ Enter Valid Password</p>";

           return;
       }
       
       

       

 //**FieldEmptyCheck--end **//
 
 
 //save cookies
function saveCookie() {
	
    //save cookies
	if(document.getElementById('rememberCheck').checked == true) {
        
        var d = new Date();
        d.setTime(d.getTime() + 24*60*60*1000);
        d.toUTCString();
		console.log($("#driverLoginId").val())
        document.cookie = "email="+$("#driverLoginId").val()+";"+ "expires="+d + ";path=/";
        document.cookie = "pass="+window.btoa($("#Password").val() ) +";"+ "expires="+d + ";path=/";

    }

	//delete cookies if remember me not checked
	else {
		document.cookie = "email=" +";"+ "expires="+ (new Date() - 24*60)  + ";path=/";
        document.cookie = "pass="+";"+ "expires="+(new Date() - 24*60) + ";path=/";
	}
	
}
 //save cookies end
 
 
 
 
   
       
       var formData = {
    		loginId : $("#driverLoginId").val(),
    		password :  $("#Password").val()
    	}
    	
       


    xhr.open("POST", 'http://localhost:8080/authenticate', true);
    xhr.setRequestHeader("Content-Type", "application/json");
   
    xhr.send(JSON.stringify(formData));

 

    xhr.onreadystatechange = function() {
    	
    	if(xhr.readyState == 4 && xhr.status ==200) {
	 jwt =  xhr.responseText;

    	saveCookie();
    	
    	getCabDetails();
    	
    		
    	}
    	if(xhr.readyState == 4 && xhr.status == 401){
    	
    	 document.getElementById("invalidCrediantial").innerHTML="<P class = 'InvalidLoginid'> ⓘ Enter Valid Credentials</p>";
    	
    	
    	
    	}
    }

 }
 
 
 
    
    function storeTokenInLocal(token){
    	return window.localStorage.setItem("token",xhr.token);
    	
    }
    
    function getTokenFromLocal(){
    	
    	return window.localStorage.getItem(token);
    	
    }
    
     
    
    
    function getCabDetails(){
    	var xhrCabdetails = new XMLHttpRequest();
    	var cabId= $("#driverLoginId").val();
    	xhrCabdetails.open("GET","http://localhost:8080/CabInfo/" +cabId ,true);
    	xhrCabdetails.onreadystatechange = function(){
    		if(xhrCabdetails.readyState==4 && xhrCabdetails.status==200){
    			cabDetail= JSON.parse(this.responseText);
    			sessionStorage.setItem('commonFileCabNumber',cabDetail.cabNumber);
    			sessionStorage.setItem('commonFileDriverName',cabDetail.driverName);
    			sessionStorage.setItem('commonFileDriverId',cabDetail.driverId);
    			sessionStorage.setItem('commonFileCabModel',cabDetail.cabModel);
    			sessionStorage.setItem("jwtToken", jwt);	
    			sessionStorage.setItem('commonFileAvailableSeats',cabDetail.availableSeats);
    			
    			
    			
    			
    	 		window.location.href= "No-Trip-Assigned-Page.html";
    		}
    	};
    	xhrCabdetails.send(null);
    }
    
    
    
   