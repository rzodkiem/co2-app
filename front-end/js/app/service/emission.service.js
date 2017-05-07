export default class EmissionService{
    /*@ngInject*/

    constructor($http){
        this.$http = $http;
    }

    getEmissions(country){
        this.$http({
            method: 'POST',
            url: '/emission/emissions',
            data:{
                country: country

            }
        })
    }
}
