export default class AppController{
    /*@ngInject*/
    constructor($scope, $state){
        this.showLoader = false;
        this.$scope = $scope;
        this.$state = $state;
        this.selectedCountry = {};
        this.selectedSection = {};
        this.countries = [
            {
                name: 'Poland'
            },
            {
                name: 'Germany'
            }

        ];

        this.datepickerOptions = {
            datepickerMode:"'year'",
            minMode:"'year'",
            minDate:"minDate",
            showWeeks:"false",
        };

        this.format = 'yyyy';


    }

    openFilters(){
        this.$scope.$broadcast('openSidebar');
    }

    selectFilterValue(data){
        console.log(data);
    }
}