Application.$controller("ManageFormsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };




}]);


Application.$controller("dialogCreateFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;






        $scope.dialogCreateFormOpened = function($event, $isolateScope) {
            var charSet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
            var randomString = '';
            for (var i = 0; i < 32; i++) {
                var randomPoz = Math.floor(Math.random() * charSet.length);
                randomString += charSet.substring(randomPoz, randomPoz + 1);
            }
            $scope.Widgets.liveformCreateForm.GUID = randomString;
        };


        $scope.liveformCreateFormSuccess = function($event, $operation, $data) {
            $scope.Variables.goToPage_EditForm.dataSet.FormTypeId = $data.id;
            $scope.Variables.goToPage_EditForm.dataSet.MunicipalityId = $scope.Widgets.selectMunicipality.datavalue.ID;
            $scope.Variables.goToPage_EditForm.navigate();
        };

    }
]);

Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("liveformCreateFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("dialogCategoriesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("gridFormcategoriesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFormTypesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);