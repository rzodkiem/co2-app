export default class LoginController{
    /*@ngInject*/
    constructor($state, $scope, LoginService, SessionFactory){
        this.$state = $state;
        this.$scope = $scope;
        this.LoginService = LoginService;
        this.SessionFactory = SessionFactory;
    }

    submit(){
        console.log(this.credentials);
        this.LoginService.login(this.credentials).then((response) => {
            if(response.status === 200){
                this.SessionFactory.setSession(response.data);
                this.$state.go('app.admin');
            }else{
                //TODO: Notify about unathorized
            }
        })
    }


}