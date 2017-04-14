Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.svFeesInCartByUseronSuccess = function(variable, data) {
        $scope.$broadcast('feeListUpdate', data.content);
    };
}]);

Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFeesListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.feeTotal = 0;

        $scope.$on('feeListUpdate', function(event, fees) {
            var newFeeTotal = 0;

            fees.forEach(function(fee, index) {
                newFeeTotal += fee.amount;
            });

            $scope.feeTotal = newFeeTotal;
        });

        $scope.customButtonAction = function($event) {
            $scope.Variables.svCartItemIds.update();
            let temp = $scope.Variables.loggedInUser.dataSet.roles;
            for (let i = 0; i < temp.length; i++) {
                if ((temp[i] == "MunicipalityEmployee")) {
                    $scope.Variables.goToPage_MunicipalityCheckOut.navigate();
                }
            }
        };


        $scope.updaterowAction = function($event, $rowData) {

            var tempLink
            if ($rowData.itemType == 'form') {
                tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $rowData.itemGuid
                window.open(tempLink);
            } else {
                tempLink = window.location.hostname + "/#/ViewInspection?inspectionGuid=" + $rowData.itemGuid
                window.open(tempLink);
            }
        };

    }
]);