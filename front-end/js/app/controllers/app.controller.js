export default class AppController{
    /*@ngInject*/
    constructor($scope, $state, $timeout, DataFactory, AppService) {
        this.$timeout = $timeout;
        this.showLoader = false;

        //global variables
        this.$scope = $scope;
        this.$state = $state;
        this.DataFactory = DataFactory;
        this.AppService = AppService;


        //calling initial back-end queries
        this.getCountries();
        this.getSectors();
        this.getYears();
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
                this.sectors = response.data;
            }
        })
    }

    getYears(){
        this.AppService.getYears().then(response => {
            if(response.status != 200){

            }else{
                this.startYears = response.data;
            }
        })
    }

    clearFilters(){
        this.startYear = {};
        this.endYear = {};
        this.$scope.$broadcast('angucomplete-alt:clearInput');
        this.selectedCountries = [];
        this.selectedSectors = [];
        this.clearMultiselects();
    }

    confirmFilters(){
        let startYear = this.startYear ? this.startYear.title : 0;
        let endYear = this.endYear ? this.endYear.title : 3000;
        this.AppService.getEmissions(
            startYear, endYear, this.selectedCountries, this.selectedSectors

        ).then(response => {
                if(response.status != 200){
                    this.$scope.$broadcast('hideSidebar');

                }else{
                    this.DataFactory.setEmission(response.data);
                    this.$scope.$broadcast('emissionsLoadedEvent', {
                        data: response.data
                    })
                    this.$scope.$broadcast('hideSidebar');
                }
            })

    }

    clearMultiselects(){
        this.countries.forEach(item => item.ticked = false);
        this.sectors.forEach(item => item.ticked = false);


    }
}