export default class AppController{
    /*@ngInject*/
    constructor($scope, $state){
        this.showLoader = false;
        this.$scope = $scope;
        this.$state = $state;
        console.log(this.$scope);
    }
}