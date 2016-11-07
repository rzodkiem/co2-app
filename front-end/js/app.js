import 'babel-polyfill';

// import '../scss/preload.scss';
import '../scss/app.scss';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import 'isteven-angular-multiselect/isteven-multi-select.js'
import ngTouch from 'angular-touch';
import 'ng-storage';
import ngFileUpload from 'ng-file-upload';
import tcChartjs from 'tc-angular-chartjs';
import Chart from 'chart.js';

import Router from './router';


const MODULE_NAME = 'co2';

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
;

export default MODULE_NAME;