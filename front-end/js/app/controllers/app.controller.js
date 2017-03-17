export default class AppController{
    /*@ngInject*/
    constructor($scope, $state, AppService){

        this.showLoader = false;

        //global variables
        this.$scope = $scope;
        this.$state = $state;
        this.AppService = AppService;

        //initialization for model
        this.selectedCountry = {};
        this.selectedSection = {};

        //calling initial back-end queries
        this.getCountries();
        this.getSectors();

    }

    openFilters(){
        this.$scope.$broadcast('openSidebar');
    }

    selectFilterValue(data){
        console.log(data);
    }

    getCountries(){
        this.AppService.getCountries().then(response => {
            if(response.status != 200){
                //TODO: notify about error
            }else{
                this.countries = response.data;
            }
        })
    }

    getSectors(){
        this.AppService.getSectors().then(response => {
            if(response.status != 200){
                //TODO: notify about error
            }else{
                this.sections = response.data;
            }
        })
    }

    clearFilters(){
        //TODO: clear all inputs
    }

    confirmFilters(){

    }
}