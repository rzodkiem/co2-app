export default class LoginService{
    /*@ngInject*/

    constructor($http){
        this.$http = $http;
    }

    login(credentials){
        return this.$http({
            method: 'POST',
            url: '/login',
            params: {
                username: credentials.username,
                password: credentials.password
            }
        })
    }
}