Application.$controller("MunicipalityAdministrationPageController", ["$scope", function($scope) {
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


    $scope.GISRecordsTileClick = function($event, $isolateScope) {
        $scope.Variables.goToPage_ManageGISData.setData({
            'from': '4mA8'
        });
        $scope.Variables.goToPage_ManageGISData.navigate();
    };


    $scope.CountOfFormsForMunicipalityonSuccess = function(variable, data) {
        $scope.Variables.FormDesignsForMuniAdmin.dataSet.dataValue = data.content[0].count;
    };

}]);