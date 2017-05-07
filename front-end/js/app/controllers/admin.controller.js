export default class AdminController {
    /*@ngInject*/

    constructor($state, $scope, AdminService, SessionFactory, ngNotify) {
        this.$state = $state;
        this.$scope = $scope;
        this.isAdmin = SessionFactory.isAdmin();
        this.AdminService = AdminService;
        this.ngNotify = ngNotify;

        if (!this.isAdmin) {
            this.$state.go('app.login');
        }

    }

    upload(){
        this.showLoader = true;
        this.AdminService.upload(this.file).then(response => {
            if(response.status === 200){
                this.ngNotify.set('File has been successfully uploaded', 'success');
                this.showLoader = false;
            }else{
                this.ngNotify.set('Server error occurred during upload', 'error');
                this.showLoader = false;
            }
        })
    }
}