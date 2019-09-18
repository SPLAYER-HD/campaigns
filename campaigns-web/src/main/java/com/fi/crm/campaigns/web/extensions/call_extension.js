window.com_fi_crm_campaigns_web_extensions_CallExtension = function() {
	this.initCall = function() {
		var state = this.getState();
		var token = state.token;
		
		/* Create the Client with a Capability Token */
	    Twilio.Device.setup(token, {debug : true});

		/* Let us know when the client is ready. */
		Twilio.Device.ready(function(device) {
			//alert("Ready");
		});

		/* Report any errors on the screen */
		Twilio.Device.error(function(error) {
			alert("Error: " + error.message);
		});
		
		Twilio.Device.connect(function(conn) {
			alert("Successfully established call");
		});
	};
	
	this.makeCall = function(phoneNumber) {
		params = {"PhoneNumber": phoneNumber};
		console.log("phoneNumber ="+phoneNumber);
		//params = {phone_number: "+13056879990"};
		//params = {"PhoneNumber": "+13056879990"};
		Twilio.Device.connect(params);
	};

	this.hangUp = function() {
		Twilio.Device.disconnectAll();
	};
};