export default class AdminController {
    /*@ngInject*/

    constructor($state, $scope, AdminService, SessionFactory) {
        this.$state = $state;
        this.$scope = $scope;
        this.isAdmin = SessionFactory.isAdmin();
        this.AdminService = AdminService;

        if (!this.isAdmin) {
            this.$state.go('app.login');
        }

    }

    upload(){
        this.showLoader = true;
        this.AdminService.upload(this.file).then(response => {
            if(response.status === 200){
                //TODO: notify success
                this.showLoader = false;
            }else{
                //TODO: notify error
                this.showLoader = false;
            }
        })
    }
}