export default class AppController{
    /*@ngInject*/
    constructor($scope, $state, $timeout, DataFactory, AppService, SessionFactory, ngNotify) {
        this.$timeout = $timeout;
        this.showLoader = false;
        this.ngNotify = ngNotify;
        //global variables
        this.$scope = $scope;
        this.$state = $state;
        this.DataFactory = DataFactory;
        this.AppService = AppService;
        this.isAdmin = SessionFactory.isAdmin();

        this.chartOptions = [
            {
                name: 'countryChartData',
                display: 'Country',
                chartTitle: 'Emission per Country'
            },
            {
                name: 'categoryChartData',
                display: 'Category',
                chartTitle: 'Emission per Sector'
            }
        ];

        this.selectedChart = this.chartOptions[0];


        //calling initial back-end queries
        this.getCountries();
        this.getSectors();
        this.getYears();
    }

    openFilters(){
        this.$scope.$broadcast('openSidebar');
    }

    getCountries(){
        this.AppService.getCountries()

            .success(response => {
                this.countries = response;
            })
            .error(() => {
                this.ngNotify.set('Server error occurred during loading data', 'error');
            })

    }

    getSectors(){
        this.AppService.getSectors()
            .success(response => {
                this.sectors = response;
            })
            .error(() => {
                this.ngNotify.set('Server error occurred during loading data', 'error');
            })
    }

    getYears(){
        this.AppService.getYears()
            .success((response) => {
                this.startYears = response;
            })
            .error(() => {
                this.ngNotify.set('Server error occurred during loading data', 'error');
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
                    });
                    this.$scope.$broadcast('hideSidebar');
                }
            })

    }

    clearMultiselects(){
        this.countries.forEach(item => item.ticked = false);
        this.sectors.forEach(item => item.ticked = false);


    }

    changeChartData() {
        this.$scope.$broadcast('chartDataChangedEvent', {
            aggregation: this.selectedChart.name,
            chartTitle: this.selectedChart.chartTitle
        })
    }
}