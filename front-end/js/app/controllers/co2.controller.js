export default class Co2Controller{
    /*@ngInject*/
    constructor($state, $scope){
        this.showLoader = false;
        this.$state = $state;
        this.$scope = $scope;
        $scope.$on('showLoader', () => this.showLoader = true);
        $scope.$on('hideLoader', () => this.showLoader = false);
    }
}