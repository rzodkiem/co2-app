export default class AppController{
    /*@ngInject*/
    constructor($scope, $state, AppService){

        this.showLoader = false;

        //global variables
        this.$scope = $scope;
        this.$state = $state;
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
        console.log('clear filters');
        this.startYear = undefined;
        this.endYear = undefined;
        this.$scope.$broadcast('angucomplete-alt:clearInput');
        this.selectedCountries = [];
        this.selectedSectors = [];
        this.clearMultiselects();
        console.log(this.startYear);
    }

    confirmFilters(){
        console.log(this.countries);
        console.log(this.sectors);
        console.log(this.startYear);
        console.log(this.endYear);
        this.AppService.getEmissions(
            this.startYear.title, this.endYear.title, this.selectedCountries, this.selectedSectors

        ).then(response => {
                if(response.status != 200){
                    this.$scope.$broadcast('hideSidebar');

                }else{
                    this.emissions = response.data;
                    console.log(this.emissions);
                    this.$scope.$broadcast('hideSidebar');
                }
            })

    }

    clearMultiselects(){
        this.countries.forEach(item => item.ticked = false);
        this.sectors.forEach(item => item.ticked = false);


    }
}