export default class Co2Controller {

    constructor($window, $scope, $state) {
        this.fullscreenSafari = $window.navigator && $window.navigator.standalone;
        this.showLoader = false;

        $scope.$on('showLoader', () => this.showLoader = true);
        $scope.$on('hideLoader', () => this.showLoader = false);

    }

}
Co2Controller.$inject = ['$window', '$scope', '$state'];