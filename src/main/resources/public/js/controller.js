'use strict';

$.fn.dataTable.Api.register( 'jumpToData()', function ( data, column ) {
	var pos = this.column(column, {order:'current'}).data().indexOf( data );

	if ( pos >= 0 ) {
		var page = Math.floor( pos / this.page.info().length );
		this.page( page ).draw( false );
	}

	return this;
} );

myapp.controller('LoginController', function ($rootScope, $scope, AuthSharedService) {
        $scope.rememberMe = true;
        $scope.login = function () {
            $rootScope.authenticationError = false;
            AuthSharedService.login(
                $scope.username,
                $scope.password,
                $scope.rememberMe
            );
        }
    })
    .controller('HomeController', function ($rootScope,$scope, HomeService) {
    	console.log('In Home'+$rootScope.accountLookup);
    	var data;
    	var oTable;
    	$scope.columns = [{'name':'Account Number', 'value':'accountNumber'},
    	                  {'name':'Account Name', 'value':'accountName'},
    	                  {'name':'Market Value', 'value':'marketValueString'},
    	                  {'name':'YTD Rtn', 'value':'ytdPerformance'},
    	                  {'name':'1Yr Rtn', 'value':'oneYearPerformance'},
    	                  {'name':'2Yr Rtn', 'value':'twoYearPerformance'},
    	                  {'name':'3Yr Rtn', 'value':'threeYearPerformance'},
    	                  {'name':'5Yr Rtn', 'value':'fiveYearPerformance'},
    	                  {'name':'10Yr Rtn', 'value':'tenYearPerformance'},
    	                  {'name':'Inception Rtn', 'value':'sinceInceptionPerformance'},
    	                  {'name':'LT Realized Gain/Loss', 'value':'ltrealizedGainLoss'},
    	                  {'name':'ST Realized Gain/Loss', 'value':'strealizedGainLoss'}
    	                  ];
    	$scope.selectedColumns = ['Account Number', 'Account Name', 'Market Value', 'YTD Rtn'];
    	$scope.selectedDatatableColumns = [{ "sTitle": "Account Number","mData": "accountNumber" },
    	                                   { "sTitle": "Account Name","mData": "accountName" },
    	                                   { "sTitle": "Market Value","mData": "marketValueString" },
    	                                   { "sTitle": "YTD Rtn","mData": "ytdPerformance" }];
    	var title = ['Account MarketValue Report','Account YTD Performance Report','Account Realized Gain/Loss Report'];
    	var chart;
    	
    	$scope.toggleSelection = function toggleSelection(column) {
       		var idx = $scope.selectedColumns.indexOf(column.name);
        	// is currently selected
        	if (idx > -1) {
          		$scope.selectedColumns.splice(idx, 1);
          		$scope.selectedDatatableColumns.splice(idx, 1);
        	}
        	// is newly selected
        	else {
          		$scope.selectedColumns.push(column.name);
          		var newColumn = {"sTitle":column.name,"mData": column.value}
          		$scope.selectedDatatableColumns.push(newColumn);
        	}
        	//$('#columnSettings').removeClass('open');
        	oTable.fnClearTable();
        	oTable.fnDraw();
        	oTable.fnDestroy();
        	$('#account-datatable').empty();
        	createTable();
    	};
    	
    	function createTable() {
    		oTable = $('#account-datatable').dataTable( {
                "bDestroy": true,
                "aaData": data.accounts,
                "aoColumns": $scope.selectedDatatableColumns,
                "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
               	 $(nRow).removeClass('highlight');
               	 if ( aData.accountName == $scope.selectedAccount ) {    
               	    $(nRow).addClass('highlight');
               	 }
               },
               "fnDrawCallback": function(oSettings) {
               	this.on( 'click','tr', function () {
               		sorting(this.rowIndex);
               	});
               }
            });
    	}
      
        HomeService.getDashBoardDetails().then(function(d) {
            $scope.faData = d.data;
        });
        
        HomeService.getSummaryDetails($rootScope.accountLookup).then(function(d) {
        	data = d.data;
            createTable();
            $scope.accountNames = populateAccountNames(data.ytdPerformance);
            $scope.ytd = populateAccountValue(data.ytdPerformance);
            $scope.marketValues = populateAccountValue(data.marketValues);
        	options.series[0].data = $scope.ytd;
        	options.xAxis.categories = $scope.accountNames;
        	$scope.selectedAccount='';
        	Highcharts.setOptions({
        	    lang: {
        	        thousandsSep: ','
        	    }
        	});
            chart = new Highcharts.Chart(options);
        });
        
        function populateAccountNames(mappedObj) {
    		var keys = [];
    		for(var obj in mappedObj) {
    			keys.push(obj);
        	}
    		return keys;
    	}
    	
    	function populateAccountValue(mappedObj) {
    		var values = [];
    		for(var obj in mappedObj) {
    			values.push(mappedObj[obj]);
        	}
    		return values;
    	}
    	
    	function sortData(mappedObj, compareByKey, isAscending){
            var sortable = [];
            for (var obj in mappedObj) {
                sortable.push([obj, mappedObj[obj]])
            }
            sortable.sort(
            		function(a, b) {
            			if(compareByKey && isAscending) {
            				if(a[0] > b[0]) {
            					return 1;
            				} else {
            					return -1;
            				}
            			} else if(compareByKey && !isAscending) {
            				if(b[0] > a[0]) {
            					return 1;
            				} else {
            					return -1;
            				}
            			} else if(!compareByKey && isAscending) {
            				return a[1] - b[1];
            			} else if(!compareByKey && !isAscending) {
            				return b[1] - a[1];
            			} 
            		}
            )
            return sortable;
        }
    	
    	function populateAccountDataFromSortedArray(mappedObj, isKey) {
    		var values = [];
    		for(var obj in mappedObj) {
    			if(isKey ==1) {
    				values.push(mappedObj[obj][0]);
    			} else {
    				values.push(mappedObj[obj][1]);
    			}
        	}
    		return values;
    	}
    	
    	
    	function sorting(rowIndex) {
            if(rowIndex==0) { //Header sorting
            	oTable = $('#account-datatable').dataTable();
            	var setting = oTable.fnSettings();
            	var sortInfo = setting.aaSorting;
                var direction = sortInfo[0][1];
                var columnIndex = sortInfo[0][0];
                var columnName = $scope.selectedColumns[columnIndex];
                if(columnName == "Account Name" || columnName == "Account Number") {
                	var sortedMap =[];
                	if(options.yAxis.title.text == "Market Value") {
	                	if(direction =='desc') {
	                		sortedMap= sortData(data.marketValues,1,0);
	                	} else {
	                		sortedMap= sortData(data.marketValues,1,1);
	                	}
                	} else {
                		var columnValue;
                    	for (var val in $scope.columns) {
                    		var column = $scope.columns[val];
    						if(column.name == options.yAxis.title.text) {
    							columnValue = column.value;
    							break;
    						}
    					}
                		if(direction =='desc') {
	                		sortedMap= sortData(data[columnValue],1,0);
	                	} else {
	                		sortedMap= sortData(data[columnValue],1,1);
	                	}
                	}
                	options.xAxis.categories=populateAccountDataFromSortedArray(sortedMap,1);
                	options.series[0].data = populateAccountDataFromSortedArray(sortedMap,0);
                	chart = new Highcharts.Chart(options);
                }
                if(columnName == "Market Value") {
                	options.title.text=title[0];
                	options.yAxis.title.text=columnName;
                	var sortedMap =[];
                	if(direction =='desc') {
                		sortedMap= sortData(data.marketValues,0,0);
                	} else {
                		sortedMap= sortData(data.marketValues,0,1);
                	}
                	options.xAxis.categories=populateAccountDataFromSortedArray(sortedMap,1);
                	options.series[0].data = populateAccountDataFromSortedArray(sortedMap,0);
                	options.yAxis.labels.formatter=function() {
                		return '$' + this.axis.defaultLabelFormatter.call(this);
                	};
                	options.tooltip.formatter = function() {
                		return '<b>'+ this.x+ ' : '+ Highcharts.numberFormat(this.y, 0) + '</b>'
                	}
                	chart = new Highcharts.Chart(options);
                }
                if(columnName.indexOf('Rtn')>=0 || columnName.indexOf('Gain')>=0) {
                	options.title.text=title[1];
                	options.yAxis.title.text=columnName;
                	var columnValue;
                	for (var val in $scope.columns) {
                		var column = $scope.columns[val];
						if(column.name == columnName) {
							columnValue = column.value;
							break;
						}
					}
                	var sortedArray =[];
                	if(direction =='desc') {
                		sortedArray= sortData(data[columnValue],0,0);
                	} else {
                		sortedArray= sortData(data[columnValue],0,1);
                	}
                	options.xAxis.categories=populateAccountDataFromSortedArray(sortedArray,1);
                	options.series[0].data = populateAccountDataFromSortedArray(sortedArray,0);
                	if(columnName.indexOf('Rtn')>=0) {
                		options.yAxis.labels.formatter=function() {return this.value + ' %';};
                		options.tooltip.formatter = function() {
                    		return '<b>'+ this.x+ ' : '+ this.y + '</b>';
                    	}
                	} else {
                		options.title.text=title[2];
                		options.yAxis.labels.formatter=function() {
                    		return '$' + this.axis.defaultLabelFormatter.call(this);
                    	};
                    	options.tooltip.formatter = function() {
                    		return '<b>'+ this.x+ ' : '+ Highcharts.numberFormat(this.y, 0) + '</b>'
                    	}
                	}
                	chart = new Highcharts.Chart(options);
                }
            }
    	}
    	
    	var options = {
        	chart: {
        		renderTo: 'container',
                type: 'column'
            },
            title: {
                text: 'Account YTD Performance Report'
            },
            tooltip: {
                formatter: function () {
                    return '<b>'+ this.x+ ' : '+ this.y + '</b>';
                }
            },
            xAxis: {
                title: {
                	text: 'Accounts'
                },
                categories: $scope.accountNames,
                labels: {enabled:false}
            },
            yAxis: {
            	title: {
                	text: 'YTD Rtn'
                },
                labels: {
                    formatter: function() {
                        return this.value + ' %';
                    }
                }
            },
            plotOptions: {
            	series: {
            		cursor : 'pointer',
            		point : {
            			events: {
            				click : function() {
            					$scope.selectedAccount = this.category;
            					oTable.fnDraw();
            				}
            			}
            		},
            		dataLabels: {
            			shape: 'callout'
            		},
            		showInLegend: false       
            	}
            },
            credits: {
                enabled: false
            },
            series: [{
            	data:$scope.ytd
            }]
    	}
    })
    .controller('UsersController', function ($scope, $log, UsersService) {
        UsersService.getAll().then(function(d) {
            var oTable = jQuery('#user-datatable').dataTable( {
                "bDestroy": true,
                "aaData": d.data,
                "aoColumns": [
                { "mData": "login" },
                { "mData": "firstName" },
                { "mData": "familyName" },
                { "mData": "email" },
                ]
            });         
        });
    })
    .controller('LogoutController', function (AuthSharedService) {
        AuthSharedService.logout();
    })
    .controller('NavigationController', function ($rootScope,$scope,$route) {
    	if($scope.accountLookup =='') {
    		$scope.accountLookup = $rootScope.accountLookup;
    	}
    	$scope.search = function () {
    		console.log($scope.accountLookup);
    		$rootScope.accountLookup = $scope.accountLookup;
    		$route.reload();
    	}
    })
    .controller('ErrorController', function ($scope, $routeParams) {
        $scope.code = $routeParams.code;

        switch ($scope.code) {
            case "403" :
                $scope.message = "Oops! you have come to unauthorised page."
                break;
            case "404" :
                $scope.message = "Page not found."
                break;
            default:
                $scope.code = 500;
                $scope.message = "Oops! unexpected error"
        }

    });

function jumpToPage ( table, data, column ) {
	var pos = table.column(column, {order:'current'}).data().indexOf( data );

	if ( pos >= 0 ) {
		var page = Math.floor( pos / table.page.info().length );
		table.page( page ).draw( false );
	}

	return table;
}
