export default class TableController{
    /*@ngInject*/
    constructor($state, $scope){
        this.$state = $state;
        this.$scope = $scope;
        console.log('test');

        this.$scope.$on('emissionsLoadedEvent', (event, data) => {
            console.log('event in table controller');
            this.emissions = data;
            console.log(this.emissions);
        })

    }
}