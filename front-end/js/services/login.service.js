export default class LoginService {
    constructor($http){
        this.$http = $http;
    }

    login(params) {
        return this.$http({
            method: 'POST',
            url: '/login',
            params: params
        });
    }


}

LoginService.$inject = ['$http'];