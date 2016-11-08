import 'babel-polyfill';

// import '../scss/preload.scss';
import '../scss/app.scss';

//External dependencies
import angular from 'angular';
import uiRouter from 'angular-ui-router';
import 'isteven-angular-multiselect/isteven-multi-select.js'
import ngTouch from 'angular-touch';
import 'ng-storage';
import ngFileUpload from 'ng-file-upload';
import tcChartjs from 'tc-angular-chartjs';
import Chart from 'chart.js';


//Internal dependecies
import Router from './router';

import Co2Controller from './controllers/co2.controller';
import LoginController from './controllers/login.controller';


import LoginService from './services/login.service';

const MODULE_NAME = 'Co2';

angular.module(MODULE_NAME,
    [
        uiRouter,
        tcChartjs,
        ngFileUpload,
        ngTouch,
        'ngStorage',
        'isteven-multi-select'
    ])
    .config(Router)

    .constant('AUTH_EVENTS', {
        loginSuccess: 'auth-login-success',
        loginFailed: 'auth-login-failed',
        logoutSuccess: 'auth-logout-success',
        sessionTimeout: 'auth-session-timeout',
        notAuthenticated: 'auth-not-authenticated',
        notAuthorized: 'auth-not-authorized'
    })
    .constant('USER_ROLES', {
        all: '*',
        admin: 'ROLE_ADMIN',
        user: 'ROLE_USER'
    })
    .constant('NET_STATUS', {
        undefined: 'Undefined',
        online: 'Online',
        offline: 'Offline'
    })

    .controller('Co2Controller', Co2Controller)
    .controller('LoginController', LoginController)

    .service('LoginService', LoginService)
;

export default MODULE_NAME;