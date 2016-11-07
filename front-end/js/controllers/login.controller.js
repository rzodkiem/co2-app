export default class LoginController {

    constructor(LoginService, $state) {
        this.LoginService = LoginService;
        this.$state = $state;

        this.params = {
            username: '',
            password: ''
        };
        this.requesting = false;
    }

    submitForm () {
        if (!this.requesting && this.params.username && this.params.password) {
            this.requesting = true;
            this.LoginService.login(this.params)
                .then((response) => {
                    this.$state.go('app.table');
                })
                .catch((response) => {
                    // show error
                    this.requesting = false;
                });
        }
    }

}

LoginController.$inject = ['LoginService', '$state'];
