Application.$controller("InspectionListPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {};

    $scope.getDateRanges = function() {
        return {
            previous: {
                start: moment().endOf('day').subtract(1, 'months').valueOf(),
                end: moment().endOf('day').subtract(1, 'days').valueOf()
            },
            today: {
                start: moment().startOf('day').valueOf(),
                end: moment().endOf('day').valueOf()
            },
            upcoming: {
                start: moment().endOf('day').valueOf(),
                end: moment().startOf('day').add(1, 'months').valueOf()
            }
        };
    };

    $scope.dateRanges = $scope.getDateRanges();


    $scope.buttonRefreshInspectionListClick = function($event, $isolateScope) {
        $scope.dateRanges = $scope.getDateRanges();

        $scope.Variables.svInspectionList.update();
    };
}]);

Application.$controller("gridInspectionsController", ["$scope", "$location",
    function($scope, $location) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.viewInspectionAction = function($event, $rowData) {
            $location.path("/ViewInspection").search({
                inspectionGuid: $rowData.inspectionGuid
            });
        };

    }
]);