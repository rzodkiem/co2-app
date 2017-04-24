export default class AdminController {
    /*@ngInject*/

    constructor($state, $scope, AdminService, SessionFactory) {
        this.$state = $state;
        this.$scope = $scope;
        this.isAdmin = SessionFactory.isAdmin();

        if (!this.isAdmin) {
            this.$state.go('app.login');
        }

    }
}