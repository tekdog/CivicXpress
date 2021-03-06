Application.$controller("dynamicFieldEditorPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {};

    function resetFormFieldDialog() {
        $scope.Widgets.textFormFieldLabel.reset();
        $scope.Widgets.selectFormFieldType.reset();
        $scope.Widgets.checkboxFormFieldRequired.reset();
        $scope.Widgets.textDefaultValue.reset();
        $scope.Widgets.longTextDefault.reset();
        $scope.Widgets.dateDefaultValue.reset();
        $scope.Widgets.datetimeDefaultValue.reset();
        $scope.Widgets.numberDefaultValue.reset();
        $scope.Widgets.booleanDefaultValue.reset();
        $scope.Widgets.textareaFormFieldHelpText.reset();
        $scope.Widgets.textFormFieldDisplayOrder.reset();
        $scope.Variables.stvPossibleValues.dataSet = [];
    }

    $scope.svSaveFormFieldonSuccess = function(variable, data) {
        resetFormFieldDialog();
        $scope.Widgets.dlgFormTypeField.close();
    };

    $scope.svSaveInspectionFieldonSuccess = function(variable, data) {
        $scope.svSaveFormFieldonSuccess(variable, data);
    };

    $scope.liveformUpdateFormTypeBeforeservicecall = function($event, $operation, $data) {
        if ($operation === 'update') {
            if ($scope.Widgets.gisOptionSelect.datavalue === 'Multiple') {
                $data.gisrecord = true;
                $data.multipleGisrecords = true;
            } else if ($scope.Widgets.gisOptionSelect.datavalue === 'Single') {
                $data.gisrecord = true;
                $data.multipleGisrecords = false;
            } else {
                $data.gisrecord = false;
                $data.multipleGisrecords = false;
            }

            if ($scope.Widgets.selectVendorOption.datavalue === 'Multiple') {
                $data.vendorSelection = true;
                $data.multipleVendors = true;
            } else if ($scope.Widgets.selectVendorOption.datavalue === 'Single') {
                $data.vendorSelection = true;
                $data.multipleVendors = false;
            } else {
                $data.vendorSelection = false;
                $data.multipleVendors = false;
            }
        }
    };

    $scope.svUpdateFormTypeFieldonSuccess = function(variable, data) {
        resetFormFieldDialog();
        $scope.Widgets.dlgFormTypeField.close();
    };
}]);

Application.$directive("fieldCalculator", [function() {
    "use strict";
    return {
        restrict: 'A',
        scope: true,
        link: function(scope, elem, attrs) {
            var valueWidget = scope.Widgets.calculatedDefault;

            var calculation = '';

            scope.$on('dlgDynamicFieldOpened', function(args) {
                calculation = valueWidget.datavalue || '';
            });

            var operationList = ['/', '*', '-', '+', '(', ')'];

            function getLastChar() {
                return !!valueWidget.datavalue && !!valueWidget.datavalue.length ? valueWidget.datavalue.substr(valueWidget.datavalue.length - 1) : null;
            }

            scope.addField = function(fieldName) {
                var lastChar = getLastChar();

                if (!lastChar || operationList.indexOf(lastChar) > -1) {
                    calculation += '[' + fieldName + ']';

                    valueWidget.datavalue = calculation;
                }
            };

            scope.addOperation = function(operation) {
                var lastChar = getLastChar();

                if (!!lastChar && operationList.indexOf(operation) > -1 && operationList.indexOf(lastChar) === -1) {
                    calculation += operation;

                    valueWidget.datavalue = calculation;
                }
            };

            scope.addNumber = function(number) {
                var lastChar = getLastChar();

                if (!lastChar || lastChar !== ']') {
                    calculation += number;

                    valueWidget.datavalue = calculation;
                }
            };

            scope.backspace = function($event) {
                var lastChar = getLastChar();

                if (!lastChar) {
                    return;
                }

                if (lastChar === ']') {
                    var startDeleteIndex = calculation.lastIndexOf("[");

                    calculation = calculation.slice(0, startDeleteIndex);
                } else {
                    calculation = calculation.slice(0, -1);
                }

                valueWidget.datavalue = calculation;
            };

            // Field buttons
            scope.fieldGroups = [];
            scope.fieldGroups.push([]);

            scope.perRow = 3;

            var fieldsWatcher = scope.$watch(function() {
                return scope.$eval(attrs.fieldList);
            }, function(newVal, oldVal) {
                if (!!newVal) {
                    newVal.forEach(function(field, index) {
                        scope.fieldGroups[scope.fieldGroups.length - 1].push(field);

                        if ((index + 1) % scope.perRow === 0) {
                            scope.fieldGroups.push([]);
                        }
                    });
                }
            });
        }
    };

}]);

Application.$controller("gridFieldsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            formTypeFieldToEdit = $rowData;
            $scope.Widgets.dlgFormTypeField.open();
        };
    }
]);

var formTypeFieldToEdit = null;

Application.$controller("dlgFormTypeFieldController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        function getDefaultValueWidgetByType(formFieldType) {
            switch (formFieldType) {
                case 'Text':
                case 'Header':
                    return $scope.Widgets.textDefaultValue;
                case 'Long Text':
                case 'Instruction Text':
                    return $scope.Widgets.longTextDefault;
                case 'Date':
                    return $scope.Widgets.dateDefaultValue;
                case 'Date+Time':
                    return $scope.Widgets.datetimeDefaultValue;
                case 'Number':
                    return $scope.Widgets.numberDefaultValue;
                case 'Currency':
                    return $scope.Widgets.currencyDefaultValue;
                case 'Boolean':
                    return $scope.Widgets.booleanDefaultValue;
                case 'Calculated':
                    return $scope.Widgets.calculatedDefault;
                default:
                    return $scope.Widgets.textDefaultValue;
            }
        }

        $scope.buttonSaveFormFieldClick = function($event, $isolateScope) {
            var possibleValues = "";
            var defaultValue = "";

            var defaultValueWidget = getDefaultValueWidgetByType($scope.Widgets.selectFormFieldType.datavalue.label);
            if ($scope.Widgets.selectFormFieldType.datavalue.label == 'Date+Time') {
                defaultValue = !!defaultValueWidget.datavalue ? moment(defaultValueWidget.datavalue).format("YYYY-MM-DD HH:mm:ss") : '';
            } else {
                defaultValue = defaultValueWidget.datavalue;
            }

            $scope.Variables.stvPossibleValues.dataSet.forEach(function(possibleValue, index) {
                if (index > 0) {
                    possibleValues += ',';
                }
                possibleValues += possibleValue.dataValue.replace(/,/g, '&#44;');
            });

            var variable = null;

            if (!!formTypeFieldToEdit) {
                variable = $scope.Variables.svUpdateFormTypeField;
                variable.setInput('formTypeFieldId', formTypeFieldToEdit.id);
            } else {
                if (!!$scope.pageParams.inspectionDesignId) {
                    variable = $scope.Variables.svSaveInspectionField;
                } else {
                    variable = $scope.Variables.svSaveFormField;
                }
            }

            variable.setInput('required', !!$scope.Widgets.checkboxFormFieldRequired.datavalue);
            variable.setInput('possibleValues', possibleValues);
            variable.setInput('defaultValue', defaultValue);
            variable.update();
            formTypeFieldToEdit = null;
            $scope.Widgets.dlgFormTypeField.close();
        };

        $scope.dlgFormTypeFieldOpened = function($event, $isolateScope) {
            var formTypeFieldData = formTypeFieldToEdit;
            if (!!formTypeFieldData) {
                $scope.Widgets.textFormFieldLabel.datavalue = formTypeFieldData.label;
                $scope.Widgets.selectFormFieldType.datavalue = formTypeFieldData.formFieldTypes;
                $scope.Widgets.selectFormFieldType.setProperty('disabled', true);
                $scope.Widgets.checkboxFormFieldRequired.datavalue = formTypeFieldData.required;
                getDefaultValueWidgetByType(formTypeFieldData.formFieldTypes.label).datavalue = formTypeFieldData.formFieldTypes.label === 'Boolean' ? formTypeFieldData.defaultValue === 'true' : (formTypeFieldData.defaultValue || '');
                $scope.Widgets.textareaFormFieldHelpText.datavalue = (formTypeFieldData.helpText || '');
                $scope.Widgets.textFormFieldDisplayOrder.datavalue = formTypeFieldData.displayOrder;

                $scope.Variables.stvPossibleValues.dataSet = [];
                if (!!formTypeFieldData.possibleValues) {
                    var possibleValues = formTypeFieldData.possibleValues.split(',');
                    possibleValues.forEach(function(possibleValue) {
                        $scope.Variables.stvPossibleValues.dataSet.push({
                            dataValue: possibleValue
                        });
                    });
                }
            } else {
                $scope.Widgets.selectFormFieldType.setProperty('disabled', false);
                $scope.Widgets.textFormFieldLabel.reset();
                $scope.Widgets.selectFormFieldType.reset();
                $scope.Widgets.checkboxFormFieldRequired.reset();
                $scope.Widgets.textDefaultValue.reset();
                $scope.Widgets.longTextDefault.reset();
                $scope.Widgets.dateDefaultValue.reset();
                $scope.Widgets.datetimeDefaultValue.reset();
                $scope.Widgets.numberDefaultValue.reset();
                $scope.Widgets.booleanDefaultValue.reset();
                $scope.Widgets.textareaFormFieldHelpText.reset();
                $scope.Widgets.textFormFieldDisplayOrder.reset();
                $scope.Variables.stvPossibleValues.dataSet = [];
            }

            $scope.$broadcast('dlgDynamicFieldOpened');
        };

        $scope.dlgFormTypeFieldClose = function($event, $isolateScope) {
            formTypeFieldToEdit = null;
        };

        $scope.button2Click = function($event, $isolateScope) {
            $scope.dlgFormTypeFieldClose($event, $isolateScope);
            $scope.Widgets.dlgFormTypeField.close();
        };
    }
]);

Application.$controller("gridPossibleValuesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dlgDeleteDynamicFieldController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);