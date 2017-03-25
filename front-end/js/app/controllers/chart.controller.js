export default class MapController{
    /*@ngInject*/
    constructor($state, $scope, $filter){
        this.$scope = $scope;
        this.$state = $state;
        this.$filter = $filter;
        this.$scope.$on('emissionsLoadedEvent', (event, data) => {
            this.emissions = data.data;
            this.years = this.$filter('unique')(this.emissions, 'year')
            console.log(this.years);
        })

        this.testData = [10, 20, 30, 50];


    }
}