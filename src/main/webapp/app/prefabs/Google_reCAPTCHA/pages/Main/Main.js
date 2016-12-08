Application.$controller("Google_reCAPTCHAController", ["$scope", function($scope) {
    "use strict";

    /* 
     * This function will be invoked when any of this prefab's property is changed
     * @key: property name
     * @newVal: new value of the property
     * @oldVal: old value of the property
     */
    function propertyChangeHandler(key, newVal, oldVal) {
            /*
		switch (key) {
			case "prop1":
				// do something with newVal for property 'prop1'
				break;
			case "prop2":
				// do something with newVal for property 'prop2'
				break;
		}
		*/
        }
        /* register the property change handler */
    $scope.propertyManager.add($scope.propertyManager.ACTIONS.CHANGE, propertyChangeHandler);

    $scope.onInitPrefab = function() {
        // this method will be triggered post initialization of the prefab.

        //empty the previously cached captcha elements
        $('.g-recaptcha').empty();
        var recaptchaEle = $('.g-recaptcha');
        //remove previously generated captcha's
        if (recaptchaEle.length > 1) {
            $('.g-recaptcha')[0].remove();
        }

        //generate captcha
        var recaptchaId = grecaptcha.render($('.g-recaptcha')[0], {
            'sitekey': $scope.sitekey,
            'theme': 'light',
            'callback': verifyCallback
        });
        var childCaptcha = recaptchaEle.find('div');
        if (childCaptcha[1]) {
            $(childCaptcha[1]).addClass('recaptcha-desired');
        }

        function verifyCallback() {
            $scope.responseData = {
                "respkey": grecaptcha.getResponse()
            };
            $scope.Variables.verifyRecaptcha.setInput('ReCaptcha$VerifyReCaptchaObject', $scope.responseData);
            $scope.Variables.verifyRecaptcha.update();
        }

    };





    $scope.verifyRecaptchaonSuccess = function(variable, data) {
        $scope
        debugger;

    };

}]);