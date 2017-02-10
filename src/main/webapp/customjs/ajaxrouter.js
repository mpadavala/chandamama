/**
* @param rqstUrl   	- ${pageContext.request.contextPath}/abc/xyz.htm
* @param rqstType 	 	- GET / POST  -- Default is POST
* @param asyncFlag 	- true / false  -- Default is false
* @param requestParams - {inputlist1: JSON.stringify(inputlist1), inputlist2: JSON.stringify(inputlist2)}
* @param dataType		- XML/JSON/HTML/Text
* @param successCallback  - function(){}
* @param errorCallback	   - function(){}
*/
function ajaxCall(rqstUrl, rqstType, asyncFlag, requestParams,
		dataType, successCallback, errorCallback) {

	  if(!validate(rqstUrl, successCallback, errorCallback)) { return; }

	  if (requestParams != undefined
			&& requestParams["HandleInfosec"] != undefined && requestParams["HandleInfosec"]) {
		  requestParams = handleInfosec(requestParams);
	  }
	  
      $.ajax({
     	url : rqstUrl,
       	type : rqstType == undefined ? "POST": rqstType,
       	async : asyncFlag == undefined ? true: asyncFlag,
       	data : requestParams,
     	dataType : dataType == undefined ? "json": dataType,
   		beforeSend: function(jqXHR, settings) {
               if (typeof sessionStorage.tabid == "undefined" ) {
                    sessionStorage.tabid = generateTabid();
               } 
      	       jqXHR.setRequestHeader("tabid", sessionStorage.tabid);
      	       
   		},
   		error : function(jqXHR, ajaxOptions, thrownError) {
   	    	 console.log("ERROR: HTTP response status code: " + jqXHR.status);
         	     console.log("ERROR: " + jqXHR.responseText);
         	     errorCallback(jqXHR, ajaxOptions, thrownError);
   		},
   		success : function(data) {
   			console.log("ajax request completed sucessfully : " + rqstUrl);
   			successCallback(data);
           }
       });
}

function dynAjaxCall(requestObject) {
	  
	  if (!validate(requestObject.rqstUrl, requestObject.successCallback,
		requestObject.errorCallback)) {
		  return;
	  }
	  
      $.ajax({
     	url : requestObject.rqstUrl,
       	type : requestObject.rqstType == undefined ? "POST": requestObject.rqstType,
       	async : requestObject.asyncFlag == undefined ? true: requestObject.asyncFlag,
       	data : requestObject.requestParams,
     	dataType : requestObject.dataType == undefined ? "json": requestObject.dataType,
     	timeout : requestObject.timeOut == undefined ? 10000: requestObject.timeOut,
   		beforeSend: function(jqXHR, settings) {
               if (typeof sessionStorage.tabid == "undefined" ) {
                    sessionStorage.tabid = generateId();
               } 
      	        jqXHR.setRequestHeader("tabid", sessionStorage.tabid);
   		},
   		error : function(jqXHR, ajaxOptions, thrownError) {
   			console.log("ERROR: HTTP response status code: " + jqXHR.status);
         	console.log("ERROR: " + jqXHR.responseText);
         	requestObject.errorCallback(jqXHR, ajaxOptions, thrownError);
   		},
   		success : function(data) {
   			console.log("ajax request completed sucessfully : " + requestObject.rqstUrl);
   			requestObject.successCallback(data);
           }
       });
}

/** Validate query
* @param url
* @param successCallback
* @param errorCallback
* @returns {Boolean}
*/
function validate(url, successCallback, errorCallback) {
	if(url == undefined) {
		alert("url is not passed");
		return false;
	}
	
	if (successCallback == undefined || typeof successCallback != 'function') {
		alert("Pass callback function for Success");
		return false;
	}

	if (errorCallback == undefined || typeof errorCallback != 'function') {
		alert("Pass callback function for Errors");
		return false;
	}
	return true;

}

function generateId() {
	return randomId() + randomId() + '-' + randomId() + randomId();
}

function randomId() {
    return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
}


