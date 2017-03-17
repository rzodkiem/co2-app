export default class EmissionService{
    /*@ngInject*/

    constructor($http){
        this.$http = $http;
    }

    getEmissions(country, sector, startYear, endYear){
        this.$http({
            method: 'POST',
            url: '/emission/emissions',
            data:{
                country: country,

            }
        })
    }
}
