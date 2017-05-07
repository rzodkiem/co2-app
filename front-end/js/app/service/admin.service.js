export default class AdminService {
    /*@ngInject*/

    constructor($http) {
        this.$http = $http;
    }

    upload(file) {
        var fd = new FormData();
        fd.append('file', file);
        return this.$http({

            method: 'POST',
            url: '/upload/unfccc',
            data: fd,
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        })

    }
}