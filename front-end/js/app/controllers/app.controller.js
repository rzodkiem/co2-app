export default class AppController{
    /*@ngInject*/
    constructor($scope, $state){
        this.showLoader = false;
        this.$scope = $scope;
        this.$state = $state;


    }

    openFilters(){
        console.log('opening filters');
        this.$scope.$broadcast('openSidebar');
    }
}