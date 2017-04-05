import 'babel-polyfill';


import '../scss/app.scss';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import 'isteven-angular-multiselect/isteven-multi-select.js';
import 'ng-storage';
import ngFileUpload from 'ng-file-upload';
import tcChartjs from 'tc-angular-chartjs';
import Chart from 'chart.js/src/chart.js';
import 'angular-simple-sidebar/angular-simple-sidebar.min.js' //from '../../node_modules/angular-simple-sidebar/angular-simple-sidebar.min';
import ngSidebar from '../../node_modules/angular-sidebar/src/index.js';
import 'angucomplete-alt';
import uiGrid from 'angular-ui-grid';

/**Angular configuration **/
import Router from './router';

/**Angular controllers **/
import Co2Controller from './app/controllers/co2.controller.js';
import LoginController from './app/controllers/login.controller.js';
import LogoutController from './app/controllers/logout.controller.js';
import AppController from './app/controllers/app.controller.js';
import TableController from './app/controllers/table.controller.js';
import ChartController from './app/controllers/chart.controller.js';
import MapController from './app/controllers/map.controller.js';


/** Angular Services **/
import AppService from './app/service/app.service.js';

/** Angular Factories **/
import DataFactory from './app/factory/data.factory.js';

const ANGULAR_MODULE_NAME = 'co2Insight';

angular.module(ANGULAR_MODULE_NAME,
    [
        uiRouter,
        ngFileUpload,
        tcChartjs,
        'angular-simple-sidebar',
        ngSidebar,
        'angucomplete-alt',
        'isteven-multi-select',
        uiGrid,
 //       Chart,
        tcChartjs,
        'ngStorage'

    ])

    .config(Router)

    .controller('Co2Controller', Co2Controller)
    .controller('LoginController', LoginController)
    .controller('LogoutController', LogoutController)
    .controller('AppController', AppController)
    .controller('TableController', TableController)
    .controller('ChartController', ChartController)
    .controller('MapController', MapController)

    .service('AppService', AppService)

    .factory('DataFactory', DataFactory)

;