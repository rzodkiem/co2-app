export default class LoginController{
    /*@ngInject*/
    constructor($state, $scope, LoginService, SessionFactory, ngNotify){
        this.$state = $state;
        this.$scope = $scope;
        this.LoginService = LoginService;
        this.SessionFactory = SessionFactory;
        this.ngNotify = ngNotify;
    }

    submit(){

        this.LoginService.login(this.credentials)

            .success(response => {
                this.SessionFactory.setSession(response);
                this.$state.go('app.admin');
        }).
            error(response => {
                this.ngNotify.set('Incorrect username or password', 'error');
            })
    }


}