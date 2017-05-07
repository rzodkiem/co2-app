export default class TableController{
    /*@ngInject*/
    constructor($state, $scope, DataFactory) {
        this.$state = $state;
        this.$scope = $scope;
        this.DataFactory = DataFactory;

        console.log('table constructor');

        this.$scope.$on('emissionsLoadedEvent', (event, data) => {
            this.emissions = data.data;
        });

        if (!this.emissions) {
            console.log('getting from local storage');
            this.emissions = this.DataFactory.getTableData();
        }

    }
}