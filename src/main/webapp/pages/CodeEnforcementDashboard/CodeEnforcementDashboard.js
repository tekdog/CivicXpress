Application.$controller("CodeEnforcementDashboardPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.weekDayStart = moment().startOf('day').subtract(7, 'days').valueOf();
    };

}]);