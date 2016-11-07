export default class UploadFileService{

    constructor($http){
        this.$http = $http;

    }

    upload(file){
        return this.$http({
            method: 'POST',
            url : 'upload',
            file : file
        })
    }

}
UploadFileService.$inject = ['$http'];
