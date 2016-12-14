Application.$controller("MunicipalityUsersPageController", ["$scope", function($scope) {
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


    $scope.buttonAddEmployeeClick = function($event, $isolateScope) {
        $scope.Variables.AdminOrEmp.dataSet.dataValue = "MunicipalityEmployee";
    };


    $scope.buttonAddAdminClick = function($event, $isolateScope) {
        $scope.Variables.AdminOrEmp.dataSet.dataValue = "MunicipalityAdmin";
    };


    $scope.GroupMembersDataonError = function(variable, data) {
        debugger;
    };


    $scope.GroupMembersDataonSuccess = function(variable, data) {
        debugger
    };

}]);






Application.$controller("gridUsersubscriptionsListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);









Application.$controller("grid4Controller", ["$scope",
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

Application.$controller("gridEmployeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddEmployeeORAdminController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonRoleClick = function($event, $isolateScope) {
            if ($scope.Variables.CheckingUserWithMunicipalityInRoles.dataSet.content[0].exist > 0) {
                $scope.Variables.UpdateEmployeeORAdminRoleForMunicipality.update();
            } else {
                $scope.Variables.NewRole.update();
            }
        };

    }
]);

Application.$controller("MunicipalitygroupsgridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("ManageUsersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("GroupMembersDataController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);