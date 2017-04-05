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

    getEmissions(startYear, endYear, countries, sectors){
        return this.$http({
            method: 'POST',
            url: '/emission/emissions',
            data: {
                startDate: startYear,
                endDate: endYear,
                countries: this.parseObjects(countries),
                sectors: this.parseObjects(sectors)
            },
            headers:{
                'Content-Type': 'application/json'
            }

        })
    }


    parseObjects(objects){
        let values = [];
        objects.forEach(item => {
            values.push(item.name);
        })
        return values;
    }


}