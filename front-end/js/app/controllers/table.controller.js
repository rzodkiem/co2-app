export default class TableController{
    /*@ngInject*/
    constructor($state, $scope){
        this.$state = $state;
        this.$scope = $scope;

        this.$scope.$on('emissionsLoadedEvent', (event, data) => {
            this.emissions = data.data;
        })

    }
}