export default class AppService{
    /*@ngInject*/

    constructor($http){
        this.$http = $http;
    }

    getSectors(){
        return this.$http({
            method: 'GET',
            url: '/emission/sectors'
        })
    }

    getCountries(){
        return this.$http({
            method: 'GET',
            url: '/emission/countries'
        })
    }

    getYears(){
        return this.$http({
            method: 'GET',
            url: '/emission/years'
        })
    }
}